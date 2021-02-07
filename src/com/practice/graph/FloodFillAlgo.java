package com.practice.graph;

public class FloodFillAlgo {

    private void dfs(int row, int col, boolean[][] visited, int color, int[][] matrix) {
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[row].length || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        if (matrix[row][col] != color) {
            return;
        } else {
            matrix[row][col] = -1;
        }
        dfs(row +1, col, visited, color, matrix);
        dfs(row, col + 1, visited, color, matrix);
        dfs(row - 1, col, visited, color, matrix);
        dfs(row, col - 1, visited, color, matrix);
    }

    public int[][] floodFill(int[][] matrix, int x, int y, int color) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        dfs(x, y, visited, color, matrix);
        return matrix;
    }
    public static void main(String args[]) {
        int matrix[][] = {{1,3,0,1,0,1,1},
                          {1,4,0,1,1,0,0},
                          {0,0,3,2,2,1,1},
                          {1,0,1,2,2,2,2},
                          {1,0,0,0,0,2,1}
        };

        FloodFillAlgo floodFillAlgo = new FloodFillAlgo();
        floodFillAlgo.floodFill(matrix, 3, 4, 2);
    }
}
