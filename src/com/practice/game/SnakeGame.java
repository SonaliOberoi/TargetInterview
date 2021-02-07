package com.practice.game;

import java.util.Deque;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

//https://leetcode.com/problems/design-snake-game/
public class SnakeGame {
    class State {
        int row,col;
        State(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    int width;
    int height;
    int[][] matrix = new int[width][height];
    int[][] food;
    Deque<State> deque = new LinkedBlockingDeque<>();
    public SnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        deque.add(new State(0,0));
    }

    public int move(String direction) {
        Queue<Integer> queue = new LinkedList<>();
       if("R".equals(direction)) {
            State head = deque.getFirst();
            int row = head.row;
            int col = head.col + 1;
            if(col >= width || col < 0) {
                return -1;
            }
            if(food != null && row == food[0][0] && col == food[0][1]) {
                //update food and remove first row
                deque.addFirst(new State(row, col));
                return 1;
            }
            deque.addFirst(new State(row, col));
            deque.removeLast();
            return 0;
       } else if("L".equals(direction)) {

       } else if("D".equals(direction)) {

       }else if("U".equals(direction)) {

       }
       return -1;
    }
}


