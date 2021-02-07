package com.practice.graph;

import java.util.LinkedList;
import java.util.Queue;

//https://leetcode.com/problems/the-maze-iii/
public class MazeGame {
    class Element{
        int x,y;
        String path;
        Element(int x, int y, String path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }
    }
    final String IMPOSSIBLE = "impossible";
    int[][] dir = {{1,0}, {0,-1}, {0,1}, {-1,0}};
    String[] path = new String[]{"d", "l", "r", "u"};
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        if (maze == null) {
            return IMPOSSIBLE;
        }
        if(ball == hole) {
            return "";
        }
        Queue<Element> queue = new LinkedList<>();
        queue.add(new Element(ball[0], ball[1], ""));
        int rowLen = maze.length;
        int colLen = maze[0].length;
        boolean[][] visited = new boolean[rowLen][colLen];
        visited[ball[0]][ball[1]] = true;
        while(!queue.isEmpty()) {
            int size = queue.size();
            Element current = queue.poll();
            for(int i=0;i<size;i++) {
                for(int j = 0;j<dir.length;j++) {
                    int[] currentDirection = dir[j];
                    int currX = current.x + currentDirection[0];
                    int currY = current.y + currentDirection[1];
                    if (!isValid(currX, currY, visited, rowLen, colLen, maze)) {
                        continue;
                    }
                    String currentPath = current.path;
                    while (isValid(currX, currY, visited, rowLen, colLen, maze)) {
                        currX = currX + currentDirection[0];
                        currY = currY + currentDirection[1];
                        if(currX == hole[0] && currY == hole[1]) {
                            return currentPath;
                        }
                    }
                    currX = currX - currentDirection[0];
                    currY = currY - currentDirection[1];
                    currentPath = currentPath + path[i];
                    System.out.println("x:" + currX + " y:" + currY);
                    if(currX == hole[0] && currY == hole[1]) {
                        return currentPath;
                    }
                    if (currX != current.x || currY != current.y && !visited[currX][currY]) {
                        System.out.println(currentPath);
                        queue.add(new Element(currX, currY, currentPath));
                        visited[currX][currY] = true;
                    }

                }
            }
        }


        return IMPOSSIBLE;
    }

    private boolean isValid(int x, int y, boolean[][] visited, int rowLen, int colLen, int[][] maze) {
        return (x >=0 && y >=0 && x < rowLen && y < colLen && !visited[x][y] && maze[x][y] != 1);
    }

    public static void main(String args[]) {
        MazeGame mazeGame = new MazeGame();
        int[][] maze = {{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
        int[] ball = {4,3};
        int[] hole = {3,0};
        System.out.println(mazeGame.findShortestWay(maze, ball, hole));
    }
}
