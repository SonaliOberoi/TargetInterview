package com.practice.dp;

//https://leetcode.com/problems/range-sum-query-2d-immutable/
public class MatrixBlockSum {
    public int[][] matrixBlockSum(int[][] mat, int K) {
        if(mat == null || mat.length < 1) {
            return mat;
        }
        int rowLen = mat.length;
        int colLen = mat[0].length;
        if(K >= rowLen && K >= colLen) {
            return mat;
        }
        int[][] dp = new int[rowLen][colLen];
        fillDp(dp, mat);

        int[][] answer = new int[rowLen][colLen];
        for(int i=0;i<rowLen;i++) {
            for(int j=0;j<colLen;j++) {
                int startRow = i - K >= 0 ? i - K : 0;
                int startCol = j - K >= 0 ? j - K : 0;
                int endRow = i + K < rowLen ? i + K : rowLen - 1;
                int endCol = j + K < colLen ? j + K : colLen - 1;
                answer[i][j] = rangeQuery(startRow, startCol, endRow, endCol, dp);
            }
        }
        return answer;
    }

    int rangeQuery(int row1, int col1, int row2, int col2, int[][] dp) {
        return dp[row2][col2] - (col1 - 1 >= 0 ? dp[row2][col1 -1] : 0) - (row1 -1 >= 0 ? dp[row1 -1][col2] : 0) + (col1 - 1 >= 0 && row1 - 1 >=0 ? dp[row1 -1][col1 -1] : 0);
    }

    void fillDp(int[][] dp, int[][] mat) {
        dp[0][0] = mat[0][0];
        for(int i=0;i<mat.length;i++) {
            for(int j=0;j<mat[0].length;j++) {
                if(i==0 && j==0) {
                    continue;
                }
                if(i == 0) {
                    dp[i][j] = dp[i][j - 1] + mat[i][j];

                } else if(j==0) {
                    dp[i][j] = dp[i-1][j] + mat[i][j];

                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j - 1] - dp[i-1][j-1] + mat[i][j];
                }
                // System.out.println("dp:" + dp[i][j]);
            }

        }
    }
}
