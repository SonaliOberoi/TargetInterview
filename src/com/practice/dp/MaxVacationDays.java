package com.practice.dp;

//https://leetcode.com/problems/maximum-vacation-days/
public class MaxVacationDays {
    public static int maxVacationDays(int[][] flights, int[][] days) {
        if(flights == null || days == null || flights.length < 0 || days.length <0) {
            return 0;
        }

        int rowLen = days.length;
        int colLen = days[0].length;
        int[][] dp = new int[rowLen][colLen];
        dp[0][0] = days[0][0];
        for(int row=1;row<rowLen;row++) {
            if(flights[0][row] == 1) {
                dp[row][0] = days[row][0];
            } else {
                dp[row][0] = -1;
            }
        }

        for(int col=1;col<colLen;col++) {
            for(int row=0;row<rowLen;row++) {
                int toCity = row;

                dp[row][col] = dp[row][col -1] == -1 ? -1 : days[row][col] + dp[row][col - 1];
                for(int i=0;i<rowLen;i++) {
                    if(i == row) {
                        continue;
                    }
                    int fromCity = i;
                    if(flights[fromCity][toCity] == 1 && dp[fromCity][col  - 1]  != -1) {
                        dp[row][col] = Math.max(dp[row][col], dp[i][col-1] + days[row][col]);
                    }

                }
            }
        }

        int max = dp[0][colLen -1];
        for(int j = 1;j<rowLen;j++) {
            max = Math.max(max, dp[j][colLen-1]);
        }
        return max;
    }

    public static void main(String args[]) {
        int[][] flights ={{0,0,0,0,0},{0,0,0,0,0},{1,1,0,1,0},{1,0,0,0,0},{1,1,1,1,0}};
        int[][] days = {{0,1,1,0,0},{1,2,2,1,2},{2,0,0,2,1},{0,2,1,2,2},{0,1,1,1,0}};
        maxVacationDays(flights, days);
    }
}
