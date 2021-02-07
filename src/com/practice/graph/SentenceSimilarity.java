package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/sentence-similarity-ii/
public class SentenceSimilarity {
    public boolean areSentencesSimilarTwo(
            String[] words1, String[] words2, List<List<String>> pairs) {
        if(words1.length != words2.length) {
            return false;
        }
        if(pairs.size() == 0) {
            for(int i=0;i<words1.length;i++) {
                if(words1[i] != words2[i]) {
                    return false;
                }
            }
            return true;
        }
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> pair: pairs) {
            for (String p: pair) if (!graph.containsKey(p)) {
                graph.put(p, new ArrayList());
            }
            graph.get(pair.get(0)).add(pair.get(1));
            graph.get(pair.get(1)).add(pair.get(0));
        }

        boolean areSimilar = true;
        for(int i=0;i<words1.length;i++) {
            if(words1[i] == words2[i]) {
                continue;
            } else {

                if(dfs(graph, words1[i], words2[i], new HashSet<>())) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return areSimilar;
    }

    private boolean dfs(Map<String, List<String>> graph, String start,
                        String end, Set<String> visited) {
        if(start == end) {
            return true;
        }
        visited.add(start);
        List<String> neighbours = graph.get(start);
        for(String currentNeighbour: neighbours) {
            if(visited.contains(currentNeighbour)) {
                continue;
            }
            if(dfs(graph, currentNeighbour, end, visited)) {
                return true;
            }
        }
        return false;
    }


    public static void main(String args[]) {
        SentenceSimilarity similarWords = new SentenceSimilarity();
        String[] words1 = {"great", "acting", "skills"};
        String[] words2 = {"fine", "drama", "talent"};
        String[] pair1 = {"great", "good"};
        String[] pair2 = {"fine", "good"};
        String[] pair3 = {"acting","drama"};
        String[] pair4 = {"skills","talent"};
        List<List<String>> pairList = new ArrayList<>();
        pairList.add(Arrays.asList(pair1));
        pairList.add(Arrays.asList(pair2));
        pairList.add(Arrays.asList(pair3));
        pairList.add(Arrays.asList(pair4));
        String[][] pairs = {{"great", "good"},
                {"fin", "good"},
                {"acting","drama"},
                {"skills","talent"}};
        System.out.print(similarWords.areSentencesSimilarTwo(words1, words2, pairList));


    }
}
