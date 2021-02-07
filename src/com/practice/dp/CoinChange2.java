package com.practice.dp;

import java.util.Arrays;

//https://leetcode.com/problems/coin-change-2/
public class CoinChange2 {
    public int change(int amount, int[] coins) {
        if (coins == null || coins.length < 1) {
            return amount < 1 ? 1 : 0;
        }

        Arrays.sort(coins);
        int length = coins.length;
        int[][] dp = new int[length + 1][amount + 1];
        dp[0][0] = 1;
        for (int i =1;i<amount + 1;i++) {
            dp[0][i] = 0;
        }
        for (int i =1;i<length + 1;i++) {
            dp[i][0] = 1;
        }
        for(int i=1;i<length + 1;i++) {
            for(int j=1;j<amount+1;j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i - 1]];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    public static void main(String args[]) {
        CoinChange2 coinChange2 = new CoinChange2();
        int[] arr = {1,2,5};
        coinChange2.change(5,arr);
    }

}
