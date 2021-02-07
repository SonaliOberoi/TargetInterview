package com.practice.graph;

import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/shortest-distance-two-cells-matrix-grid/
public class ShortestDistanceInMatrix {

    private int bfs(char grid[][], char end, int row, int col, boolean visited[][]) {
        Queue<QItem> itemQueue = new LinkedList();
        itemQueue.add(new QItem(row, col, 0));
        visited[row][col] = true;

        while (!itemQueue.isEmpty()) {
            QItem currentItem = itemQueue.poll();

            if (grid[currentItem.row][currentItem.col] == end) {
                return currentItem.dist;
            }
            // moving up
            if (currentItem.row - 1 >= 0 &&
                    visited[currentItem.row - 1][currentItem.col] == false) {
                itemQueue.add(new QItem(currentItem.row - 1, currentItem.col, currentItem.dist + 1));
                visited[currentItem.row - 1][currentItem.col] = true;
            }

            // moving down
            if (currentItem.row + 1 < grid.length &&
                    visited[currentItem.row + 1][currentItem.col] == false) {
                itemQueue.add(new QItem(currentItem.row + 1, currentItem.col, currentItem.dist + 1));
                visited[currentItem.row + 1][currentItem.col] = true;
            }

            // moving left
            if (currentItem.col - 1 >= 0 &&
                    visited[currentItem.row][currentItem.col - 1] == false) {
                itemQueue.add(new QItem(currentItem.row, currentItem.col - 1, currentItem.dist + 1));
                visited[currentItem.row][currentItem.col - 1] = true;
            }

            // moving right
            if (currentItem.col + 1 < grid[0].length &&
                    visited[currentItem.row][currentItem.col + 1] == false) {
                itemQueue.add(new QItem(currentItem.row, currentItem.col + 1, currentItem.dist + 1));
                visited[currentItem.row][currentItem.col + 1] = true;
            }
        }
        return -1;
    }

    private void exploreNeighbours(int row, int col, boolean visited[][]) {

    }

    public int findShortestDistance(char grid[][], char start, char end, char invalid) {
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == invalid) {
                    visited[i][j] = true;
                }
            }
        }
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == start) {
                    return bfs(grid, end, i, j, visited);
                }
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        char grid[][] = { { '0', '*', '0', 's' },
                          { '*', '0', '*', '*' },
                          { '0', '*', '*', '*' },
                          { 'd', '*', '*', '*' } };

        ShortestDistanceInMatrix distanceInMatrix = new ShortestDistanceInMatrix();
        System.out.println(distanceInMatrix.findShortestDistance(grid, 's', 'd', '0'));
    }
}


class QItem {
    int row;
    int col;
    int dist;
    QItem(int row, int col, int dist) {
        this.row = row;
        this.col = col;
        this.dist = dist;
    }
}
