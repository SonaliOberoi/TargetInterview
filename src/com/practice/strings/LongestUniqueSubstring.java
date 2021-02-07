package com.practice.strings;

import java.util.HashMap;
import java.util.*;

//https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestUniqueSubstring {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
    public static void main(String args[]) {
        LongestUniqueSubstring longestUniqueSubstring = new LongestUniqueSubstring();
        longestUniqueSubstring.lengthOfLongestSubstring("dvdf");
    }
}
