package team.left.framework.context.core.dependency.graph;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import team.left.framework.context.core.dependency.DependencyFinder;

public class DefaultDependencyGraphSearcher implements DependencyGraphSearcher {
    private final DependencyFinder dependencyFinder;

    public DefaultDependencyGraphSearcher(DependencyFinder dependencyFinder) {
        this.dependencyFinder = dependencyFinder;
    }

    @Override
    public boolean isTopologicalSort(Map<String, Class<?>> beanTypeByBeanName,
                                     Map<Class<?>, Collection<String>> beanNamesByBeanType) {
        Set<String> verticesNotVisited = new HashSet<>(beanTypeByBeanName.keySet());
        while (!verticesNotVisited.isEmpty()) {
            String vertexNotVisited = verticesNotVisited.iterator().next();
            if (doesCycleExists(vertexNotVisited, verticesNotVisited, new HashSet<>(), beanTypeByBeanName, beanNamesByBeanType)) {
                return false;
            }
        }
        return true;
    }

    private boolean doesCycleExists(String vertex,
                                    Set<String> verticesNotVisited,
                                    Set<String> visitationOfCurrentDfs,
                                    Map<String, Class<?>> beanTypeByBeanName,
                                    Map<Class<?>, Collection<String>> beanNamesByBeanType) {
        visitationOfCurrentDfs.add(vertex);
        if (!verticesNotVisited.contains(vertex)) {
            return false;
        }
        verticesNotVisited.remove(vertex);
        Class<?> vertexType = beanTypeByBeanName.get(vertex);
        Constructor<?> injectionConstructor = this.dependencyFinder.getInjectionConstructor(vertexType);
        Collection<Method> injectionMethods = this.dependencyFinder.getInjectionMethods(vertexType);
        Collection<Field> injectionFields = this.dependencyFinder.getInjectionFields(vertexType);

        List<String> nextVertices = new ArrayList<>();
        nextVertices.addAll(
                getNextVertices(
                        injectionConstructor.getParameterTypes(),
                        injectionConstructor.getGenericParameterTypes(),
                        beanNamesByBeanType
                )
        );
        for (Method method : injectionMethods) {
            nextVertices.addAll(
                    getNextVertices(
                            method.getParameterTypes(),
                            method.getGenericParameterTypes(),
                            beanNamesByBeanType
                    )
            );
        }
        for (Field field : injectionFields) {
            nextVertices.addAll(
                    getNextVertices(
                            new Class<?>[] { field.getType() },
                            new Type[] { field.getGenericType() },
                            beanNamesByBeanType
                    )
            );
        }

        boolean result = false;
        for (String nextVertex : nextVertices) {
            if (visitationOfCurrentDfs.contains(nextVertex)) {
                return true;
            }
            result = result || doesCycleExists(nextVertex,
                    verticesNotVisited,
                    visitationOfCurrentDfs,
                    beanTypeByBeanName,
                    beanNamesByBeanType);
        }
        return result;
    }

    private Collection<String> getNextVertices(Class<?>[] types,
                                               Type[] genericTypes,
                                               Map<Class<?>, Collection<String>> beanNamesByBeanType) {
        List<String> dependencies = new ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            Class<?> type = types[i];
            Type genericType = genericTypes[i];
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                Class<?> actualTypeArgument = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                dependencies.addAll(beanNamesByBeanType.get(actualTypeArgument));
            } else {
                dependencies.addAll(beanNamesByBeanType.get(type));
            }
        }
        return dependencies;
    }
}
