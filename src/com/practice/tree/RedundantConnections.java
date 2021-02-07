package com.practice.tree;

import java.util.*;

//https://leetcode.com/problems/redundant-connection-ii/
public class RedundantConnections {

    public int[] findRedundantDirectedConnection(int[][] edges) {

        if(edges == null || edges.length == 1) {
            return new int[0];
        }

        Set<Integer> masterSet = new HashSet();
        Set<Integer> childSet = new HashSet();
        Map<Integer, List<Integer>> graph = new HashMap();
        for(int[] edge: edges) {
           int parent = edge[0];
           int child = edge[1];
           masterSet.add(parent);
           masterSet.add(child);
           childSet.add(child);
           //if(parentSet.contains())
           List<Integer> list = graph.get(parent);
           if (list == null || list.isEmpty()) {
               list = new ArrayList<>();
           }
           list.add(child);
           graph.put(parent, list);
        }

        if(masterSet.size() == childSet.size()) {

        }

        Set<Integer> visited = new HashSet();
        Queue<Integer> queue = new LinkedList<>();
        masterSet.removeAll(childSet);
        int root = masterSet.stream().findFirst().get();
        queue.offer(root);
        visited.add(root);
        int[] answer = new int[2];

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0;i<size;i++) {
                int current = queue.poll();
                List<Integer> children = graph.get(current);
                if(children == null || children.isEmpty()) {
                    continue;
                }
                for(Integer  childElement: children) {
                    if(visited.contains(childElement)) {
                        answer[0] = current;
                        answer[1] = childElement;
                        return answer;
                    } else {
                        visited.add(childElement);
                        queue.offer(childElement);
                    }
                }
            }
        }
        return answer;
    }

    public static void main(String args[]) {
        RedundantConnections redundantConnections = new RedundantConnections();
        int[][] edges = {{1,2},{2,3},{3,4},{4,1},{1,5}};
        System.out.println(Arrays.toString(redundantConnections.findRedundantDirectedConnection(edges)));
    }
}
