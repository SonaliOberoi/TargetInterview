package com.practice.trie;

import java.util.*;

//https://leetcode.com/problems/implement-magic-dictionary/
public class MagicDict {
    class Node {
        Map<Character, Node> children;
        boolean isEndOfWord;
        Node() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }
    Node root;
    /** Initialize your data structure here. */
    public MagicDict() {
        root = new Node();
    }

    public void buildDict(String[] dictionary) {
        for(String current: dictionary) {
            Node currentNode = root;
            for(int i=0;i<current.length();i++) {
                char ch = current.charAt(i);
                if(currentNode.children.containsKey(ch)) {
                    currentNode = currentNode.children.get(ch);
                } else {
                    Node node = new Node();
                    currentNode.children.put(ch, node);
                    currentNode = node;
                }
            }
            currentNode.isEndOfWord = true;
        }
    }

    public boolean search(String searchWord) {
        Node currentNode = root;
        for(int i=0;i<searchWord.length();i++) {
            char ch = searchWord.charAt(i);
            if(currentNode.children.containsKey(ch)) {
                currentNode = currentNode.children.get(ch);
                continue;
            } else {
                i++;
                for(Map.Entry<Character, Node> entry: currentNode.children.entrySet()) {
                    if (searchUtil(searchWord.substring(i, searchWord.length()), entry.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean searchUtil(String searchWord, Node node) {
        Node currentNode = node;
        for(int i=0;i<searchWord.length();i++) {
            char ch = searchWord.charAt(i);
            if(currentNode.children.containsKey(ch)) {
                currentNode = currentNode.children.get(ch);
            } else {
                return false;
            }
        }
        return currentNode.isEndOfWord;
    }

    public static void main(String args[]) {
        String[] arr = {"hello", "hallo", "leetcode"};
        MagicDict magicDict = new MagicDict();
        magicDict.buildDict(arr);
        System.out.println(magicDict.search("hello"));
        System.out.println(magicDict.search("hhllo"));
        System.out.println(magicDict.search("hell"));
        System.out.println(magicDict.search("leetcoded"));

    }
}
