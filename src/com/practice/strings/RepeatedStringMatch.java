package com.practice.strings;

//https://leetcode.com/problems/repeated-string-match/
public class RepeatedStringMatch {

    public static int repeatedStringMatch(String a, String b) {
        if((a == null && b == null) || b.length() == 0) {
            return 0;
        }
        if (b.indexOf(a) == -1) {
            return -1;
        }
        int answer = 1;

        int firstIndex = b.indexOf(a);
        int lastIndex = b.lastIndexOf(a);
        int aLength = a.length();
        int index = firstIndex;

       while (index != -1) {
           int previousIndex = index;
           index = b.indexOf(a, index + 1);

           if (index != -1 && index != previousIndex + aLength) {
               return -1;
           }
           answer++;
       }

       if (b.substring(lastIndex + aLength, b.length()) + b.substring(0, firstIndex) == a) {
           return answer + 1;
       } else {
           return -1;
       }
    }

    public static void main(String args[]) {
        System.out.println(repeatedStringMatch("abcd", "cdabcdabcdab"));
    }
}
