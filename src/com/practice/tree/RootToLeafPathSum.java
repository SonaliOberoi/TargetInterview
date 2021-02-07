package com.practice.tree;

//https://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/
public class RootToLeafPathSum {

    boolean pathUtil(Node node, int sum) {
        if (node == null) {
            return false;
        }

        if (node.left == null && node.right == null && sum == node.element) {
            return true;
        }
        boolean left = pathUtil(node.left, sum - node.element);
        boolean right = pathUtil(node.right, sum - node.element);

        if (left || right) {
            return true;
        }

        return false;
    }

    public static void main(String args[]) {
        /* Constructed binary tree is
              10
             /  \
           8     2
          / \   /
         3   5 2
        */

        Node root = new Node(null, null, 10);
        root.left = new Node(null, null, 8);
        root.right = new Node(null, null, 2);
        root.left.left = new Node(null, null,3);
        root.left.right = new Node(null, null,5);
        root.right.left = new Node(null, null,2);

        RootToLeafPathSum rootToLeafPathSum = new RootToLeafPathSum();
        System.out.println(rootToLeafPathSum.pathUtil(root, 15));
    }

}
