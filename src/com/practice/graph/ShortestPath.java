package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
public class ShortestPath {
    private static final int DIRS[][] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        // last dimension is # of obstables removed
        boolean[][][] visited = new boolean[m][n][m * n];
        Queue<Node> q = new ArrayDeque<>();

        visited[0][0][0] = true;
        q.add(new Node(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            int i = node.x;
            int j = node.y;
            int dist = node.dist;
            int removed = node.removed;
            // reach desination, not need to bfs further
            if (i == m - 1 && j == n - 1) {
                return dist;
            }

            for (int dir = 0; dir < 4; dir++) {
                int newRow = i + DIRS[dir][0], newCol = j + DIRS[dir][1];
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                    int newRemoved = removed + grid[newRow][newCol];
                    if (newRemoved <= k && !visited[newRow][newCol][newRemoved]) {
                        visited[newRow][newCol][newRemoved] = true;
                        q.add(new Node(newRow, newCol, dist + 1, newRemoved));
                    }
                }
            }
        }
        return -1;
    }

    static class Node {
        int x, y, dist, removed;

        Node(int x, int y, int dist, int removed) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.removed = removed;
        }
    }


}
