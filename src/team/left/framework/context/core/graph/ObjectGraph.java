package team.left.framework.context.core.graph;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import team.left.framework.context.annotation.Bean;
import team.left.framework.context.annotation.Configuration;
import team.left.framework.context.core.dependency.DependencyFinder;
import team.left.framework.context.core.mapper.BeanInfoWrapper;
import team.left.framework.context.core.mapper.BeanTypeMapper;
import team.left.framework.context.core.util.NameUtils;

public class ObjectGraph {
    private final Map<String, Set<String>> adjacencies = new HashMap<>();

    private ObjectGraph() {
    }

    public static ObjectGraph makeObjectGraph(BeanInfoWrapper beanInfoWrapper,
            Map<Class<?>, Collection<String>> beanNamesByBeanType, DependencyFinder dependencyFinder) {
        Map<String, Class<?>> typePerBeanName = beanInfoWrapper.getTypePerBean();
        ObjectGraph objectGraph = new ObjectGraph();

        Set<String> verticesNotVisited = new HashSet<>(typePerBeanName.keySet());
        System.out.println(typePerBeanName);
        while (!verticesNotVisited.isEmpty()) {
            String vertexNotVisited = verticesNotVisited.iterator().next();
            dfs(vertexNotVisited, verticesNotVisited, objectGraph, typePerBeanName, beanInfoWrapper, beanNamesByBeanType,
                    dependencyFinder);
        }
        addDependencyFromBeanToConfigBean(objectGraph, typePerBeanName);
        return objectGraph;
    }

    private static void dfs(String vertex, Set<String> verticesNotVisited, ObjectGraph objectGraph,
            Map<String, Class<?>> typePerBeanName, BeanInfoWrapper beanInfoWrapper,
            Map<Class<?>, Collection<String>> beanNamesByBeanType, DependencyFinder dependencyFinder) {
        if (!verticesNotVisited.contains(vertex)) {
            return;
        }
        verticesNotVisited.remove(vertex);
        Set<String> nextVertices = new HashSet<>();
        if (beanInfoWrapper.isInstantiatedByMethod(vertex)) {
            Method method = beanInfoWrapper.getMethodToInstantiate(vertex);
            nextVertices.addAll(getNextVertices(method.getParameterTypes(), method.getGenericParameterTypes(),
                    beanNamesByBeanType));
            objectGraph.adjacencies.put(vertex, nextVertices);
        } else {
            Class<?> vertexType = typePerBeanName.get(vertex);
            Constructor<?> injectionConstructor = dependencyFinder.getInjectionConstructor(vertexType);
            Collection<Method> injectionMethods = dependencyFinder.getInjectionMethods(vertexType);
            Collection<Field> injectionFields = dependencyFinder.getInjectionFields(vertexType);

            nextVertices.addAll(getNextVertices(injectionConstructor.getParameterTypes(),
                    injectionConstructor.getGenericParameterTypes(), beanNamesByBeanType));
            for (Method method : injectionMethods) {
                nextVertices.addAll(getNextVertices(method.getParameterTypes(), method.getGenericParameterTypes(),
                        beanNamesByBeanType));
            }

            for (Field field : injectionFields) {
                nextVertices.addAll(getNextVertices(new Class<?>[] { field.getType() },
                        new Type[] { field.getGenericType() }, beanNamesByBeanType));
            }

            objectGraph.adjacencies.put(vertex, nextVertices);
        }
        for (String nextVertex : nextVertices) {
            dfs(nextVertex, verticesNotVisited, objectGraph, typePerBeanName, beanInfoWrapper, beanNamesByBeanType,
                    dependencyFinder);
        }
    }

    private static Collection<String> getNextVertices(Class<?>[] types, Type[] genericTypes,
            Map<Class<?>, Collection<String>> beanNamesByBeanType) {
        List<String> dependencies = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            Class<?> type = types[i];
            Type genericType = genericTypes[i];
            try {
                if (genericType instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = (ParameterizedType) genericType;
                    Class<?> actualTypeArgument = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    dependencies.addAll(beanNamesByBeanType.get(actualTypeArgument));
                } else {
                    dependencies.addAll(beanNamesByBeanType.get(type));
                }
            } catch (NullPointerException e) {
                throw new RuntimeException("다음 type에 해당하는 Bean이 없습니다: " + type + " / ", e);
            }
        }
        return dependencies;
    }

    private static void addDependencyFromBeanToConfigBean(ObjectGraph objectGraph,
            Map<String, Class<?>> typePerBeanName) {
        for (Map.Entry<String, Class<?>> beanNameAndType : typePerBeanName.entrySet()) {
            Class<?> clazz = beanNameAndType.getValue();
            if (clazz.isAnnotationPresent(Configuration.class)) {
                for (Method beanMethod : clazz.getDeclaredMethods()) {
                    if (beanMethod.isAnnotationPresent(Bean.class)) {
                        String methodBeanName = NameUtils.convertToCamelCase(beanMethod.getName());
                        if (!typePerBeanName.containsKey(methodBeanName)) {
                            throw new RuntimeException("뭔가 이상함");
                        }
                        objectGraph.adjacencies.get(methodBeanName).add(beanNameAndType.getKey());
                    }
                }
            }
        }
    }

    public Set<String> getDependenciesOfBean(String beanName) {
        return this.adjacencies.get(beanName);
    }
    
    public Set<String> keySet() {
        return this.adjacencies.keySet();
    }
}
