package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/maximum-points-you-can-obtain-from-cards/
//https://leetcode.com/problems/snakes-and-ladders/
public class SnakesAndLadder {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length < 0) {
            return -1;
        }
        int len = board.length;
        Queue<int[]> q = new LinkedList();
        boolean[] visited = new boolean[len* len];
        boolean LR = true;
        int move = 0;
        int[] arr = new int[len*len];

        for(int row = 0;row < len;row++) {
            if(LR) {
                for(int col = 0;col < len;col++) {
                    arr[move] = board[row][col];
                    move++;
                }
            } else {
                for(int col = len -1;col >=0;col--) {
                    arr[move] = board[row][col];
                    move++;
                }
            }
            LR = !LR;
        }
        System.out.print(Arrays.toString(arr));

        for(int i=0;i<6 && i < len*len;i++) {
            if(arr[i] == -1) {
                int[] temp = {i, 1};
                q.offer(temp);

            } else if(arr[i] > i + 1) {
                int[] temp = {arr[i], 1};
                q.offer(temp);

            }
            visited[i] = true;
        }

        while (!q.isEmpty()) {
            int size = q.size();
            for(int i=0;i<size;i++) {
                int[] current = q.poll();
                if(current[0] == len*len -1) {
                    return current[1];
                }
                for(int j=current[0] + 1;j< current[0] + 7 && j < len*len;j++) {
                    if(!visited[j]) {
                        visited[j] = true;
                        if (arr[j] == -1) {
                            int[] temp = {j, current[1] + 1};
                            q.offer(temp);

                        } else if (arr[j] > j + 1) {
                            int[] temp = {arr[j], current[1] + 1};
                            q.offer(temp);

                        }
                    }
                }
            }
        }

        return -1;
    }
}
