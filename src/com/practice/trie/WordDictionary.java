package com.practice.trie;

import java.util.*;

//https://leetcode.com/problems/design-add-and-search-words-data-structure/
public class WordDictionary {
    class Node{
        Map<Character, Node> children;
        boolean isWord;
        Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node current = root;
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
        current.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if(word == null || word.length() == 0) {
            return false;
        }
        return searchUtil(root, word);
    }

    private boolean searchUtil(Node node, String word) {
        if(word == null || word.length() == 0) {
            return node.isWord;
        }
        char ch = word.charAt(0);
        boolean found = false;
        if( ch == '.') {
            for(Character key: node.children.keySet()) {
                found = searchUtil(node.children.get(key), word.substring(1));
                if (found) {
                    break;
                }
            }
        } else {
            if(!node.children.containsKey(ch)) {
                return false;
            } else {
                found = searchUtil(node.children.get(ch), word.substring(1));
            }
        }
        return found;
    }

    public static void  main(String args[]) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // return False
        System.out.println(wordDictionary.search("bad")); // return True
        System.out.println(wordDictionary.search(".ad")); // return True
        System.out.println(wordDictionary.search("b..")); // return True
        System.out.println(wordDictionary.search("b.x")); // return True
    }
}
