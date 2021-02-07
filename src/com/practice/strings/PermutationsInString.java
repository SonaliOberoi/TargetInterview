package com.practice.strings;

import java.util.*;

//https://leetcode.com/problems/permutation-in-string/
public class PermutationsInString {
    public boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s2 == null || s1.length() > s2.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0;i<s1.length();i++) {
            map.put(s1.charAt(i), map.getOrDefault(map.get(s1.charAt(i)), 0 + 1));
        }
        for(int i=0;i<s2.length() - s1.length();i++) {
            if(map.containsKey(s2.charAt(i))) {
                Map<Character, Integer> clone = new HashMap<>(map);
                if(clone.get(s2.charAt(i)) == 1) {
                    clone.remove(s2.charAt(i));
                } else {
                    clone.put(s2.charAt(i), clone.get(s2.charAt(i) - 1));
                }
                int k = i+1;
                while(clone.size() > 0) {
                    if(clone.get(s2.charAt(k)) == 1) {
                        clone.remove(s2.charAt(k));
                    } else {
                        break;
                    }
                }
                if(clone.size() == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
