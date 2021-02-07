package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/minimum-height-trees/
public class MinimumHeightTree {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> answer = new LinkedList<>();
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for(int i=0;i<edges.length;i++) {
            int to = edges[i][1];
            int from = edges[i][0];
            if(graph.containsKey(from)) {
                graph.get(from).add(to);
                indegree.put(from, graph.get(from).size());
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(to);
                graph.put(from, set);
                indegree.put(from, graph.get(from).size());
            }
            if(graph.containsKey(to)) {
                graph.get(to).add(from);
                indegree.put(to, graph.get(to).size());
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(from);
                graph.put(to, set);
                indegree.put(to, graph.get(to).size());
            }
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        indegree.forEach((k,v) -> {
            if(v == 1) {
                queue.offer(k);
            }
        });
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for(int i=0;i<size;i++) {
                int current = queue.poll();
                visited.add(current);
                for(Integer neighbour: graph.get(current)) {
                    if(visited.contains(neighbour)) {
                        continue;
                    }
                    indegree.put(neighbour, indegree.get(neighbour) - 1);
                    if(indegree.get(neighbour) == 1) {
                        queue.offer(neighbour);
                    }
                }
            }
            answer = temp;

        }
        return answer;
    }

    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        int[] degree = new int[n];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(i, new HashSet<>());
        for (int[] e : edges) {
            map.get(e[0]).add(e[1]);
            map.get(e[1]).add(e[0]);
            degree[e[0]]++;
            degree[e[1]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) q.offer(i);
        }
        while (!q.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                list.add(cur);
                for (int parent : map.get(cur)) {
                    degree[parent]--;
                    if (degree[parent]== 1) q.offer(parent);
                }
            }
            res = list;
        }
        return res;
    }
}
