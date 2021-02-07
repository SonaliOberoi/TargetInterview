package com.practice.tree;

import java.util.HashMap;
import java.util.*;
import java.util.stream.Collectors;

public class DistanceKNodes {
    Map<Node, List<Node>> map = new HashMap<>();
    public List<Integer> distanceK(Node root, Node target, int K) {
        List<Integer> res = new ArrayList<Integer> ();
        if (root == null || K < 0) return res;
        buildMap(root, null);
        if (!map.containsKey(target)) return res;
        Set<Node> visited = new HashSet<Node>();
        Queue<Node> q = new LinkedList<Node>();
        q.add(target);
        visited.add(target);
        while (!q.isEmpty()) {
            int size = q.size();
            if (K == 0) {
                for (int i = 0; i < size ; i++) res.add(q.poll().element);
                return res;
            }
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                for (Node next : map.get(node)) {
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    q.add(next);
                }
            }
            K--;
        }
        return res;
    }
    private void buildMap(Node node, Node parent) {
        if (node == null) return;
        if (!map.containsKey(node)) {
            map.put(node, new ArrayList<Node>());
            if (parent != null)  { map.get(node).add(parent); map.get(parent).add(node) ; }
            buildMap(node.left, node);
            buildMap(node.right, node);
        }
    }

    public static void main(String args[]) {
        Node root = new Node(null, null, 1);
        root.left = new Node(null, null, 2);
        root.right = new Node(null, null, 3);
        root.left.left = new Node(null, null, 4);
        root.left.left.left = new Node(null, null, 8);
        root.left.right = new Node(null, null, 5);
        root.left.right.left = new Node(null, null, 12);
        root.left.left.right = new Node(null, null, 9);
        root.left.left.right.left = new Node(null, null, 13);
        root.left.left.right.right = new Node(null, null, 14);
        root.left.left.right.right.left = new Node(null, null, 15);
        root.right.right = new Node(null, null, 7);
        root.right.left = new Node(null, null, 6);
        root.right.right.left = new Node(null, null, 10);
        root.right.right.left.right = new Node(null, null, 11);

        DistanceKNodes distanceKNodes = new DistanceKNodes();
        System.out.print(distanceKNodes.distanceK(root, root.left.left, 4));
    }
}
