package com.practice.disjointsets;

import java.util.*;

public class UnionFind {
    Map<String, Node> map = new HashMap<>();


    public boolean union(String a, String b) {
        Node parent1 = findUtil(a);
        Node parent2 = findUtil(b);

        if (parent1 == parent2) {
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

    public String find(String a) {
        return map.containsKey(a) ? map.get(a).data : "";
    }

    private Node findUtil(String a) {
        Node node = map.get(a);

        if (node == node.parent) {
            return node;
        }

        node.parent = findUtil(node.parent.data);
        return node.parent;
    }

    class Node {
        String data;
        Node parent;
        int rank;
        Node(String data, Node parent, int rank) {
            this.data = data;
            this.parent = parent;
            this.rank = rank;
        }
    }
}
