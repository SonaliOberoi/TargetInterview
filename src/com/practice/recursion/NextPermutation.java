package com.practice.recursion;

import java.util.Arrays;
import java.util.Collections;

//https://leetcode.com/problems/next-permutation/
public class NextPermutation {
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }
        if(nums.length == 1) {
            System.out.println(nums);
        }
        int size = nums.length;
        int prev = nums[size - 1];
        for(int i = size -2 ;i>=0;i--) {
            if(prev > nums[i]) {
                int temp = nums[i];
                nums[i] = nums[size - 1];
                nums[size - 1] = temp;
                System.out.println(nums);
                return;
            }
        }
        Collections.reverse(Arrays.asList(nums));
        return;
    }

    public static void main(String args[]) {
        int[] arr = {1,2,3};
        nextPermutation(arr);
    }
}
