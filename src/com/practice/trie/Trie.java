package com.practice.trie;

import java.util.*;

//https://leetcode.com/problems/implement-trie-prefix-tree/
public class Trie {
    Node head;
    /** Initialize your data structure here. */
    public Trie() {
        head = new Node();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        Node current = head;
        for(int i =0;i<word.length();i++) {
            char ch = word.charAt(i);
            if(current.children.containsKey(ch)) {
                current = current.children.get(ch);
            } else {
                Node newNode = new Node();
                current.children.put(ch, newNode);
                current = newNode;
            }
        }
        current.isEndOfWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Node current = head;
        for(int i =0;i<word.length();i++) {
            char ch = word.charAt(i);
            if (current.children.containsKey(ch)) {
                current = current.children.get(ch);
            } else {
                return false;
            }
        }
        return current.isEndOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Node current = head;
        for(int i =0;i<prefix.length();i++) {
            char ch = prefix.charAt(i);
            if (current.children.containsKey(ch)) {
                current = current.children.get(ch);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("beer");
        trie.insert("jam");
        trie.insert("rental");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}

class Node{
    Map<Character, Node> children;
    boolean isEndOfWord;
    Node() {
        children = new HashMap<>();
        isEndOfWord = false;
    }
}