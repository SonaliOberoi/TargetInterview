package com.practice.graph;

import java.util.*;

public class WallsAndGates {
    private static final int EMPTY = Integer.MAX_VALUE;
    private static final int GATE = 0;
    private static final List<int[]> DIRECTIONS = Arrays.asList(
            new int[] { 1,  0},
            new int[] {-1,  0},
            new int[] { 0,  1},
            new int[] { 0, -1}
    );

    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        if (m == 0) return;
        int n = rooms[0].length;
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (rooms[row][col] == GATE) {
                    q.add(new int[] { row, col });
                }
            }
        }
        while (!q.isEmpty()) {
            int[] point = q.poll();
            int row = point[0];
            int col = point[1];
            for (int[] direction : DIRECTIONS) {
                int r = row + direction[0];
                int c = col + direction[1];
                if (r < 0 || c < 0 || r >= m || c >= n || rooms[r][c] != EMPTY) {
                    continue;
                }
                rooms[r][c] = rooms[row][col] + 1;
                q.add(new int[] { r, c });
            }
        }
    }
    class Helper {
        String state;
        int dist;
        Helper(String state, int dist) {
            this.dist = dist;
            this.state = state;
        }
    }
    public int openLock(String[] deadends, String target) {
        Queue<Helper> q = new LinkedList<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        q.offer(new Helper("0000", 0));
        visited.add("0000");

        while(!q.isEmpty()) {

                Helper helper = q.poll();
                if(deads.contains(helper.state)) {

                    continue;
                }
                if(helper.state.equals(target)) return helper.dist;
                StringBuilder sb = new StringBuilder(helper.state);
                for(int i = 0; i < 4; i ++) {
                    char c = sb.charAt(i);
                    String s1 = sb.substring(0, i) + (c == '9' ? 0 : c - '0' + 1) + sb.substring(i + 1);
                    String s2 = sb.substring(0, i) + (c == '0' ? 9 : c - '0' - 1) + sb.substring(i + 1);
                    if(!visited.contains(s1) && !deads.contains(s1)) {
                        q.offer(new Helper(s1, helper.dist + 1));
                        visited.add(s1);
                    }
                    if(!visited.contains(s2) && !deads.contains(s2)) {
                        q.offer(new Helper(s2, helper.dist + 1));
                        visited.add(s2);
                    }
                }


        }
        return -1;
    }

    public static void main(String args[]) {
        WallsAndGates wallsAndGates = new WallsAndGates();
        int[][] matrix = {{Integer.MAX_VALUE,-1,0,Integer.MAX_VALUE},
                          {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,-1},
                          {Integer.MAX_VALUE,-1,Integer.MAX_VALUE,-1},
                          {0,-1,Integer.MAX_VALUE,Integer.MAX_VALUE}};
        wallsAndGates.wallsAndGates(matrix);
    }
}
