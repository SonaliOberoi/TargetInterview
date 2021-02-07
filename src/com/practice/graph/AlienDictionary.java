package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/alien-dictionary/
public class AlienDictionary {

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap();
        Map<Character, Integer> indegree = new HashMap<>();
        Set<Character> processed = new HashSet<>();
        int n = words.length;
        for(int i = 0;i < n;i++) {
            if(i + 1 < n) {
                if(words[i].charAt(0) == words[i+1].charAt(0)) {
                    fillUpGraph(graph, indegree, words[i], words[i+1], processed);
                } else {
                    Character from = words[i].charAt(0);
                    Character to = words[i + 1].charAt(0);
                    processed.add(to);
                    processed.add(from);
                    if(!graph.containsKey(from)) {
                        Set<Character> set = new HashSet<>();
                        graph.put(from, set);
                    }
                    graph.get(from).add(to);
                    if(indegree.containsKey(to)) {
                        indegree.put(to, indegree.get(to) + 1);
                    } else {
                        indegree.put(to, 1);
                    }
                    continue;
                }
            }
        }
        if(processed.size() == 0 && words[0].length() == 1) {
            return words[0];
        }
        //topological search
        Queue<Character> queue = new LinkedList<>();
        for(Character character: processed) {
            if(!indegree.containsKey(character)) {
                queue.offer(character);
            }
        }
        String order = "";
        int count = 0;
        while(!queue.isEmpty()) {
            Character current = queue.poll();
            order = order + current;
            count++;
            if(!graph.containsKey(current)) {
                continue;
            }
            for(Character neighbor: graph.get(current)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if(indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        if(count != processed.size()) {
            return "";
        }
        return order;
    }

    private static void fillUpGraph(Map<Character, Set<Character>> graph, Map<Character, Integer> indegree,
                                    String word1, String word2, Set<Character> processed) {
        for(int j =1;j<word1.length();j++) {
            if(word1.charAt(j) != word2.charAt(j)) {
                Character from = word1.charAt(j);
                Character to = word2.charAt(j);
                processed.add(to);
                processed.add(from);
                if(!graph.containsKey(from)) {
                    Set<Character> set = new HashSet<>();
                    graph.put(from, set);
                }
                graph.get(from).add(to);
                if(indegree.containsKey(to)) {
                    indegree.put(to, indegree.get(to) + 1);
                } else {
                    indegree.put(to, 1);
                }
                return;
            }
        }

    }
    public static void main(String args[]) {
        AlienDictionary alienDictionary = new AlienDictionary();
        String[] dict = {"wrt", "wrf", "er", "ett", "rftt"};
        String [] dict2 = {"z", "z"};
        System.out.println(alienDictionary.alienOrder(dict2));
    }



}
