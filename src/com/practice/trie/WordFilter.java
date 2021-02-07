package com.practice.trie;

import java.util.HashMap;
import java.util.*;

//https://leetcode.com/problems/prefix-and-suffix-search/
public class WordFilter {
    Node prefixTrie;
    Map<String, Integer> map;
    public WordFilter(String[] words) {
        prefixTrie = new Node(' ');
        map = new HashMap<>();
        for(String current: words) {
            for(int i=current.length() - 1;i >=0;i--) {

            }
            insert(prefixTrie, current);

        }
    }

    public int f(String prefix, String suffix) {

        return map.getOrDefault("", -1);
    }

    private  void insert(Node root, String word) {
        Node current = root;
        for(int i=0;i<word.length();i++) {
            char ch = word.charAt(i);
            if(current.children.containsKey(ch)) {
                current = current.children.get(ch);
            } else {
                Node newNode = new Node(ch);
                current.children.put(ch, newNode);
                current = newNode;
            }
        }
        current.isEndOfWord = true;

    }

    class Node {
        Map<Character, Node> children;
        Character character;
        boolean isEndOfWord;

        Node(Character character) {
            this.character = character;
            this.children = new HashMap<>();
            this.isEndOfWord = false;
        }
    }

    public static void main(String args[]) {
        String[] words = {"apple"};
        WordFilter wordFilter = new WordFilter(words);
        System.out.println(wordFilter.f("a", "e"));
    }
}
