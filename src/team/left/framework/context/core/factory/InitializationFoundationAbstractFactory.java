package team.left.framework.context.core.factory;

import team.left.framework.context.core.classloader.BeanClassLoader;
import team.left.framework.context.core.dependency.DependencyFinder;
import team.left.framework.context.core.dependency.graph.DependencyGraphSearcher;
import team.left.framework.context.core.initializer.BeanInitializer;
import team.left.framework.context.core.mapper.BeanInitializerMapper;
import team.left.framework.context.core.mapper.BeanTypeMapper;

public interface InitializationFoundationAbstractFactory {
    
    BeanClassLoader getBeanClassFinder();

    BeanTypeMapper getBeanTypeMapper();
    
    BeanInitializerMapper getBeanInitializerMapper();

    BeanInitializer getBeanInitializer();

    DependencyGraphSearcher getDependencyGraphSearcher();
    
    DependencyFinder getDependencyFinder();
}
