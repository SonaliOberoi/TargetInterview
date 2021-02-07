package com.practice.strings;

//https://leetcode.com/problems/swap-adjacent-in-lr-string/discuss/147987/Explanation-and-java-solution
public class ReplaceLR {
    public static boolean canTransform(String start, String end) {
        int l = 0, r = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'L') l++;
            if (start.charAt(i) == 'R') r++;
            if (end.charAt(i) == 'L') l--;
            if (end.charAt(i) == 'R') r --;

            if (l > 0 || r < 0 || ( l < 0 && r != 0) || (r > 0 && l != 0)) return false;
        }

        return l == 0 && r == 0;
    }

    public static void main(String args[]) {
        canTransform("RXXLRXRXL", "XRLXXRRLX");
    }
}
