package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/word-break/solution/
public class WordBreak {
    private final TrieNode root;
    public WordBreak() {
        root = new TrieNode();
    }

    /**
     * Iterative implementation of insert into trie
     */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        //mark the current nodes endOfWord as true
        current.endOfWord = true;
    }

    //recursive sol
    public boolean wordBreakDfs(String s, Set<String> wordDict) {
        if (s==null || s.length() == 0) {
            return true;
        }
        for (int i = 1; i<= s.length();i++) {
            String prefix = s.substring(0, i);
            String suffix = s.substring(i);
            if (wordDict.contains(prefix) && wordBreakDfs(suffix, wordDict)) {
                return true;
            }
        }
        return false;
    }

    //leetcode dp
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String subStr = s.substring(j, i);
                if (dp[j] && wordDictSet.contains(subStr)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public static void main(String args[]) {
        String[] arr = {"cat", "cats", "and", "sand", "dog", "eye", "car"};
        wordBreak("catsanddog", List.of(arr));
        WordBreak wordBreak = new WordBreak();
        System.out.print(wordBreak.wordBreakDfs("catsanddog", new HashSet<>(List.of(arr))));
    }

    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }
}
