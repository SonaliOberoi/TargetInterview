package com.practice.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/dungeon-game/
public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        if(dungeon == null) {
            return -1;
        }
        int rowLen = dungeon.length;
        int colLen = dungeon[0].length;
        int[][] graph = new int[rowLen][colLen];
        for(int i=0;i<rowLen;i++) {
            for(int j=0;j<colLen;j++) {
                if(i==0 && j==0) {
                    continue;
                }
                if(i == 0) {
                    graph[i][j]+= graph[i - 1][j];
                } else if(j == 0) {
                    graph[i][j]+= graph[i][j - 1];
                } else {
                    graph[i][j]+= Math.max(graph[i-1][j], graph[i][j-1]);
                }
            }
        }
        System.out.println(Arrays.toString(graph));
        //do a bfs on graph
        boolean[][] visited = new boolean[rowLen][colLen];
        Queue<State> queue = new LinkedList();
        queue.add(new State(0,0));
        visited[0][0] = true;
        int answer = Integer.MIN_VALUE;
        int[][] directions = {{1,0}, {0,1}};
        while(!queue.isEmpty()) {
            int size = queue.size();
            int min = Integer.MAX_VALUE;
            for(int i=0;i<size;i++) {
                State current = queue.poll();
                min = Math.min(min, graph[current.row][current.col]);
                for(int[] dir:directions) {
                    int x = current.row + dir[0];
                    int y = current.col + dir[1];
                    if(x<0 || x >= rowLen || y<0 || y >=colLen) {
                        continue;
                    }
                    queue.offer(new State(x,y));
                }
                answer = Math.max(answer, min);
            }

        }
        return  answer;
    }
    class State {
        int row, col;
        State(int row,int col) {
            this.col = col;
            this.row = row;
        }
    }
}
