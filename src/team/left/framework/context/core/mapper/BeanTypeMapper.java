package team.left.framework.context.core.mapper;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface BeanTypeMapper {
    
    Map<String, Class<?>> mapBeanInstanceToName(Set<Class<?>> beanClasses);

    Map<Class<?>, Collection<String>> mapBeansToType(Map<String, Class<?>> beanClassByName);

    Map<Class<? extends Annotation>, Collection<String>> mapBeansToAnnotation(Map<String, Class<?>> beanClassByName);
}
