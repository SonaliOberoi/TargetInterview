package com.practice.recursion;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/find-shortest-safe-route-in-a-path-with-landmines/
public class FindShortest {

    public int findShortest(int[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int row = 0; row <matrix.length; row ++ ) {
            for (int col = 0; col < matrix[0].length; col ++) {
                if (matrix[row][col] == 0) {
                    visited[row][col] = true;
                    makeAll4DirectionsInvalid(matrix, row, col, visited);
                }
            }
        }
        int minimumPath = Integer.MAX_VALUE;
        for (int row = 0; row <matrix.length; row ++ ) {
            if (matrix[row][0] == 1 && !visited[row][0]) {
                System.out.println("Starting with row, col : " + row  + ", " + 0);
                int pathDist = bfsUtil(matrix, visited, row, 0, new HashMap<QItem, QItem>());
                System.out.println("Path till here: " + pathDist);
                if (pathDist < minimumPath) {
                    minimumPath = pathDist;
                }
            }
        }
        return minimumPath;
    }

    private int bfsUtil(int[][] matrix, boolean[][] visited, int row, int col, HashMap<QItem, QItem> parentMap) {
        Queue<QItem> queue = new LinkedList<>();
        queue.add(new QItem(row, col, 0));

        while (!queue.isEmpty()) {
            QItem currentItem = queue.poll();
            visited[currentItem.row][currentItem.col] = true;
            if (currentItem.col == matrix[0].length -1) {
                System.out.println("We got to the end with distance: " + currentItem.distance);
                return currentItem.distance;
            }
            exploreNeighbours(matrix, visited, currentItem, queue, currentItem.distance, parentMap);
        }

        return Integer.MAX_VALUE;
    }

    private void exploreNeighbours(int[][] matrix, boolean[][] visited,
                                   QItem current, Queue<QItem> queue, int currentDist, HashMap parentMap) {
        int row = current.row;
        int col = current.col;
        int up = row - 1;
        int down = row + 1;
        int right = col  + 1;
        int left = col - 1;
        if (up >= 0 && up < matrix.length &&  matrix[up][col] == 1 && !visited[up][col]) {
            QItem next = new QItem(up, col, currentDist + 1);
            queue.add(next);
            visited[up][col] = true;
            parentMap.put(next, current);
        }
        if (down >= 0 && down < matrix.length && matrix[down][col] == 1 && !visited[down][col]  ) {
            QItem next = new QItem(down, col, currentDist + 1);
            queue.add(next);
            visited[down][col] = true;
            parentMap.put(next, current);
        }
        if (right >= 0 && right < matrix[0].length && matrix[row][right] == 1 && !visited[row][right]) {
            QItem next = new QItem(row, right, currentDist + 1);
            queue.add(next);
            visited[row][right] = true;
            parentMap.put(next, current);
        }
        if (left >= 0 && left < matrix[0].length && matrix[row][left] == 1 && !visited[row][left]) {
            QItem next = new QItem(row, left, currentDist + 1);
            queue.add(next);
            visited[row][left] = true;
            parentMap.put(next, current);
        }

    }
    private void makeAll4DirectionsInvalid(int[][] matrix, int row, int col, boolean[][] visited) {
        int up = row - 1;
        int down = row + 1;
        int right = col  + 1;
        int left = col - 1;

        if (up >= 0 && up < matrix.length) {
            matrix[up][col] = -1;
            visited[up][col] = true;
        }
        if (down >= 0 && down < matrix.length) {
            matrix[down][col] = -1;
            visited[down][col] = true;
        }
        if (right >= 0 && right < matrix[0].length) {
            matrix[row][right] = -1;
            visited[row][right] = true;
        }
        if (left >= 0 && left < matrix[0].length) {
            matrix[row][left] = -1;
            visited[row][left] = true;
        }
    }

    public static void main(String args[]) {
        // input matrix with landmines shown with number 0
        int mat[][] =
        {
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
            { 1, 0, 1, 1, 1, 1, 1, 1, 0, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 0, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
            { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
            { 1, 1, 1, 0, 1, 1, 1, 1, 1, 1 }
        };
        FindShortest findShortest = new FindShortest();
        findShortest.findShortest(mat);
    }
}
class QItem {
    int row;
    int col;
    int distance;
    QItem(int row, int col, int distance) {
        this.row = row;
        this.col = col;
        this.distance = distance;
    }
}
