package com.practice.trie;

import java.util.*;

//https://leetcode.com/problems/stream-of-characters/
public class StreamChecker {
    class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        TrieNode() {
            children = new HashMap<>();
            this.endOfWord = false;
        }
    }
    TrieNode root;
    int MAX_QUEUE_SIZE = Integer.MIN_VALUE;
    public StreamChecker(String[] words) {
        root = new TrieNode();
        for(String word: words) {
            if(word.length() > MAX_QUEUE_SIZE) {
                MAX_QUEUE_SIZE = word.length() + 1;
            }
            insert(word);
        }
    }

    public void insert(String word) {
        TrieNode current = root;
        for (int i = word.length() - 1; i >= 0; i--) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfWord = true;
    }

    public void find(String word) {
        TrieNode current = root;
        for(int i = 0;i<word.length();i++) {
            if(current.children.get(word.charAt(i)) != null) {
                current = current.children.get(word.charAt(i));
            } else {
                System.out.println(word + " word not found");
                return;
            }
        }
        if(current.endOfWord) {
            System.out.println(word + " word found");
        } else {
            System.out.println(word + " word not found");
        }
    }

    StringBuilder sb = new StringBuilder();
    public boolean query(char letter) {
        boolean result = false;
        sb.append(letter);
        TrieNode current = root;
        for(int i = sb.length() - 1;i>=0;i--) {
            Character character = sb.charAt(i);
            current = current.children.get(character);
            if(current != null && current.endOfWord) {
                return true;
            }
            if(current == null) {
                return false;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        String[] words = {"cd", "f", "kl", "lk","cdef", "fk", "fish"};
        String[] words2 = {"ab","ba","aaab","baa"};
        StreamChecker streamChecker = new StreamChecker(words2);
        streamChecker.find("f");
        streamChecker.find("c");
        streamChecker.find("cde");
        streamChecker.find("ckl");
        streamChecker.find("cd");

        //query
        /*System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('c'));
        System.out.println(streamChecker.query('d'));
        System.out.println(streamChecker.query('f'));
        System.out.println(streamChecker.query('i'));*/

        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));
        System.out.println(streamChecker.query('a'));
        System.out.println(streamChecker.query('b'));

    }
}
