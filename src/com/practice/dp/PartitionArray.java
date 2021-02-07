package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/partition-array-for-maximum-sum/
public class PartitionArray {
    public int maxSumAfterPartitioning(int[] A, int K) {
        int N = A.length, dp[] = new int[N];
        for (int i = 0; i < N; ++i) {
            int curMax = 0;
            for (int k = 1; k <= K && i - k + 1 >= 0; ++k) {
                curMax = Math.max(curMax, A[i - k + 1]);
                dp[i] = Math.max(dp[i], (i >= k ? dp[i - k] : 0) + curMax * k);
            }
        }
        return dp[N - 1];
    }

    public static void main(String args[]) {
        PartitionArray partitionArray = new PartitionArray();
        int[] arr = {1,15,7,9,2,5,10};
        System.out.println(partitionArray.maxSumAfterPartitioning(arr, 4));
    }
}

