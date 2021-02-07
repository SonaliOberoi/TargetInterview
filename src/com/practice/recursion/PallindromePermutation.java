package com.practice.recursion;

import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/palindrome-permutation-ii/
public class PallindromePermutation {

    List<String> list = new ArrayList<>();
    Set<String> set = new HashSet<>();

    public List<String> generatePalindromes(String s) {
        if(s == null || s.length() < 0) {
            return Collections.EMPTY_LIST;
        }
        if(s.length() == 1) {
            list.add(s);
            return list;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        String permString = "";
        int numOfOddChar = 0;
        String oddChar = "";
        for (Character key : map.keySet()) {
            if (map.get(key) % 2 == 0) {
                String temp = "";
                for(int i=0;i<map.get(key) /2;i++) {
                    temp = temp + key;
                }
                permString = permString + temp;
            } else {
                numOfOddChar++;
                oddChar = String.valueOf(key);
                if (numOfOddChar > 1) {
                    return Collections.EMPTY_LIST;
                }
                String temp = "";
                for(int i=0;i<map.get(key) /2;i++) {
                    temp = temp + key;
                }
                permString = permString + temp;
            }
        }
        recur(permString, "", permString.length(), oddChar);
        return list;

    }

    private void recur(String remaining, String target, int length, String oddChar) {
        if(target.length() == length) {
            if(set.contains(target)) {
                return;
            }
            set.add(target);
            list.add(target + oddChar + new StringBuilder(target).reverse().toString());
        }
        for(int i=0;i<remaining.length();i++) {
            char fixed = remaining.charAt(i);
            StringBuilder sb = new StringBuilder(remaining);
            recur(sb.deleteCharAt(i).toString(), target + fixed, length, oddChar);
        }
    }

    public static void main(String args[]) {
        PallindromePermutation pallindromePermutation = new PallindromePermutation();
        pallindromePermutation.generatePalindromes("aaa");
    }
}
