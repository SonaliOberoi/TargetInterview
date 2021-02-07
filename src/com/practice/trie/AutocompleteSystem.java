package com.practice.trie;

import java.util.*;

//https://leetcode.com/problems/design-search-autocomplete-system/
public class AutocompleteSystem {
    Node root;
    List<String> sentencesList;
    int lastIndex = -1;
    Map<String, Integer> sentenceCountMap;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Node();
        this.sentencesList = new ArrayList<>();
        sentenceCountMap = new HashMap<>();
        for(int i=0;i<sentences.length;i++) {
            sentencesList.add(sentences[i]);
            insert(sentences[i], i, times[i], 3);
            lastIndex++;
        }
    }

    private void insert(String string, int index, int count, int topN) {
        Node current = root;
        for(int i=0;i<string.length();i++) {
            Character character = string.charAt(i);
            if(current.children.containsKey(character)) {
                Node existingNode = current.children.get(character);
                PriorityQueue<TopNElements> existingPq = existingNode.pq;
                if(existingPq.size() < topN) {
                    existingPq.add(new TopNElements(count, index));
                } else {
                    TopNElements topElement = existingPq.peek();
                    if(count < topElement.count) {
                        continue;
                    } else if(count == topElement.count && sentencesList.get(topElement.index).compareTo(string) > 0){
                        existingPq.poll();
                        existingPq.add(new TopNElements(count, index));
                    } else {
                        existingPq.poll();
                        existingPq.add(new TopNElements(count, index));
                    }
                }
                current = current.children.get(character);
            } else {
                Node newNode = new Node();
                newNode.pq.add(new TopNElements(count, index));
                current.children.put(character, newNode);
                current = newNode;
            }
        }
        current.isEndOfWord = true;
    }

    String currentWord = "";
    private List<String> search(String str) {
       Node current = root;
       for(int i=0;i<str.length();i++) {
           char ch = str.charAt(i);
           if(current.children.containsKey(ch)) {
               current = current.children.get(ch);
           } else {
               return Collections.EMPTY_LIST;
           }
       }
       PriorityQueue<TopNElements> pq = new PriorityQueue<>(current.pq);
       List<String> list = new ArrayList<>();
       while (!pq.isEmpty()) {
           list.add(sentencesList.get(pq.poll().index));
       }
       Collections.reverse(list);
       return list;
    }

    public List<String> input(char c) {
        if(c == '#') {
            insert(currentWord, 0, 3, 3);
            currentWord = "";
            return Collections.EMPTY_LIST;
        }
        currentWord = currentWord + c;
        return search(currentWord);
    }

    class Node {
        Map<Character, Node> children;
        PriorityQueue<TopNElements> pq;
        boolean isEndOfWord;
        Node() {
            this.isEndOfWord = false;
            this.children = new HashMap<>();
            this.pq = new PriorityQueue<>(3, (e1, e2) -> {
                return  e1.count - e2.count;
            });
        }
    }

    class TopNElements {
        int count,index;
        TopNElements(int count, int index) {
            this.count = count;
            this.index = index;
        }
    }

    public static void main(String args[]) {
        String[] sentences = {"i love you","island","iroman","i love leetcode"};
        int[] times = {5,3,2,2};
        AutocompleteSystem autocompleteSystem = new AutocompleteSystem(sentences, times);
        autocompleteSystem.input('i');
        autocompleteSystem.input(' ');
        autocompleteSystem.input('a');
        autocompleteSystem.input('#');
    }
}
