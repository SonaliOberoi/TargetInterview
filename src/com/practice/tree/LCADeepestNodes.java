package com.practice.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

//https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
public class LCADeepestNodes {
    public Node lcaDeepestLeaves(Node root) {
        if(root == null) {
            return null;
        }

        //level order to find deepest nodes
        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        Node left = null;
        Node right = null;
        while (!(queue.isEmpty())) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                left = queue.getFirst();
                right = queue.getLast();
                Node current = queue.poll();
                if(left != null)queue.offer(current.left);
                if(right != null)queue.offer(current.right);
            }
        }
        if(left == null && right == null) {
            return root;
        } else if(left == null) {
            return right;
        } else if(right == null) {
            return left;
        } else {
            return lca(root, left, right);
        }
    }

    private Node lca(Node root, Node left, Node right) {
        if(root == null) {
            return null;
        }
        if(root == left) {
            return left;
        }
        if(root == right) {
            return right;
        }
        Node leftResult = lca(root.left, left, right);
        Node rightResult = lca(root.right, left, right);

        if(left != null && right != null) {
            return root;
        }
        return null;
    }
}
