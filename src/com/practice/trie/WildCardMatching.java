package com.practice.trie;

import java.util.*;

public class WildCardMatching {
    class TrieNode {
        Map<Character, TrieNode> map;
        boolean isEndWord;
        public TrieNode() {
            map = new HashMap<>();
            isEndWord = false;
        }

    }

    TrieNode root;
    WildCardMatching() {
        this.root = new TrieNode();
    }

    public void insert(String string) {
        TrieNode current = root;
        for (int i=0;i<string.length();i++) {
            Character ch = string.charAt(i);
            if(current.map.containsKey(ch)) {
                current = current.map.get(ch);
                continue;
            } else {
                TrieNode temp = new TrieNode();
                current.map.put(ch, temp);
                current = temp;
            }
        }
        current.isEndWord = true;
    }

    public boolean searchUtil(String string, TrieNode current) {
        for(int i=0;i<string.length();i++) {
            Character ch = string.charAt(i);
            if(ch.equals('*')) {
                for(Character characters: current.map.keySet()) {
                    if (searchUtil(characters + string.substring(i + 1), current)) {
                        return true;
                    }
                }
            } else {
                if(current.map.containsKey(ch)) {
                    current = current.map.get(ch);
                } else {
                    return false;
                }
            }
        }
        return current.isEndWord;
    }

    public boolean search(String string) {
        return searchUtil(string, root);
    }


    public static void main(String args[]) {
        WildCardMatching wildCardMatching = new WildCardMatching();
        wildCardMatching.insert("abcabbde");
        wildCardMatching.insert("abc");
        wildCardMatching.insert("acc");
        wildCardMatching.insert("adcc");
        wildCardMatching.insert("adc");
        wildCardMatching.insert("tbac");
        wildCardMatching.insert("bbc");
        System.out.println(wildCardMatching.search("ab"));
        System.out.println(wildCardMatching.search("a***"));
        System.out.println(wildCardMatching.search("tb"));
        System.out.println(wildCardMatching.search("tb*c"));
        System.out.println(wildCardMatching.search("t**e"));
    }
}

