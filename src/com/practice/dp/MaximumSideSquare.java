package com.practice.dp;

import com.sun.source.tree.Tree;

import java.util.NavigableMap;
import java.util.TreeMap;

//https://leetcode.com/problems/maximum-side-length-of-a-square-with-sum-less-than-or-equal-to-threshold/
public class MaximumSideSquare {
    public int maxSideLength(int[][] mat, int threshold) {

        int rowLen = mat.length;
        int colLen = mat[0].length;
        int maxSquareLen = Math.min(rowLen , colLen);
        int answer = 0;
        for(int L=0;L < colLen - 1;L++) {
            int[] tempArr = new int[rowLen];
            for(int R=L;R<colLen - 1 && L - R <= maxSquareLen;R ++) {
                for(int row =0;row<rowLen;row++) {
                    tempArr[row] = tempArr[row] + mat[row][R];
                }
                answer = Math.max(answer, maxSubArraySum(tempArr, maxSquareLen, threshold));
            }
        }
        return answer;

    }
    private int maxSubArraySum(int[] arr, int maxLength, int threshold) {
        int ans = 0;
        int[] temp = new int[arr.length];
        if(arr[0] <= threshold) {
            ans = 1;
        }
        temp[0] = arr[0];
        for(int i=1;i<arr.length;i++) {
            temp[i] = temp[i-1] + arr[i];
            for(int j=0;j<i;j++) {
                if(temp[i] - temp[j] <= threshold) {
                    ans = Math.max(ans, i - j);
                    if(ans == maxLength) {
                        return maxLength;
                    }
                }
            }
        }
        return ans;
    }




    boolean found = false;
    public int maxSideLengthSonali(int[][] mat, int threshold) {
        if(mat == null || mat.length < 0) {
            return 0;
        }
        int rowLen = mat.length;
        int colLen = mat[0].length;

        int maxSideLen = Math.min(rowLen, colLen);
        int[][] dp = new int[rowLen][colLen];
        int answer = 0;

        fillDP(dp, mat, threshold);
        if(!found) {
            return 0;
        }
        for(int i=1;i<=maxSideLen;i++) {
            if(!isSquarePossible(dp, i, threshold, rowLen, colLen)) {
                return answer;
            }
            answer++;
        }

        return answer;
    }

    boolean isSquarePossible(int[][] dp, int maxLen, int threshold, int rowLen, int colLen) {
        int endRow = maxLen - 1;
        int endCol = maxLen - 1;
        int startRow = 0;
        int startCol = 0;
        while(endCol < colLen && endRow < rowLen) {
            int range = rangeQuery(startRow, startCol, endRow, endCol, dp);
            if (range <= threshold) {
                return true;
            }
            if(endCol == colLen - 1) {
                endRow++;
                startRow++;
                startCol = 0;
                endCol = maxLen - 1;
            } else {
                startCol++;
                endCol++;
            }
        }

        return false;
    }

    int rangeQuery(int row1, int col1, int row2, int col2, int[][] dp) {
        return dp[row2][col2] - (col1 - 1 >= 0 ? dp[row2][col1 -1] : 0) - (row1 -1 >= 0 ? dp[row1 -1][col2] : 0) + (col1 - 1 >= 0 && row1 - 1 >=0 ? dp[row1 -1][col1 -1] : 0);
    }

    void fillDP(int[][] dp, int[][] mat, int threshold) {
        dp[0][0] = mat[0][0];
        for(int i=0;i<mat.length;i++) {
            for(int j=0;j<mat[0].length;j++) {
                if(mat[i][j] <= threshold) {
                    found = true;
                }
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
            }

        }
    }
}
