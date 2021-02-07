package com.practice.disjointsets;

import java.util.*;

//https://leetcode.com/problems/redundant-connection/
public class RedundantConnections {

    Map<Integer, Node> nodeMap = new HashMap<>();

    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 1) {
            return null;
        }
        for(int[] current: edges) {
            if(!union(current[0], current[1])) {
                return current;
            }
        }
        return null;
    }


    private boolean union(int a, int b) {
        Node parent1 = find(a);
        Node parent2 = find(b);
        if(parent1 == parent2) {
            return false;
        }
         parent1.parent = parent2;
        return true;
    }

    private Node find(int a) {
        if(!nodeMap.containsKey(a)) {
           Node node = new Node(a, null);
           node.parent = node;
            nodeMap.put(a, node);
            return node;
        }
        Node current = nodeMap.get(a);
        if(current == current.parent) {
            return current;
        }
        current.parent = find(current.parent.data);
        return current.parent;

    }
    class Node {
        int data;
        Node parent;
        Node(int data, Node parent) {
            this.data = data;
            this.parent = parent;
        }
    }
}
