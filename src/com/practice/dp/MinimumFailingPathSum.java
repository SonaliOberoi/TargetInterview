package com.practice.dp;

//https://leetcode.com/problems/minimum-falling-path-sum/
public class MinimumFailingPathSum {
    public static int minFallingPathSum(int[][] A) {
        int min = Integer.MAX_VALUE;
        int[][] dp = new int[A.length][A[0].length];
        for(int col = 0; col < A[0].length; col++) {
            dp[0][col] = A[0][col];
        }

        for (int row = 1; row < A.length; row++) {
            for (int col = 0; col < A[0].length; col++) {
                int leftUp = (row - 1 >= 0 && col - 1 >= 0) ? dp[row - 1][col - 1] : Integer.MAX_VALUE;
                int up = (row - 1 >= 0) ? dp[row - 1][col] : Integer.MAX_VALUE;
                int rightUp = (row - 1 >= 0 && col + 1 < A[0].length) ? dp[row - 1][col + 1] : Integer.MAX_VALUE;
                dp[row][col] = Math.min(Math.min(leftUp, up), rightUp) + A[row][col];
            }
        }

        for(int col = 0; col < A[0].length; col++) {
            min = Math.min(dp[A.length - 1 ][col], min);
        }
        return min;
    }

    public static void main (String[] args) {
        int A[][] = { { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 } };

        // function to print required answer
        System.out.println( minFallingPathSum(A));
    }
}

