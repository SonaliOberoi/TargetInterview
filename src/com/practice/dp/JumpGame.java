package com.practice.dp;

//https://leetcode.com/problems/jump-game-ii/
public class JumpGame {
    public int jump(int[] nums) {
        if(nums == null || nums.length < 1) {
            return -1;
        }
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 0;
        for(int i=1;i<length;i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i=0;i<length;i++) {
            for(int j=i+1;j <= i + nums[i] && j < length;j++) {
                dp[j] = Math.min(dp[i] + 1, dp[j]);
            }
        }

        return dp[length -1];
    }

    public static void main(String args[]) {
        JumpGame jumpGame = new JumpGame();
        int[] arr = {2,3,1,1,4};
        jumpGame.jump(arr);
    }
}
