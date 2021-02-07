package com.practice.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    TrieNode(){
        children = new HashMap<>();
        this.isEndOfWord = false;
    }
}
