package com.practice.strings;

import java.util.*;

//https://leetcode.com/problems/find-and-replace-in-string/
public class FindReplaceString {
    public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if(S == null || S.length() < 1) {
            return S;
        }
        TreeMap<Integer, String[]> map = new TreeMap<>();
        for(int i=0;i<indexes.length;i++) {
            String[] arr = {sources[i], targets[i]};
            map.put(indexes[i], arr);
        }
        StringBuilder sb = new StringBuilder();
        int currentIndex = 0;
        for(Map.Entry<Integer,String[]> entry : map.entrySet()) {
            if(entry.getKey() - currentIndex > 0) {
                sb.append(S.substring(currentIndex, entry.getKey() ));
            }
            System.out.println(S.substring(entry.getKey() , entry.getKey()  + entry.getValue()[0].length()));
            if(entry.getValue()[0].equals(S.substring(entry.getKey() , entry.getKey()  + entry.getValue()[0].length()))) {
                sb.append(entry.getValue()[1]);
                currentIndex = entry.getKey() + entry.getValue()[0].length();
            } else {
                currentIndex = entry.getKey();
            }
        }
        if(currentIndex < S.length()) {
            sb.append(S.substring(currentIndex, S.length()));
        }

        return sb.toString();
    }

    public static void main(String args[]) {
        String S = "vmokgggqzp";
        int[] indexes = {3, 5, 1};
        String[] sources = {"kg","ggq","mo"};
        String[] targets = {"s","so","bfr"};
        System.out.println(findReplaceString(S, indexes, sources, targets));
    }
}
