package com.practice.game;

//https://leetcode.com/problems/find-winner-on-a-tic-tac-toe-game/
public class TicTacToe {

    public String tictactoe(int[][] moves) {

        if(moves == null || moves.length < 5) {
            return "Pending";
        }

        String[] row = new String[3];
        String[] col = new String[3];
        String[] dia = new String[2];
        int i = 0;
        for(int[] turn: moves) {
            int x = turn[0];
            int y = turn[1];
            String player = i % 2 == 0 ? "A" : "B";
            row[x] = row[x] + player;
            System.out.println("row:" +row[x]);
            if(hasWon(row[x])) {
                return player;
            }

            col[y] = col[y] + player;
            System.out.println("col:" + col[y]);
            if(hasWon(col[y])) {
                return player;
            }

            if ((x == 0 && y == 0) || (x == 1 && y == 1) || (x == 2 && y == 2)) {
                dia[0] = dia[0] + player;
                System.out.println("dia:" +dia[0]);
                if(hasWon(dia[0])) {
                    return player;
                }
            }

            if ((x == 0 && y == 2) || (x == 1 && y == 1) || (x == 2 && y == 0)) {
                dia[1] = dia[1] + player;
                if(hasWon(dia[1])) {
                    return player;
                }
            }
            i++;
        }

        return "Draw";

    }

    private boolean hasWon(String str) {
        str.replace("null", "");
        System.out.println(str.chars().allMatch(c -> c == str.charAt(0)));
        return str.length()==3 && str.chars().allMatch(c -> c == str.charAt(0));
    }
}
