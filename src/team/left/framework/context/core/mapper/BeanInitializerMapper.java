package team.left.framework.context.core.mapper;

import java.util.Set;

public interface BeanInitializerMapper {

    BeanInfoWrapper map(Set<Class<?>> beanClasses);
}
