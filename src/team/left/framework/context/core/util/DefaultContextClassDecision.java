package team.left.framework.context.core.util;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import team.left.framework.context.annotation.Component;

public class DefaultContextClassDecision implements ContextClassDecision {
    
    @Override
    public boolean isBeanToBeIncludedInContext(Class<?> clazz) {
        return doesAnnotationExists(clazz, Component.class);
    }
    
    @Override
    public boolean doesAnnotationExists(Class<?> targetClass, Class<? extends Annotation> annotationType) {
        return isAnnotationExists(targetClass, annotationType, new HashSet<>());
    }
    
    private boolean isAnnotationExists(Class<?> targetAnnotationType, Class<? extends Annotation> toCheck, Set<Class<?>> visited) {
        if (targetAnnotationType.isAnnotationPresent(toCheck)) {
            return true;
        }
        if (visited.contains(targetAnnotationType)) {
            return false;
        }
        visited.add(targetAnnotationType);
        Annotation[] annotations = targetAnnotationType.getAnnotations();
        boolean result = false;
        for (Annotation annotation : annotations) {
            result = result || isAnnotationExists(annotation.annotationType(), toCheck, visited);
        }
        return result;
    }

    @Override
    public String getBeanName(Class<?> clazz) {
        String beanName = clazz.getAnnotation(Component.class).name();
        return beanName.isEmpty()
                ? NameUtils.generateDefaultBeanName(clazz)
                : beanName;
    }
}
