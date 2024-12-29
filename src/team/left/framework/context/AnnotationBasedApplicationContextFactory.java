package team.left.framework.context;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import team.left.framework.context.annotation.ComponentScan;
import team.left.framework.context.core.classloader.BeanClassLoader;
import team.left.framework.context.core.dependency.DependencyFinder;
import team.left.framework.context.core.dependency.graph.DependencyGraphSearcher;
import team.left.framework.context.core.factory.DefaultInitializationFoundationAbstractFactory;
import team.left.framework.context.core.factory.InitializationFoundationAbstractFactory;
import team.left.framework.context.core.graph.ObjectGraph;
import team.left.framework.context.core.initializer.BeanInitializer;
import team.left.framework.context.core.mapper.BeanInfoWrapper;
import team.left.framework.context.core.mapper.BeanInitializerMapper;
import team.left.framework.context.core.mapper.BeanTypeMapper;
import team.left.framework.context.exception.AssociationCyclePresenceException;
import team.left.framework.context.exception.InvalidMetadataException;

public class AnnotationBasedApplicationContextFactory {
    private static final InitializationFoundationAbstractFactory initFactory = new DefaultInitializationFoundationAbstractFactory();
    
    private AnnotationBasedApplicationContextFactory() {
    }
    
    public static ApplicationContext getApplicationContext(Class<?> componentScanClass, Map<String, Object> additionalBeansByName) {
        return null; // TODO
    }

    public static ApplicationContext getApplicationContext(Class<?> componentScanClass) {
        String basePackage = getBasePackage(componentScanClass);

        // Pipeline
        BeanClassLoader beanClassFinder = initFactory.getBeanClassFinder();
        BeanTypeMapper beanTypeMapper = initFactory.getBeanTypeMapper();
        BeanInitializerMapper beanInitializerMapper = initFactory.getBeanInitializerMapper();
        BeanInitializer beanInitializer = initFactory.getBeanInitializer();
        DependencyGraphSearcher dependencyGraphSearcher = initFactory.getDependencyGraphSearcher();
        DependencyFinder dependencyFinder = initFactory.getDependencyFinder();

        Set<Class<?>> beanClasses = beanClassFinder.findAllOnBasePackage(basePackage);
        BeanInfoWrapper beanInfoWrapper = beanInitializerMapper.map(beanClasses);
        Map<Class<?>, Collection<String>> beansPerType = beanTypeMapper.mapBeansToType(beanInfoWrapper.getTypePerBean());
        ObjectGraph objectGraph = ObjectGraph.makeObjectGraph(beanInfoWrapper, beansPerType, dependencyFinder);

        if (!dependencyGraphSearcher.isTopologicalSort(objectGraph)) {
            throw new AssociationCyclePresenceException("순환 참조가 존재합니다");
        }

        Map<String, Object> beanByName = beanInitializer.initInstances(beanInfoWrapper, objectGraph, beansPerType);
        Map<Class<? extends Annotation>, Collection<String>> beansByAnnotation =
                beanTypeMapper.mapBeansToAnnotation(beanInfoWrapper.getTypePerBean());
        beanInitializer.callInitMethods(beanByName.values());

        return new AnnotationBasedApplicationContext(beanByName, beansPerType, beansByAnnotation);
    }

    private static String getBasePackage(Class<?> componentScanClass) {
        ComponentScan componentScanAnnotation = componentScanClass.getAnnotation(ComponentScan.class);
        if (componentScanAnnotation == null) {
            throw new InvalidMetadataException("ComponentScan 클래스가 아닙니다: " + componentScanClass);
        }

        return componentScanAnnotation.basePackage();
    }
}
