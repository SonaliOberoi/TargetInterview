package com.practice.graph;

//https://leetcode.com/problems/max-area-of-island/
public class MaxIslandArea {

    final static int[][] DIRS = {{1,0}, {0,1},{-1,0}, {0,-1}};

    int answer = 0;
    int currentDfsCount = 0;

    private void dfs(int x, int y, boolean visited[][], int rowSize, int colSize, int[][] matrix) {
        if(!isValidMove(x, y, rowSize, colSize) || visited[x][y] || matrix[x][y] != 1) {
            return;
        }
        visited[x][y] = true;
        currentDfsCount +=1;

        for(int[] direction: DIRS) {
            int row = x + direction[0];
            int col = y + direction[1];
            dfs(row, col, visited, rowSize, colSize, matrix);
        }
    }
    private boolean isValidMove(int x, int y, int rowSize, int colSize) {
        return !(x < 0 || x >= rowSize || y < 0 || y >= colSize);
    }

    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null) {
            return answer;
        }
        int rowSize = grid.length;
        int colSize = grid[0].length;

        boolean[][] visited = new boolean[rowSize][colSize];
        for(int i = 0;i<rowSize;i++) {
            for(int j=0;j<colSize;j++) {
                if(grid[i][j] == 1 && !visited[i][j]) {
                    currentDfsCount = 0;
                    dfs(i, j, visited, rowSize, colSize, grid);
                    answer = Math.max(answer, currentDfsCount);
                }
            }
        }
        return answer;
    }
}
