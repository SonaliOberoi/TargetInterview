package com.practice.graph;

import java.util.*;
//https://leetcode.com/problems/sequence-reconstruction/
public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
        HashMap<Integer, Integer> indegree = new HashMap<Integer, Integer>();
        for (int i = 0; i < seqs.size(); ++i) {
            List<Integer> seq = seqs.get(i);
            if (seq.size() == 1) {
                int from = seq.get(0);
                if (!graph.containsKey(from)) {
                    graph.put(from, new ArrayList<Integer>());
                    indegree.put(from, 0);
                }
            } else {
                for (int j = 0; j < seq.size() - 1; ++j) {
                    int from = seq.get(j);
                    int to = seq.get(j + 1);
                    if (!graph.containsKey(from)) {
                        graph.put(from, new ArrayList<Integer>());
                        indegree.put(from, 0);
                    }
                    if (!graph.containsKey(to)) {
                        graph.put(to, new ArrayList<Integer>());
                        indegree.put(to, 0);
                    }
                    if (!graph.get(from).contains(to)) {
                        graph.get(from).add(to);
                        indegree.put(to, indegree.get(to) + 1);
                    }
                }
            }
        }
        if (graph.size() != org.length) return false; // Strictly checking
        Queue<Integer> queue = new LinkedList<Integer>();
        for (HashMap.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            if (queue.size() != 1) return false; // Strictly checking
            int from = queue.poll();
            if (from != org[index++]) return false; // Strictly checking
            List<Integer> list = graph.get(from);
            for (int i = 0; i < list.size(); ++i) {
                int to = list.get(i);
                indegree.put(to, indegree.get(to) - 1);
                if (indegree.get(to) == 0) {
                    queue.offer(to);
                }
            }
        }
        return index == org.length; // Strictly checking
    }
}
