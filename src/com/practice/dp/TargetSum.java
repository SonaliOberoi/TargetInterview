package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/target-sum/
public class TargetSum {
    public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length < 1) {
            return 0;
        }
        int total = Arrays.stream(nums).sum();

        if (S > total) {
            return 0;
        }
        int target = (S + total) % 2 == 0 ? (S + total) /2 : -1;

        if(target < 0) {
            return 0;
        }

        int[][] dp = new int[nums.length + 1][target + 1];
        dp[0][0] = 1;
        for (int i =1;i<target;i++) {
            dp[0][i] = 0;
        }
        for(int i=1;i<nums.length + 1;i++) {
            dp[i][0] = 1;
        }
        for(int i = 1;i<nums.length + 1;i++) {

            for(int j=1;j<target + 1;j++) {

                if (nums[i - 1] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {

                    dp[i][j] = dp[i-1][j] + dp[i-1][j - nums[i-1]];
                }

            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
