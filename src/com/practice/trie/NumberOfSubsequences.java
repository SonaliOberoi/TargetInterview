package com.practice.trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/number-of-matching-subsequences/
public class NumberOfSubsequences {

    class Node {
        Map<Character, Node> children;
        int index;
        Node(int index) {
            this.children = new HashMap<>();
            this.index = index;
        }
    }

    Node root = new Node(-10);

    boolean insertNodeAndCheckIfSubsequenceFound(String str, Map<Character, LinkedList<Integer>> sourceMap) {
        Node current = root;
        for (int i=0;i<str.length();i++) {
            char ch = str.charAt(i);
            if(current.children.containsKey(ch)) {
                current = current.children.get(ch);
            } else {
                int index;
                if (!sourceMap.containsKey(ch) || current.index == -1) {
                    index = -1;
                } else {
                    index = calculateIndex(current.index, sourceMap.get(ch));
                }
                Node newNode = new Node(index);
                current.children.put(ch, newNode);
                current = newNode;
            }
        }
        return current.index >= 0;
    }


    int calculateIndex(int previousIndex, LinkedList<Integer> list) {
        if (previousIndex > list.get(list.size() -1)) {
            return -1;
        }
        for(Integer current: list) {
            if(current > previousIndex) {
                return current;
            }
        }
        return -1;
    }

    public int numMatchingSubseq(String S, String[] words) {
        if(S == null || S.length() < 1) {
            return 0;
        }
        Map<Character, LinkedList<Integer>> sourceMap = fillMap(S);
        int answer = 0;
        for(String str: words) {
            boolean isSubsequence = insertNodeAndCheckIfSubsequenceFound(str, sourceMap);
            if (isSubsequence) {
                answer++;
            }
        }

        return answer;
    }

    private Map<Character, LinkedList<Integer>> fillMap(String string) {
        Map<Character, LinkedList<Integer>> sourceMap = new HashMap<>();
        for(int i=0;i<string.length();i++) {
            char ch = string.charAt(i);
            LinkedList<Integer> list = sourceMap.getOrDefault(ch, new LinkedList<>());
            list.add(i);
            sourceMap.put(ch, list);
        }
        return sourceMap;
    }

    public static void main(String args[]) {
        NumberOfSubsequences numberOfSubsequences = new NumberOfSubsequences();
        String[] words = {"a", "bb", "acd", "ace"};
        System.out.println(numberOfSubsequences.numMatchingSubseq("abcde", words));

    }
}
