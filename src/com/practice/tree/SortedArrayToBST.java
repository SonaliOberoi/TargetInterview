package com.practice.tree;

//https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
public class SortedArrayToBST {
    class Node {
        int val;
        Node left,right;
        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    public Node sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length);
    }

    private Node helper(int[] nums, int start, int end) {
        if(start >= end) {
            return null;
        }

        int mid = (start + end) /2;
        Node left = helper(nums, start, mid );
        Node right = helper(nums, mid + 1, end);

        return new Node(nums[mid], left, right);
    }

    public static void main(String args[]) {
        SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
        int[] arr = {-10,-3,0,5,9};
        sortedArrayToBST.sortedArrayToBST(arr);
    }
}
