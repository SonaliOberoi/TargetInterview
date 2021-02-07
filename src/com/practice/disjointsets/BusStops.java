package com.practice.disjointsets;

import java.util.*;

//https://leetcode.com/problems/bus-routes/
public class BusStops {

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if(routes == null || S == T) {
            return 0;
        }
        if(S == T) {
            return 1;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] currentRoute: routes ) {
            for(int i=0;i<currentRoute.length;i++) {
                fillMap(map, currentRoute, i);
            }
        }
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(S);
        visited.add(S);
        int answer = 1;
        while ((!queue.isEmpty())) {
            int size = queue.size();
            while (size > 0) {
                int current = queue.poll();
                Set<Integer> neighbours = map.get(current);
                for(int currentNeighbour: neighbours) {
                    if(currentNeighbour == T) {
                        return answer;
                    }
                    if(!visited.contains(currentNeighbour)) {
                        queue.add(currentNeighbour);
                        visited.add(currentNeighbour);
                    }
                }
                size--;
            }
            answer++;
        }
        return -1;
    }

    private void fillMap(Map<Integer, Set<Integer>> map, int[] routes, int keyIndex) {
        Set<Integer> set = map.getOrDefault(routes[keyIndex], new HashSet<>());
        for(int i=0;i<routes.length;i++) {
            if(!(i==keyIndex)) {
                set.add(routes[i]);
            }
        }
        map.put(routes[keyIndex], set);
    }



}
