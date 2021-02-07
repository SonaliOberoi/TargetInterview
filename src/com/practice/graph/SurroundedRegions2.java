package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/surrounded-regions/
public class SurroundedRegions2 {
    int[][] dir = {{1,0}, {0,1}, {-1, 0}, {0, -1}, {1,1}, {-1,1}, {1, -1}, {-1,-1}};
    Map<Character, Boolean> map = new HashMap<>();
    public void solve(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int count = 1;
        boolean[][] visited = new boolean[row][col];
        for (int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                if (board[i][j] == 'X') {
                    continue;
                }
                dfs(board, visited, row, col, i, j, (char) count);
                count++;
            }
        }

        for (int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                if (map.containsKey(board[i][j])) {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }


    private void dfs(char[][] board, boolean[][] visited, int rowLen, int colLen, int x, int y, char ch) {
        if (!isValid(x, y, rowLen, colLen, board, visited)) {
            return;
        }
        visited[x][y] = true;
        board[x][y] = ch;
        if (y == 0 || y == colLen -1 || x == 0 || x == rowLen -1) {
            map.put(ch, false);
        }
        for (int[] current: dir) {
            dfs(board, visited, rowLen, colLen, x + current[0], y + current[1], ch);
        }
    }

    private boolean isValid(int x, int y, int rowLen, int collen, char[][] board, boolean[][] visited) {
        return (x >= 0 && y>=0 && x < rowLen && y<collen && !visited[x][y] && board[x][y] == 'O');
    }

    public static void main(String args[]) {
        SurroundedRegions2 surroundedRegions2 = new SurroundedRegions2();
        char[][] board = {{'X','X','X','X'},
                           {'X','O','O','X'},
                           {'X','X','O','X'},
                            {'X','O','X','X'}};
        surroundedRegions2.solve(board);
    }
}
