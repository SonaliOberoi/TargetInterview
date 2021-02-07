package com.practice.graph;

public class CycleInUndirectedGraph {
    private boolean dfsUtil(boolean[] visited, GraphAdjacencyList graph, int parent, int current) {
        visited[current] = true;

        for (Integer neighbor: graph.getNeighbours(current)) {
            if (neighbor.equals(parent)) {
                continue;
            }

            if (visited[neighbor]) {
                return true;
            }
            if (dfsUtil(visited, graph, current, neighbor)) {
                return true;
            }
        }
            return false;
    }

    public boolean hasCycle(GraphAdjacencyList graph, int start, int numberOfVertices) {
        boolean[] visited = new boolean[numberOfVertices];
        while(numberOfVertices > 0) {
            if (visited[numberOfVertices - 1]) {
                numberOfVertices = numberOfVertices - 1;
                continue;
            }
            return dfsUtil(visited, graph, start, start);
        }
        return false;
    }

   /* public static void main(String args[]){
       /* GraphAdjacencyList graph = new GraphAdjacencyList(6, false);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        graph.addEdge(0, 5);

        CycleInUndirectedGraph cycleInUndirectedGraph = new CycleInUndirectedGraph();
        System.out.print(cycleInUndirectedGraph.hasCycle(graph, 0, 6));
    }*/
}
