package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/longest-repeating-substring/discuss/540449/Java-solution-beats-99-time-and-100-space-O(n2)
public class LongestRepeatingSubstring {
    public static int longestRepeatingSubstring(String s) {
        int res = 0, n = s.length();
        for (int k = 1; k <= n - 1; k++) { // check substring with len k repeated or not
            Set<String> set = new HashSet<>();
            boolean flag = false;
            for (int i = 0; i <= n - k; i++) {
                String sub = s.substring(i, i + k);
                if (!set.add(sub)) { // repeated
                    flag = true;
                    break;
                }
            }
            if (flag) res = k;
            else break;
        }
        return res;
    }

    public static void main(String args[]) {
        System.out.println(longestRepeatingSubstring("banana"));
    }
}
