package com.practice.graph;

import java.util.*;

//https://www.geeksforgeeks.org/find-if-there-is-a-path-of-more-than-k-length-from-a-source/
public class FindPath {

    boolean hasPath(Graph graph, int total, int start) {
        List pathList = new ArrayList();
        if (hasPathUtil(graph, total, new HashSet(), (Vertex) graph.getAllVertex().toArray()[0], pathList)) {
            for(int i =0; i<pathList.size(); i++) {
                System.out.println(pathList.get(i).toString());
            }
            return true;
        }
        for(int i =0; i<pathList.size(); i++) {
            System.out.println(pathList.get(i).toString());
        }
        return false;
    }

    boolean hasPathUtil(Graph graph, int total, Set visited, Vertex current, List<Vertex> pathArrayList) {
        if (total <= 0) {
            return true;
        }

        visited.add(current);
        pathArrayList.add(current);

        for (Vertex neighbor: (List<Vertex>)current.getAdjacentVertexes()) {
            if (!visited.contains(neighbor)) {
                total = total - graph.getWeight(current, neighbor);
                if(hasPathUtil(graph, total, visited, neighbor, pathArrayList)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String args[]) {
        Graph graph = new Graph(false);
        graph.addEdge(0,1,4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2,8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3,7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5 , 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        FindPath findPath = new FindPath();
        System.out.println(findPath.hasPath(graph, 48, 0));
    }
}
