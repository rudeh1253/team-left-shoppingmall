package team.left.framework.context.core.factory;

import team.left.framework.context.core.classloader.BeanClassLoader;
import team.left.framework.context.core.classloader.DefaultBeanClassLoader;
import team.left.framework.context.core.dependency.DefaultDependencyFinder;
import team.left.framework.context.core.dependency.DependencyFinder;
import team.left.framework.context.core.dependency.graph.DefaultDependencyGraphSearcher;
import team.left.framework.context.core.dependency.graph.DependencyGraphSearcher;
import team.left.framework.context.core.initializer.BeanInitializer;
import team.left.framework.context.core.initializer.DefaultBeanInitializer;
import team.left.framework.context.core.mapper.BeanInitializerMapper;
import team.left.framework.context.core.mapper.BeanTypeMapper;
import team.left.framework.context.core.mapper.DefaultBeanInitializerMapper;
import team.left.framework.context.core.mapper.DefaultBeanTypeMapper;
import team.left.framework.context.core.util.ContextClassDecision;
import team.left.framework.context.core.util.DefaultContextClassDecision;

public class DefaultInitializationFoundationAbstractFactory implements InitializationFoundationAbstractFactory {
    private static final ContextClassDecision CONTEXT_CLASS_DECISION = new DefaultContextClassDecision();
    private static final BeanClassLoader BEAN_CLASS_FINDER = new DefaultBeanClassLoader(CONTEXT_CLASS_DECISION);
    private static final DependencyFinder DEPENDENCY_FINDER = new DefaultDependencyFinder();
    private static final BeanInitializerMapper BEAN_INITIALIZER_MAPPER = new DefaultBeanInitializerMapper(DEPENDENCY_FINDER);
    private static final BeanTypeMapper BEAN_TYPE_MAPPER = new DefaultBeanTypeMapper(CONTEXT_CLASS_DECISION);
    private static final BeanInitializer BEAN_INITIALIZER = new DefaultBeanInitializer();
    private static final DependencyGraphSearcher DEPENDENCY_GRAPH_SEARCHER =
            new DefaultDependencyGraphSearcher(DEPENDENCY_FINDER);

    @Override
    public BeanClassLoader getBeanClassFinder() {
        return BEAN_CLASS_FINDER;
    }

    @Override
    public BeanTypeMapper getBeanTypeMapper() {
        return BEAN_TYPE_MAPPER;
    }
    
    @Override
    public BeanInitializerMapper getBeanInitializerMapper() {
        return BEAN_INITIALIZER_MAPPER;
    }

    @Override
    public BeanInitializer getBeanInitializer() {
        return BEAN_INITIALIZER;
    }

    @Override
    public DependencyGraphSearcher getDependencyGraphSearcher() {
        return DEPENDENCY_GRAPH_SEARCHER;
    }
    
    @Override
    public DependencyFinder getDependencyFinder() {
        return DEPENDENCY_FINDER;
    }
}
