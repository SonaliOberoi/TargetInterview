package com.practice.tree;

public class LCA {
    public Node leastCommonAncestor(Node root, Node val1, Node val2) {
        if(root == null) {
            return null;
        }
        if(root == val1 || root == val2) {
            return root;
        }
        Node left = leastCommonAncestor(root.left, val1, val2);
        Node right = leastCommonAncestor(root.right, val1, val2);

        if (left!=null && right!=null) {
            return root;
        }
        return left != null ? left : right;
    }
}
