package com.practice.graph;

public class SurroundedRegions {
    public void solve(char[][] board) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int row = 0; row <board.length; row ++) {
            for(int col = 0; col < board[row].length; col ++) {
                if (board[row][col] == '0' && !visited[row][col]) {
                    dfs(board, visited , row, col);
                }
            }
        }
        for (int row = 0; row <board.length; row ++) {
            for(int col = 0; col < board[row].length; col ++) {
                if (board[row][col] == 'Z') {
                    board[row][col] = 'X';
                }
            }
        }
    }
    private void dfs(char[][] board, boolean[][] visited, int i, int j) {
        if (i < 0 || j <0 || i >= board.length || j >= board[i].length || visited[i][j]) {
            return;
        }
        visited[i][j] = true;
        if (board[i][j] == 'X') {
            return;
        }
        if ((i > 0 && i < board.length -1) &&
                (j > 0 && j < board[i].length - 1)) {
            board[i][j] = 'Z';
        }
        dfs(board,visited,i,j+1);
        dfs(board,visited,i+1,j);
        dfs(board,visited,i-1,j);
        dfs(board,visited,i, j-1);
    }

    public static void main(String args[]) {
        SurroundedRegions surroundedRegions = new SurroundedRegions();
        char matrix[][] = {{'X','X','X','X'},
                           {'X','0','0','X'},
                           {'X','X','0','X'},
                           {'X','0','X','X'}
        };

        surroundedRegions.solve(matrix);
    }
}
