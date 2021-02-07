package com.practice.dp;

import com.practice.tree.Node;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/house-robber-iii/
public class HouseRobber3 {
    Node root;
    public class Node {
        Node left;
        Node right;
        int x;
        Node(Node left, Node right, int x) {
            this.left = left;
            this.right = right;
            this.x = x;
        }
    }
    public int rob() {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(Node root) {
        if (root == null) return new int[2];

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.x + left[0] + right[0];

        return res;
    }
    private void initializeTree() {
        this.root = new Node(null, null, 50);
        root.left = new Node(null, null, 8);
        root.right = new Node(null, null, 31);
        root.left.left = new Node(null, null, 3);
        root.left.right = new Node(null, null, 5);
        root.right.left = new Node(null, null, 1);
        root.right.right = new Node(null, null, 30);
        root.right.right.left = new Node(null, null, 70);

    }

    public static void main(String args[]) {
       HouseRobber3 houseRobber3 = new HouseRobber3();
       houseRobber3.initializeTree();
       houseRobber3.rob();
    }
}


