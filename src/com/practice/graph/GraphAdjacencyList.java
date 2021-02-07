package com.practice.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjacencyList {

    List<Integer> adjList[];
    boolean isDirected;

    GraphAdjacencyList(int numberOfVertices, boolean isDirected) {
        adjList = new ArrayList[numberOfVertices];
        this.isDirected = isDirected;
        for(int i = 0; i < numberOfVertices ; i++){
            adjList[i] = new ArrayList<>();
        }
    }

    public void addEdge(int source, int dest) {
        if(isDirected) {
            addDirectedEdge(source, dest);
        } else {
            addUnDirectedEdge(source, dest);
        }
    }

    private void addDirectedEdge(int source, int dest) {
        adjList[source].add(dest);
    }

    private void addUnDirectedEdge(int source, int dest) {
        adjList[source].add(dest);
        adjList[dest].add(source);
    }

    public List<Integer> getNeighbours(int source) {
        return adjList[source];
    }
}
