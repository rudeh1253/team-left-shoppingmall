package team.left.framework.context.core.mapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import team.left.framework.context.annotation.Bean;
import team.left.framework.context.annotation.Configuration;
import team.left.framework.context.core.dependency.DependencyFinder;
import team.left.framework.context.core.util.NameUtils;

public class DefaultBeanInitializerMapper implements BeanInitializerMapper {
    private final DependencyFinder dependencyFinder;

    public DefaultBeanInitializerMapper(DependencyFinder depdencyFinder) {
        this.dependencyFinder = depdencyFinder;
    }

    @Override
    public BeanInfoWrapper map(Set<Class<?>> beanClasses) {
        Map<String, BeanInstantiatable> beanInitializerByBeanName = new HashMap<>();
        Map<String, Class<?>> typePerBean = new HashMap<>();

        for (Class<?> beanClass : beanClasses) {
            // TODO: ContextClassDecision 사용해야 함
            if (beanClass.isAnnotationPresent(Configuration.class)) {
                addInstantiaterFromConfigClass(beanClass, beanInitializerByBeanName, typePerBean);
            } else {
                addInstantiaterFromComponentAnnotated(beanClass, beanInitializerByBeanName, typePerBean);
            }
        }

        return new BeanInfoWrapper(beanInitializerByBeanName, typePerBean);
    }

    private String addInstantiaterFromComponentAnnotated(Class<?> componentClass,
            Map<String, BeanInstantiatable> beanInstantiaterByBeanName, Map<String, Class<?>> typePerBean) {
        String beanName = NameUtils.generateDefaultBeanName(componentClass);
        beanInstantiaterByBeanName.put(beanName,
                (Map<Class<?>, Collection<String>> beansPerType, Map<String, Object> instancePerBeanName) -> {
                    Constructor<?> constructor = this.dependencyFinder.getInjectionConstructor(componentClass);
                    Class<?>[] paramTypes = constructor.getParameterTypes();
                    Type[] genericParamTypes = constructor.getGenericParameterTypes();
                    Object[] dependencyParamsForConstructor =
                            getDependencyBeanArgs(constructor.getParameterCount(), paramTypes, genericParamTypes, instancePerBeanName, beansPerType);

                    try {
                        Object newBean = constructor.newInstance(dependencyParamsForConstructor);
                        Collection<Method> injectionMethods = this.dependencyFinder.getInjectionMethods(componentClass);
                        for (Method injectionMethod : injectionMethods) {
                            Class<?> parameterType = injectionMethod.getParameterTypes()[0];
                            Type genericParameterType = injectionMethod.getGenericParameterTypes()[0];
                            Object dependency = getBeanForParameter(parameterType, genericParameterType,
                                    instancePerBeanName, beansPerType);
                            injectionMethod.invoke(newBean, dependency);
                        }

                        Collection<Field> injectionFields = this.dependencyFinder.getInjectionFields(componentClass);
                        for (Field injectionField : injectionFields) {
                            injectionField.setAccessible(true);
                            Class<?> parameterType = injectionField.getType();
                            Type genericParameterType = injectionField.getGenericType();
                            Object dependency = getBeanForParameter(parameterType, genericParameterType,
                                    instancePerBeanName, beansPerType);
                            injectionField.set(newBean, dependency);
                            injectionField.setAccessible(false);
                        }

                        return newBean;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
        typePerBean.put(beanName, componentClass);
        return beanName;
    }

    private void addInstantiaterFromConfigClass(Class<?> configClass,
            Map<String, BeanInstantiatable> beanInstantiaterByBeanName, Map<String, Class<?>> typePerBean) {
        String configBeanName = addInstantiaterFromComponentAnnotated(configClass, beanInstantiaterByBeanName,
                typePerBean);

        Method[] beanMethods = Arrays.stream(configClass.getDeclaredMethods())
                .filter((m) -> m.isAnnotationPresent(Bean.class))
                .toArray(Method[]::new);
        for (Method beanMethod : beanMethods) {
            beanMethod.setAccessible(true);
            String beanName = NameUtils.convertToCamelCase(beanMethod.getName());
            beanInstantiaterByBeanName.put(beanName,
                    (Map<Class<?>, Collection<String>> beansPerType, Map<String, Object> instancePerBeanName) -> {
                        Object configBean = instancePerBeanName.get(configBeanName);
                        try {
                            Class<?>[] paramTypes = beanMethod.getParameterTypes();
                            Type[] genericParamTypes = beanMethod.getGenericParameterTypes();
                            Object[] dependencyParams =
                                    getDependencyBeanArgs(beanMethod.getParameterCount(), paramTypes, genericParamTypes, instancePerBeanName, beansPerType);
                            beanMethod.setAccessible(true);
                            Object result = beanMethod.invoke(configBean, dependencyParams);
                            beanMethod.setAccessible(false);
                            return result;
                        } catch (Exception e) { // TODO: 구체적인 예외
                            throw new RuntimeException(e);
                        }
                    });
            typePerBean.put(beanName, beanMethod.getReturnType());
            beanMethod.setAccessible(false);
        }
    }

    private Object[] getDependencyBeanArgs(int paramCount, Class<?>[] paramTypes, Type[] genericParamTypes,
            Map<String, Object> instancePerBeanName, Map<Class<?>, Collection<String>> beansPerType) {
        Object[] dependencyParams = new Object[paramCount];
        for (int i = 0; i < paramTypes.length; i++) {
            Class<?> paramType = paramTypes[i];
            Type genericParamType = genericParamTypes[i];
            dependencyParams[i] = getBeanForParameter(paramType, genericParamType, instancePerBeanName,
                    beansPerType);
        }
        return dependencyParams;
    }

    private Object getBeanForParameter(Class<?> parameterType, Type genericType,
            Map<String, Object> instancePerBeanName, Map<Class<?>, Collection<String>> beansPerType) {
        if (Collection.class.isAssignableFrom(parameterType)) {
            Class<?> genericClass = (Class<?>) (((ParameterizedType) genericType).getActualTypeArguments()[0]);
            return beansPerType.get(genericClass).stream().map(instancePerBeanName::get).collect(Collectors.toList());
        } else {
            String beanName = beansPerType.get(parameterType).iterator().next();
            return instancePerBeanName.get(beanName);
        }
    }
}
