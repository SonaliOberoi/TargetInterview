package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/path-with-minimum-effort/
public class MinimumEffortPath {

    public int minimumEffortPath(int[][] heights) {
        if(heights == null || heights.length < 1) {
            return -1;
        }
        int rowLen = heights.length;
        int colLen = heights[0].length;
        dfs(0,0,rowLen,colLen, heights, new boolean[rowLen][colLen], 0);
        return answer;
    }
    int answer = Integer.MAX_VALUE;
    private void dfs(int row, int col, int rowLen, int colLen, int[][] heights, boolean[][] visited, int currentSum) {
        if(!isValid(row, col, rowLen, colLen) || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if(row == rowLen - 1 && col == colLen - 1) {
            System.out.println(currentSum);
            visited[row][col] = false;
            answer = Math.min(answer, currentSum);
        }


        if(row + 1 < rowLen && Math.abs(heights[row + 1][col] - heights[row][col]) <= answer) {
            dfs(row + 1, col, rowLen, colLen, heights , visited, Math.max(currentSum , Math.abs(heights[row + 1][col] - heights[row][col])));
        }

        if(col + 1 < colLen && Math.abs(heights[row][col + 1] - heights[row][col]) <= answer) {
            dfs(row, col + 1, rowLen, colLen, heights , visited, Math.max(currentSum, Math.abs(heights[row][col + 1] - heights[row][col])));
        }

        if(row  - 1 >= 0 && Math.abs(heights[row - 1][col] - heights[row][col]) <= answer) {
            dfs(row - 1, col, rowLen, colLen, heights , visited, Math.max(currentSum, Math.abs(heights[row - 1][col] - heights[row][col])));
        }

        if(col  - 1 >= 0 && Math.abs(heights[row][col - 1] - heights[row][col]) <= answer) {
            dfs(row, col - 1, rowLen, colLen, heights , visited, Math.max(currentSum, Math.abs(heights[row][col - 1] - heights[row][col])));
        }
        visited[row][col] = false;
    }

    private boolean isValid(int row, int col, int rowLen, int colLen) {
        return !(row < 0 || row >= rowLen || col < 0 || col >= colLen) ;

    }

    public static void main(String args[]) {
        MinimumEffortPath minimumEffortPath = new MinimumEffortPath();
        int[][] test = {{1,2,3},
                {3,8,4},
                {5,3,5}};
        minimumEffortPath.minimumEffortPath(test);
    }
}
