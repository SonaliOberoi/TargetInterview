package com.practice.trie;

import java.util.*;

//https://leetcode.com/problems/longest-repeating-substring/discuss/332052/Java-Straightforward-Trie-solution-O(N2)
public class LongestRepeatingSubstring {
    TrieNode root;
    LongestRepeatingSubstring() {
        root = new TrieNode();
    }

    public int longestRepeatingSubstring(String S) {
        int result = 0;
        for(int i =0;i<S.length();i++) {
            TrieNode current = root;
            for(int j=i;j<S.length();j++) {
                char currentChar = S.charAt(j);
                if(current.children.containsKey(currentChar)) {
                    result = Math.max(result, j - i + 1);
                    current = current.children.get(currentChar);
                } else {
                    TrieNode newNode = new TrieNode();
                    current.children.put(currentChar, new TrieNode());
                    current = newNode;
                }
            }
        }
        return result;
    }

    public static void main(String args[]) {
        LongestRepeatingSubstring longestRepeatingSubstring = new LongestRepeatingSubstring();
        System.out.println(longestRepeatingSubstring.longestRepeatingSubstring("banana"));
    }


}
