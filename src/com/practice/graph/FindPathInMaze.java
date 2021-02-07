package com.practice.graph;

import java.util.ArrayList;

//https://www.geeksforgeeks.org/find-paths-from-corner-cell-to-middle-cell-in-maze/
public class FindPathInMaze {

    private void dfsUtil(int maze[][], int row, int col, boolean visited[][], ArrayList<RowCol> rowColArrayList) {
        if (row < 0 || col < 0 || row >= maze.length || col >= maze[0].length || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        rowColArrayList.add(new RowCol(row, col));

        if (row == maze.length /2 && col == maze[0].length / 2) {
            System.out.println(rowColArrayList.size());
            for (RowCol rowCol: rowColArrayList) {
                System.out.println("Path row,col is: " + rowCol.row + " , " + rowCol.col);
            }
            return;
        }
        dfsUtil(maze, row, col + maze[row][col], visited, rowColArrayList);
        dfsUtil(maze, row + maze[row][col], col, visited, rowColArrayList);
        dfsUtil(maze, row - maze[row][col], col, visited, rowColArrayList);
        dfsUtil(maze, row, col - maze[row][col], visited, rowColArrayList);


    }

    public void findPath(int maze[][]) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        dfsUtil(maze, 0, 0, visited, new ArrayList<RowCol>());
    }

    public static void main(String args[]) {
        int maze[][] =
        {
            { 3, 5, 4, 4, 7, 3, 4, 6, 3 },
            { 6, 7, 5, 6, 6, 2, 6, 6, 2 },
            { 3, 3, 4, 3, 2, 5, 4, 7, 2 },
            { 6, 5, 5, 1, 2, 3, 6, 5, 6 },
            { 3, 3, 4, 3, 0, 1, 4, 3, 4 },
            { 3, 5, 4, 3, 2, 2, 3, 3, 5 },
            { 3, 5, 4, 3, 2, 6, 4, 4, 3 },
            { 3, 5, 1, 3, 7, 5, 3, 6, 4 },
            { 6, 2, 4, 3, 4, 5, 4, 5, 1 }
        };

        FindPathInMaze findPathInMaze = new FindPathInMaze();
        findPathInMaze.findPath(maze);
    }
}

class RowCol {
    int row;
    int col;
    public RowCol(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
