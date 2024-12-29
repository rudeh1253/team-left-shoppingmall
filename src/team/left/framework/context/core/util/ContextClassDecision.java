package team.left.framework.context.core.util;

import java.lang.annotation.Annotation;

/**
 * 클래스 정보를 바탕으로 해당 객체가 Context에 의해 관리되어야 할 객체인지
 * 결정하는 객체
 */
public interface ContextClassDecision {
    
    boolean isBeanToBeIncludedInContext(Class<?> clazz);
    
    boolean doesAnnotationExists(Class<?> targetClass, Class<? extends Annotation> annotationType);

    public String getBeanName(Class<?> clazz);
}
