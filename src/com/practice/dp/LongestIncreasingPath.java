package com.practice.dp;

import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length < 1) {
            return 0;
        }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int answer = 0;
        int[][] cache = new int[rowLen][colLen];
        for(int i=0;i<rowLen;i++) {
            for(int j=0;j<colLen;j++) {
                if(cache[i][j] == 0) {
                    answer = Math.max(answer, dfsUtil(i, j, matrix, cache, null));
                }
            }
        }
        return answer;
    }

    private int dfsUtil(int x, int y, int[][] matrix, int[][] cache, Integer prev) {
        if(x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || prev != null && matrix[x][y] <= prev) {
            return 0;
        }

        if(cache[x][y] != 0) {
            return cache[x][y];
        }

        int left = dfsUtil(x, y -1, matrix, cache, matrix[x][y]);
        int right = dfsUtil(x, y +1, matrix, cache, matrix[x][y]);
        int up = dfsUtil(x - 1, y, matrix, cache, matrix[x][y]);
        int down = dfsUtil(x + 1, y, matrix, cache, matrix[x][y]);

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> b -a);
        pq.offer(left);
        pq.offer(right);
        pq.offer(up);
        pq.offer(down);

        cache[x][y] = Math.max(pq.poll(), pq.poll()) + 1;
        return cache[x][y];
    }

    public static void main(String args[]) {
        LongestIncreasingPath longestIncreasingPath = new LongestIncreasingPath();
        int[][] matrix = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println(longestIncreasingPath.longestIncreasingPath(matrix));
    }
}
