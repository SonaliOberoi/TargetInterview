package com.practice.arrays;

import java.util.Arrays;

//https://leetcode.com/problems/find-pivot-index/
public class PivotIndex {
    public int pivotIndex(int[] nums) {
        if(nums == null || nums.length < 1) {
            return -1;
        }
        int sum = Arrays.stream(nums).sum();
        int currSum = 0;
        for(int i=0;i<nums.length;i++) {
            if(currSum == sum - nums[i]) {
                return i;
            }
        }
        return -1;
    }
}
