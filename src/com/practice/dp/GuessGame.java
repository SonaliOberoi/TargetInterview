package com.practice.dp;

//https://leetcode.com/problems/guess-number-higher-or-lower-ii/solution/
public class GuessGame {

    public int calculate(int low, int high) {
        if (low >= high)
            return 0;
        int minres = Integer.MAX_VALUE;
        for (int i = (low + high) / 2; i <= high; i++) {
            int res = i + Math.max(calculate(i + 1, high), calculate(low, i - 1));
            minres = Math.min(res, minres);
        }
        return minres;
    }
    public int getMoneyAmount(int n) {
        return calculate(1, n);
    }

    public static void main(String args[]) {
        GuessGame guessGame = new GuessGame();
        System.out.print(guessGame.getMoneyAmount(10));
    }
}
