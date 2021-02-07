package com.practice.graph;

import java.util.*;

public class GraphMatrix {
    int[][] graphMatrix;
    Map<Integer, List<Integer>> neighbours;
    boolean isDirected;

    public GraphMatrix(int[][] graphMatrix, boolean isDirected) {
        this.graphMatrix = graphMatrix;
        neighbours = new HashMap<>();
        this.isDirected = isDirected;

    }

    public void addEdge(int source, int destination) {
        if (isDirected) {
            addDirectedEdge(source, destination);
        } else {
            addUndirectedEdge(source, destination);
        }
    }
    private void addDirectedEdge(int source, int destination) {
        graphMatrix[source][destination] = 1;
        if (neighbours.containsKey(source)) {
            neighbours.get(source).add(destination);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(destination);
            neighbours.put(source, list);
        }
    }
    private void addUndirectedEdge(int source, int destination) {
        graphMatrix[source][destination] = 1;
        graphMatrix[destination][source] = 1;
        if (neighbours.containsKey(source)) {
            neighbours.get(source).add(destination);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(destination);
            neighbours.put(source, list);
        }
        if (neighbours.containsKey(destination)) {
            neighbours.get(destination).add(source);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(source);
            neighbours.put(destination, list);
        }
    }

    public List<Integer> getNeighbours(int source) {
        return neighbours.getOrDefault(source, Collections.emptyList());
    }


}
