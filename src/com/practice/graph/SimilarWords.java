package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/sentence-similarity-ii/solution/
class SimilarWords {

        public boolean areSentencesSimilarTwo(
                String[] words1, String[] words2, String[][] pairs) {
            if (words1.length != words2.length) return false;
            Map<String, List<String>> graph = new HashMap();
            for (String[] pair: pairs) {
                for (String p: pair) if (!graph.containsKey(p)) {
                    graph.put(p, new ArrayList());
                }
                graph.get(pair[0]).add(pair[1]);
                graph.get(pair[1]).add(pair[0]);
            }

            for (int i = 0; i < words1.length; ++i) {
                String w1 = words1[i], w2 = words2[i];
                Stack<String> stack = new Stack();
                Set<String> visited = new HashSet();
                stack.push(w1);
                visited.add(w1);
                search: {
                    while (!stack.isEmpty()) {
                        String word = stack.pop();
                        if (word.equals(w2)) break search;
                        if (graph.containsKey(word)) {
                            for (String nei: graph.get(word)) {
                                if (!visited.contains(nei)) {
                                    stack.push(nei);
                                    visited.add(nei);
                                }
                            }
                        }
                    }
                    return false;
                }
            }
            return true;
        }


    public static void main(String args[]) {
        SimilarWords similarWords = new SimilarWords();
        String[] words1 = {"great", "acting", "skills"};
        String[] words2 = {"fine", "drama", "talent"};
        String[][] pairs = {{"great", "good"},
                            {"fine", "good"},
                            {"acting","drama"},
                            {"skills","talent"}};
        System.out.print(similarWords.areSentencesSimilarTwo(words1, words2, pairs));


    }
}