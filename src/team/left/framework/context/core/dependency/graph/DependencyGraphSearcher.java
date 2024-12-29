package team.left.framework.context.core.dependency.graph;

import team.left.framework.context.core.graph.ObjectGraph;

public interface DependencyGraphSearcher {

    boolean isTopologicalSort(ObjectGraph objectGraph);
}
