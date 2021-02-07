package com.practice.tree;

import java.util.*;

//https://leetcode.com/problems/time-needed-to-inform-all-employees/
public class InformEmployees {

    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if(n <= 1) {
            return informTime[0];
        }
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for (int i = 0; i< manager.length;i++) {
            int managerId = manager[i];
            List<Integer> list;
            if(tree.containsKey(managerId)) {
                list = tree.get(managerId);

            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            tree.put(managerId, list);
        }

        Map<Integer, Integer> managerTimingMap = new HashMap<>();
        for(int i=0; i<informTime.length; i++) {
            if(tree.containsKey(i)) {
                managerTimingMap.put(i, informTime[i]);
            }
        }
        int answer = 0;
        Queue<Integer> queue = new LinkedList<>();

        int root = headID;
        queue.offer(headID);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = 0;
            for(int i=0;i<size;i++) {
                int current = queue.poll();
                max = Math.max(max, managerTimingMap.getOrDefault(current, 0));
                if(tree.containsKey(current)) {
                    for(Integer child: tree.get(current)) {
                        queue.offer(child);
                    }
                }
            }
            answer = answer + max;
            System.out.println(answer);
        }

        return answer;
    }

    public int numOfMinutes2(int n, int headID, int[] manager, int[] informTime) {
        if(n <= 1) {
            return informTime[0];
        }
        Map<Integer, List<Integer>> graph = constructGraph(manager);

        Map<Integer, Integer> managerTimingMap = constructManageTimingMap(informTime, graph);
        dfs(graph, managerTimingMap, headID, 0);
        return finalAnswer;
    }

    int finalAnswer = 0;
    private void dfs(Map<Integer, List<Integer>> graph,  Map<Integer, Integer> managerTimingMap, int current, int pathTotal) {
        if(!graph.containsKey(current)) {
            finalAnswer = Math.max(finalAnswer, pathTotal);
            System.out.println(finalAnswer);
            return;
        }
        pathTotal = pathTotal + managerTimingMap.getOrDefault(current, 0);

        for(Integer neighbor: graph.get(current)) {
            dfs(graph, managerTimingMap, neighbor, pathTotal);
        }
    }

    private  Map<Integer, Integer> constructManageTimingMap(int[] informTime, Map<Integer, List<Integer>> graph) {
        Map<Integer, Integer> managerTimingMap = new HashMap<>();
        for(int i=0; i<informTime.length; i++) {
            if(graph.containsKey(i)) {
                managerTimingMap.put(i, informTime[i]);
            }
        }
        return managerTimingMap;
    }
    private Map<Integer, List<Integer>> constructGraph(int[] manager) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i< manager.length;i++) {
            int managerId = manager[i];
            List<Integer> list;
            if(graph.containsKey(managerId)) {
                list = graph.get(managerId);

            } else {
                list = new ArrayList<>();
            }
            list.add(i);
            graph.put(managerId, list);
        }
        return graph;
    }

    public static void main(String args[]) {
        //int[] manager = {5,9,6,10,-1,8,9,1,9,3,4};
        //int[] time = {0,213,0,253,686,170,975,0,261,309,337};
        int[] manager = {2,2,-1,2,2,2};
        int[] time = {0,0,1,0,0,0};
        InformEmployees informEmployees = new InformEmployees();
        informEmployees.numOfMinutes2(6, 2, manager, time);
        //System.out.println(numOfMinutes(11, 4, manager, time));
    }
}

