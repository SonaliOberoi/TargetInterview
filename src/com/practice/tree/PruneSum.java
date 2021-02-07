package com.practice.tree;

//https://www.geeksforgeeks.org/remove-all-nodes-which-lie-on-a-path-having-sum-less-than-k/
public class PruneSum {

    private Node pruneUtil(Node root, int sum, int pathSum) {
        if (root == null) {
            return null;
        }
        pathSum = pathSum + root.element;

        root.left = pruneUtil(root.left, sum, pathSum);
        root.right = pruneUtil(root.right, sum, pathSum);

        if (isLeafNode(root) && pathSum < sum) {
            return null;
        }

        return root;
    }

    public void inOrderTraversal(Node root) {
        if(root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.element);
        inOrderTraversal(root.right);
    }

    private boolean isLeafNode(Node node) {
        if (node == null) {
            return false;
        }

        if (node.right == null && node.left == null) {
            return true;
        } else {
            return false;
        }
    }
    public Node prune(Node root, int sum) {
        return pruneUtil(root, sum, 0);
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

        PruneSum pruneSum = new PruneSum();
        pruneSum.inOrderTraversal(pruneSum.prune(root, 90));
    }
}
