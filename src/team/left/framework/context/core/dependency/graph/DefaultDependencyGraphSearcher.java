package team.left.framework.context.core.dependency.graph;

import java.util.HashSet;
import java.util.Set;

import team.left.framework.context.core.graph.ObjectGraph;

public class DefaultDependencyGraphSearcher implements DependencyGraphSearcher {

    public DefaultDependencyGraphSearcher() {
    }

    @Override
    public boolean isTopologicalSort(ObjectGraph objectGraph) {
        Set<String> verticesNotVisited = new HashSet<>(objectGraph.keySet());
        while (!verticesNotVisited.isEmpty()) {
            String vertexNotVisited = verticesNotVisited.iterator().next();
            if (doesCycleExists(vertexNotVisited, verticesNotVisited, new HashSet<>(), objectGraph)) {
                return false;
            }
        }
        return true;
    }

    private boolean doesCycleExists(String vertex,
                                    Set<String> verticesNotVisited,
                                    Set<String> visitationOfCurrentDfs,
                                    ObjectGraph objectGraph) {
        visitationOfCurrentDfs.add(vertex);
        if (!verticesNotVisited.contains(vertex)) {
            return false;
        }
        verticesNotVisited.remove(vertex);
        Set<String> nextVertices = objectGraph.getDependenciesOfBean(vertex);
        

        boolean result = false;
        for (String nextVertex : nextVertices) {
            if (visitationOfCurrentDfs.contains(nextVertex)) {
                return true;
            }
            result = result || doesCycleExists(nextVertex,
                    verticesNotVisited,
                    visitationOfCurrentDfs,
                    objectGraph);
        }
        return result;
    }
}
