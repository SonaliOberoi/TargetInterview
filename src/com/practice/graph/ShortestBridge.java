package com.practice.graph;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/shortest-bridge/
public class ShortestBridge {
    int[][] directions = {{1,0}, {0,1}, {-1,0}, {0, -1}};
    int rowLen;
    int colLen;
    public int shortestBridge(int[][] A) {
        int count = 1;
        this.rowLen = A.length;
        this.colLen = A[0].length;
        boolean[][] visited = new boolean[rowLen][colLen];
        for(int i=0;i<rowLen;i++) {
            for(int j=0;j<colLen;j++) {
                if(A[i][j] == 1 && !visited[i][j]) {
                   dfs(A, i, j, count + 1, visited);
                }
            }
        }

        //do bfs from 2 to 3 and ans = dist from 2-3
        visited = new boolean[rowLen][colLen];
        Queue<State> queue = new LinkedList<>();
        for(int i=0;i<rowLen;i++) {
            for(int j=0;j<colLen;j++) {
                if(A[i][j] == 2 || A[i][j] == 3) {
                    queue.offer(new State(i, j, 0));
                }
            }
        }
        return -1;
    }
    class State{
        int row,col,dist;
        State(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    private void dfs(int[][] matrix, int row, int col, int count, boolean[][] visited) {
        if(!isValid(row, col) || visited[row][col] || matrix[row][col] == 0) {
            return;
        }
        visited[row][col] = true;
        if(matrix[row][col] == 1) {
            matrix[row][col] = count;
        }

        for(int[] dir: directions) {
            int x = row + dir[0];
            int y = row + dir[1];
            dfs(matrix, x, y, count, visited);
        }
    }

    private boolean isValid(int row, int col) {
        return (row >= 0 && col >= 0 && row < rowLen && col < colLen);
    }

}
