package com.practice.tree;

//https://leetcode.com/problems/binary-tree-pruning/
public class PruneTree {
    public Node pruneTree(Node root) {
        if(root == null) {
            return null;
        }
        contains0(root);
        return root;
    }
    private boolean contains0(Node root) {
        if(root == null) {
            return true;
        }
        boolean left = contains0(root.left);
        boolean right = contains0(root.right);
        if(left) {
            root.left = null;
        }
        if(right) {
            root.right = null;
        }
        if(left || right || root.element == 0) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        PruneTree pruneTree = new PruneTree();
        Node root = new Node(null, null, 1);
        root.right = new Node(null, null, 0);
        root.right.left = new Node(null, null, 0);
        root.right.right = new Node(null, null, 1);
        pruneTree.pruneTree(root);
    }
}
