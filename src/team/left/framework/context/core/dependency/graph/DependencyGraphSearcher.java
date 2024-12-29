package team.left.framework.context.core.dependency.graph;

import java.util.Collection;
import java.util.Map;

public interface DependencyGraphSearcher {

    boolean isTopologicalSort(Map<String, Class<?>> beanTypeByBeanName,
                              Map<Class<?>, Collection<String>> beanNamesByBeanType);
}
