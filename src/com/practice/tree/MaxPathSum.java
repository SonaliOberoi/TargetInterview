package com.practice.tree;

//https://leetcode.com/problems/binary-tree-maximum-path-sum/
public class MaxPathSum {
    public int maxPathSum(Node root) {
        int max[] = new int[1];
        max[0] = Integer.MIN_VALUE;
        calculateSum(root, max);
        return max[0];
    }

    public int calculateSum(Node root, int[] max) {
        if (root == null)
            return 0;

        int left = calculateSum(root.left, max);
        int right = calculateSum(root.right, max);

        int current = Math.max(root.element, Math.max(root.element + left, root.element + right));

        max[0] = Math.max(max[0], Math.max(current, left + root.element + right));

        return current;
    }
    public static void main(String args[]) {
        /**
         *       -5
         *      / \
         *    - 4   8
         *    /   / \
         *   11  -13  4
         *  /  \    / \
         * -7    2  5   1
         * / \
         * -10 3
         *
         */
        Node root = new Node(null, null, -5);
        root.left = new Node(null, null, -4);
        root.right = new Node(null, null, 8);
        root.left.left = new Node(null, null, 11);
        root.left.left.left = new Node(null, null, 97);
        root.left.left.left.left = new Node(null, null, 100);
        root.left.left.left.right = new Node(null, null, 2000);
        root.left.left.right = new Node(null, null, 2);
        root.right.left = new Node(null, null, -13);
        root.right.right = new Node(null, null, 4);
        root.right.right.left = new Node(null, null, 5);
        root.right.right.right = new Node(null, null, 1);

        MaxPathSum maxPathSum = new MaxPathSum();
        System.out.print(maxPathSum.maxPathSum(root));
    }
}
