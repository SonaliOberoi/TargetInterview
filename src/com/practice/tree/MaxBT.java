package com.practice.tree;

//https://leetcode.com/problems/maximum-binary-tree/
class MaxBT {
    public Node constructMaximumBinaryTree(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        return helper(nums, 0, nums.length);
    }
    public Node helper(int[] nums, int start, int end) {
        if(start == end) {
            return null;
        }
        int indexMax = maxElementIndex(nums, start, end);

        Node node = new Node(null, null, nums[indexMax]);;
        node.left = helper(nums, start, indexMax);
        node.right = helper(nums, indexMax + 1, end);
        return node;
    }
    public int maxElementIndex(int[] nums, int start, int end) {
        int max = nums[start];
        int index = start;
        for(int i = 1; i< end;i++) {
            if(nums[i]> max) {
                max = nums[i];
                index = i;
            }
        }
        return index;
    }

    public static void main(String args[]) {
        MaxBT maxBT = new MaxBT();
        int[] arr = {3,2,1,6,0,5};
        maxBT.constructMaximumBinaryTree(arr);
    }
}