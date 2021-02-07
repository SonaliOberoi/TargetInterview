package com.practice.graph;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
public class NextPointer {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        Queue<Node> queue = new LinkedList<>();

        ((LinkedList<Node>) queue).add(root);

        while (!queue.isEmpty()) {
            int currentQueueSize = queue.size();
            for (int i =0 ;i<currentQueueSize;i++) {
                Node current = queue.poll();
                if (!queue.isEmpty() && i != currentQueueSize -1) {
                    current.next = queue.peek();
                } else {
                    current.next = null;
                }
                if (current.left != null ) {
                    ((LinkedList<Node>) queue).add(current.left);
                }
                if (current.right != null ) {
                    ((LinkedList<Node>) queue).add(current.right);
                }
            }
        }
        return root;
    }
}
