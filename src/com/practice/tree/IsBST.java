package com.practice.tree;

public class IsBST {

    public boolean isBST(Node root) {
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBSTUtil(Node root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.element <= min || root.element > max) {
            return false;
        }

        return isBSTUtil(root.left, min, root.element) && isBSTUtil(root.right, root.element, max);
    }

    public int height (Node node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(height(node.left), height((node.right)));
    }

    public boolean isChildSumProperty(Node root) {
        if (root == null) {
            return true;
        }

        boolean left = isChildSumProperty(root.left);
        boolean right = isChildSumProperty(root.right);

        if (left && right && (root.element == root.left.element + root.right.element)) {
            return true;
        }
        return false;
    }

    public static void main(String args[]) {
        Node root = new Node(null, null, 40);
        root.left = new Node(null, null, 20);
        root.right = new Node(null, null, 60);
        root.left.left = new Node(null, null, 10);
        root.left.right = new Node(null, null, 30);
        root.right.left = new Node(null, null, 50);
        root.right.right = new Node(null, null, 80);
        root.right.right.left = new Node(null, null, 70);

        IsBST isBST = new IsBST();
        System.out.println(isBST.isBST(root));

        //Node tree = new Node();
        Node root2 = new Node(null, null, 10);
        root2.left = new Node(null, null,8);
        root2.right = new Node(null, null,2);
        root2.left.left = new Node(null, null,3);
        root2.left.right = new Node(null, null,5);
        root2.right.right = new Node(null, null,2);
    }
}
