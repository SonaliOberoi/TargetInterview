package com.practice.arrays;

import java.util.PriorityQueue;

//https://leetcode.com/problems/maximum-product-subarray/
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int negatives = 0;
        int zeros = 0;
        for(int i = 0;i<nums.length;i++) {
            if(nums[i] < 0) {
                negatives++;
            } else if(nums[i] == 0) {
                zeros++;
            }
        }
        if(zeros == 0) {
            if(negatives == 0) {
                int answer = 1;
                for(int i = 0;i<nums.length;i++) {
                    answer = answer * nums[i];
                }
                return answer;
            } else {
                return maxUtil(0, nums.length - 1, nums);
            }
        } else {
            int answer = 1;
            int start = 0;
            for(int i = 0;i<nums.length;i++) {
                if(i!= 0 && i != nums.length - 1 && nums[i] == 0) {
                    int max = Math.max(answer, maxUtil(start, i -1, nums));
                    start = i + 1;
                }
            }
        }
        return -1;

    }

    private int maxUtil(int start, int end, int[] nums) {
        int negatives = 0;
        for(int i = start;i<=end;i++) {
            if(nums[i] < 0) {
                negatives++;
            }
        }
        int answer = 1;
        if(negatives%2 == 0) {
            for(int i = start;i<=end;i++) {
                answer = answer * nums[i];
            }
            return answer;
        } else {
            int[] numsCopy = new int[nums.length];
            int j = 0;
            for(int i = start;i<=end;i++) {
                if(nums[i] > 0) {
                    numsCopy[j] = numsCopy[j] * nums[i];
                } else {
                    numsCopy[j++] = nums[i];
                    j++;
                }
            }
            for(int k = 0;k<numsCopy.length;k++) {

            }
        }
        return -1;
    }
}
