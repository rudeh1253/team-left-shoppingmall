package team.left.framework.context.core.mapper;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import team.left.framework.context.annotation.Component;
import team.left.framework.context.core.util.ContextClassDecision;

public class DefaultBeanTypeMapper implements BeanTypeMapper {
    private final ContextClassDecision contextClassDecision;

    public DefaultBeanTypeMapper(ContextClassDecision contextClassDecision) {
        this.contextClassDecision = contextClassDecision;
    }
    
    @Override
    public Map<String, Class<?>> mapBeanInstanceToName(Set<Class<?>> beanClasses) {
        Map<String, Class<?>> beanClassByBeanName = new HashMap<>();
        for (Class<?> beanClass : beanClasses) {
            Component component = beanClass.getAnnotation(Component.class);
            if (component == null) {
                throw new RuntimeException("Component Annotation 부재 - Error from: " + beanClass);
            }

            beanClassByBeanName.put(this.contextClassDecision.getBeanName(beanClass), beanClass);
        }
        return beanClassByBeanName;
    }

    @Override
    public Map<Class<?>, Collection<String>> mapBeansToType(Map<String, Class<?>> beanClassByName) {
        Map<Class<?>, Collection<String>> beanNamesByType = new HashMap<>();
        for (Map.Entry<String, Class<?>> beanTypeEntry : beanClassByName.entrySet()) {
            String name = beanTypeEntry.getKey();
            Class<?> type = beanTypeEntry.getValue();
            initTypes(name, type, beanNamesByType);
        }
        return beanNamesByType;
    }

    private void initTypes(String beanName,
                           Class<?> beanType,
                           Map<Class<?>, Collection<String>> beanNamesByType) {
        if (!beanNamesByType.containsKey(beanType)) {
            beanNamesByType.put(beanType, new HashSet<>());
        }
        Collection<String> beanNamesOfType = beanNamesByType.get(beanType);
        beanNamesOfType.add(beanName);
        if (beanType == Object.class || beanType.getSuperclass() == null) {
            return;
        }
        initTypes(beanName, beanType.getSuperclass(), beanNamesByType);
        for (Class<?> implementedInterface : beanType.getInterfaces()) {
            initTypes(beanName, implementedInterface, beanNamesByType);
        }
    }

    @Override
    public Map<Class<? extends Annotation>, Collection<String>> mapBeansToAnnotation(Map<String, Class<?>> beanClassByName) {
        Map<Class<?>, Collection<String>> beansByType = mapBeansToType(beanClassByName);
        Map<Class<? extends Annotation>, Collection<String>> beansByAnnotation = new HashMap<>();
        for (Map.Entry<Class<?>, Collection<String>> beansByTypeEntry : beansByType.entrySet()) {
            Class<?> beanType = beansByTypeEntry.getKey();
            Collection<String> names = new ArrayList<>(beansByTypeEntry.getValue());
            Annotation[] annotations = beanType.getAnnotations();
            for (Annotation annotation : annotations) {
                if (beansByAnnotation.containsKey(annotation.annotationType())) {
                    beansByAnnotation.get(annotation.annotationType()).addAll(names);
                } else {
                    beansByAnnotation.put(annotation.annotationType(), names);
                }
            }
        }
        return beansByAnnotation;
    }
}
