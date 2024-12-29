package team.left.framework.context.core.mapper;

import java.util.Collection;
import java.util.Map;

@FunctionalInterface
public interface BeanInstantiatable {

    Object instantiate(Map<Class<?>, Collection<String>> beansPerType,
            Map<String, Object> instancePerBeanName);
}
