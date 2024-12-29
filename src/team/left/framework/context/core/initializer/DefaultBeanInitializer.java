package team.left.framework.context.core.initializer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import team.left.framework.context.core.graph.ObjectGraph;
import team.left.framework.context.core.mapper.BeanInfoWrapper;
import team.left.framework.context.core.mapper.BeanInstantiatable;

public class DefaultBeanInitializer implements BeanInitializer {

    @Override
    public Map<String, Object> initInstances(BeanInfoWrapper beanInfoWrapper, ObjectGraph objectGraph,
            Map<Class<?>, Collection<String>> beansPerType) {
        Map<String, Object> instanceByName = new HashMap<>();
        for (String beanName : beanInfoWrapper.getTypePerBean().keySet()) {
            initByDfs(beanName, instanceByName, beanInfoWrapper, objectGraph, beansPerType);
        }
        return instanceByName;
    }

    private void initByDfs(String vertex, Map<String, Object> instanceByName, BeanInfoWrapper beanInfoWrapper,
            ObjectGraph objectGraph, Map<Class<?>, Collection<String>> beansPerType) {
        if (instanceByName.containsKey(vertex)) {
            return;
        }

        Set<String> dependencies = objectGraph.getDependenciesOfBean(vertex);
        for (String dependency : dependencies) {
            initByDfs(dependency, instanceByName, beanInfoWrapper, objectGraph, beansPerType);
        }

        BeanInstantiatable instantiator = beanInfoWrapper.getInstantiatorByBeanName(vertex);
        Object instance = instantiator.instantiate(beansPerType, instanceByName);
        instanceByName.put(vertex, instance);
    }

    @Override
    public void callInitMethods(Collection<Object> beans) {
        for (Object bean : beans) {
            Class<?> beanType = bean.getClass();
            Arrays.stream(beanType.getDeclaredMethods())
                    .filter((method) -> method.isAnnotationPresent(PostConstruct.class)).forEach((method) -> {
                        try {
                            method.invoke(bean);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}
