package com.practice.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//https://leetcode.com/problems/best-meeting-point/
public class BestMeetingPoint {
    int[][] directions = {{1,0}, {0,1} , {-1, 0}, {0, -1}};
    public int minTotalDistance(int[][] grid) {
        if(grid == null || grid.length < 1) {
            return -1;
        }
        List<int[][]> list = new ArrayList<>();
        int rowLen = grid.length;
        int colLen = grid[0].length;
        for(int i=0;i<rowLen;i++) {
            for(int j=0;j<colLen;j++) {
                if(grid[i][j] == 1) {
                    int[][] dist = bfs(i, j, rowLen, colLen);
                    list.add(dist);
                }
            }
        }
        int[][] answerArr = list.get(0);
        for(int i=1;i<list.size();i++) {
            int[][] temp = list.get(i);
            for(int k=0;k<rowLen;k++) {
                for (int j = 0; j < colLen; j++) {
                    answerArr[k][j] = answerArr[k][j] + temp[k][j];
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i=0;i<rowLen;i++) {
            for (int j = 0; j < colLen; j++) {
                min = Math.min(min, answerArr[i][j]);
            }
        }
        return min;
    }

    private int[][] bfs(int startX, int startY, int rowLen, int colLen) {
        boolean[][] visited = new boolean[rowLen][colLen];

        Queue<Coordinates> queue =  new LinkedList<>();
        queue.offer(new Coordinates(startX, startY));

        int[][] dist = new int[rowLen][colLen];

        int startDist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i=0;i<size;i++) {
                Coordinates current = queue.poll();
                dist[current.x][current.y] = startDist;
                visited[current.x][current.y] = true;
                for(int[] neighbor: directions) {
                    int newX = neighbor[0] + current.x;
                    int newY = neighbor[1] + current.y;
                    if(isValid(newX, newY, rowLen, colLen) && !visited[newX][newY]) {
                        queue.offer(new Coordinates(newX, newY));
                    }
                }
            }
            startDist++;
        }
        return dist;
    }

    private boolean isValid(int x, int y, int rowLen, int colLen) {
        return !(x < 0 || x >= rowLen || y < 0 || y >= colLen);
    }
    class Coordinates {
        int x,y;
        Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String args[]) {
        BestMeetingPoint bestMeetingPoint = new BestMeetingPoint();
        int[][] input = {{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}};

        System.out.println(bestMeetingPoint.minTotalDistance(input));
    }
}
