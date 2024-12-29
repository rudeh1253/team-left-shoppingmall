package team.left.framework.context.core.initializer;

import java.util.Collection;
import java.util.Map;

import team.left.framework.context.core.graph.ObjectGraph;
import team.left.framework.context.core.mapper.BeanInfoWrapper;

public interface BeanInitializer {

    Map<String, Object> initInstances(BeanInfoWrapper beanInfoWrapper, ObjectGraph objectGraph, Map<Class<?>, Collection<String>> beansPerType);

    void callInitMethods(Collection<Object> beans);
}
