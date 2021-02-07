package com.practice.graph;

import java.util.*;

public class FlightItenary {
    public List<String> findItinerary(List<List<String>> tickets) {

        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for(List<String> t : tickets) {
            String src = t.get(0);
            String dest = t.get(1);
            PriorityQueue<String> queue = new PriorityQueue<>();
            if(graph.containsKey(src)){
                queue = graph.get(src);
            }
            queue.offer(dest);
            graph.put(src, queue);
        }

        List<String> path = new ArrayList<>();
        dfs(graph, path, "JFK");
        return path;

    }

    public static void dfs(Map<String, PriorityQueue<String>> graph, List<String> path, String cur) {
        PriorityQueue<String> queue = graph.get(cur);
        while(queue != null && !queue.isEmpty()) {
            String next = queue.poll();
            dfs(graph, path, next);
        }
        path.add(0,cur);
    }

    public static void main(String args[]) {
        String[][] str = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};
        List<List<String>> list = new ArrayList<>();
        for (String[] array : str) {
            list.add(Arrays.asList(array));
        }
        FlightItenary flightItenary = new FlightItenary();
        flightItenary.findItinerary(list);


    }
}
