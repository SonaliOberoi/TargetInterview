package com.practice.tree;

//https://leetcode.com/problems/binary-tree-cameras/submissions/
public class BinaryTreeCamera {
    private int NOT_MONITORED = 0;
    private int MONITORED_NOCAM = 1;
    private int MONITORED_WITHCAM = 2;
    private int cameras = 0;

    public int minCameraCover(Node root) {
        if (root == null) return 0;
        int top = dfs(root);
        return cameras + (top == NOT_MONITORED ? 1 : 0);
    }

    private int dfs(Node root) {
        if (root == null) return MONITORED_NOCAM;
        int left = dfs(root.left);
        int right = dfs(root.right);
        if (left == MONITORED_NOCAM && right == MONITORED_NOCAM) {
            return NOT_MONITORED;
        } else if (left == NOT_MONITORED || right == NOT_MONITORED) {
            cameras++;
            return MONITORED_WITHCAM;
        } else {
            return MONITORED_NOCAM;
        }
    }
}
