package com.practice.arrays;

//https://leetcode.com/problems/bulls-and-cows/submissions/
public class BullsAndCows {

    public static String getHint(String secret, String guess) {
        int[] num = new int[10];
        int bull = 0, cow = 0;
        for (int i = 0; i < secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i)) ;
            int g = guess.charAt(i) - '0';
            if (s == g)
                bull += 1;
            else {
                if (num[s]++ < 0)
                    cow++;
                if (num[g]-- > 0)
                    cow++;
            }
        }
        return bull+"A"+cow+"B";
    }
    public static void main(String args[]) {
        System.out.print(getHint("1807", "0810"));
    }
}
