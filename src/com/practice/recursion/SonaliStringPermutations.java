package com.practice.recursion;

public class SonaliStringPermutations {

    public static void recur(String leftOverString, String target, int sourceLength) {
        if (target.length() == sourceLength) {
            System.out.println(target);
            return;
        }

        for(int i=0;i<leftOverString.length();i++) {
            char fixedChar = leftOverString.charAt(i);
            StringBuilder sb = new StringBuilder(leftOverString);
            recur(sb.deleteCharAt(i).toString(),
                    target + fixedChar, sourceLength);
        }
    }

    public static void main(String args[]) {
       recur("abcd", "", 4 );
    }
}
