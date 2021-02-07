package com.practice.disjointsets;

import java.util.*;

public class DisjointSets {
    Map<String, Node> map = new HashMap<>();

    /**
     * Create a set with only one element.
     */
    public void makeSet(String string) {
        Node node = new Node(null, string, 0);
        node.parent = node;
        map.put(string, node);
    }

    /**
     * Combines two sets together to one.
     * Does union by rank
     *
     * @return true if data1 and data2 are in different set before union else false.
     */
    public boolean union(String a, String b) {
        Node node1 = map.get(a);
        Node node2 = map.get(b);

        Node parent1 = findSetUtil(node1);
        Node parent2 = findSetUtil(node2);

        //if they are part of same set do nothing
        if (parent1.data == parent2.data) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;

    }

    /**
     * Finds the representative of this set
     */
    public String findSet(String string) {
        return findSetUtil(map.get(string)).data;
    }
    private Node findSetUtil(Node node) {
        Node parent = node.parent;
        if(parent == node) {
            return node;
        }
        node.parent = findSetUtil(parent);
        return node.parent;
    }
}

class Node {
    Node parent;
    int rank;
    String data;
    Node(Node parent, String data, int rank) {
        this.parent = parent;
        this.data = data;
        this.rank = rank;
    }
}
