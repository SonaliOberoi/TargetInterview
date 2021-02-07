package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/critical-connections-in-a-network/
public class CriticalConnections {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(List<Integer> list: connections) {
            int node1 = list.get(0);
            int node2 = list.get(1);
            //node1->node2
            if(graph.containsKey(node1)) {
                List<Integer> temp = graph.get(node1);
                temp.add(node2);
                graph.put(node1, temp);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(node2);
                graph.put(node1, temp);
            }
            //node2->node1
            if(graph.containsKey(node2)) {
                List<Integer> temp = graph.get(node2);
                temp.add(node1);
                graph.put(node2, temp);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(node1);
                graph.put(node2, temp);
            }
        }

        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> cycles = new ArrayList<>();

        // arrays required to color the
        // graph, store the parent of node
        int[] color = new int[n];
        int[] par = new int[n];

        // mark with unique numbers
        int[] mark = new int[n];

        return Collections.EMPTY_LIST;
    }

    public boolean dfs(Map<Integer, List<Integer>> graph, int parent, Set<Integer> visited, int current) {
        visited.add(current);
        for(Integer neighbour: graph.get(current)) {
            if (visited.contains(neighbour)) {
                return true;
            }
            if(dfs(graph, current,visited, neighbour)) {
                return true;
            }
        }
        return false;
    }
}
