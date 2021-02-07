package com.practice.tree;

public class Diameter {

    int dia = 0;
    public int diameter(Node root) {
        if(root == null) {
            return 0;
        }

        int leftHeight = diameter(root.left);
        int rightHeight = diameter(root.right);

        dia = Math.max(dia, leftHeight + rightHeight + 1);

        return Math.max(leftHeight, rightHeight ) + 1;
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

        Diameter diameter = new Diameter();
        System.out.println(diameter.diameter(root));
        System.out.print(diameter.dia);
    }
}
