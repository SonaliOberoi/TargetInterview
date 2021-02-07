package com.practice.backtracking;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//https://leetcode.com/problems/all-paths-from-source-to-target/
public class AllPaths {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if(graph == null || graph.length < 1) {
            return Collections.EMPTY_LIST;
        }

        Map<Integer, List<Integer>> map = new HashMap();
        int i =0;
        for(int[] curr: graph) {
            List<Integer> list = map.getOrDefault(i, new ArrayList());
            if(curr != null || curr.length > 0) {
                map.put(i, IntStream.of(curr).boxed().collect(Collectors.toList()));
            }
            i++;
        }

        dfs(map, graph.length - 1, new ArrayList<>(), 0);
        return answer;
    }
    List<List<Integer>> answer = new ArrayList();
    private void dfs(Map<Integer, List<Integer>> map, int destination, List<Integer> path, int current) {
        if(current == destination) {
            path.add(destination);
            answer.add(new ArrayList<>(path));
            System.out.println(Arrays.toString(path.toArray()));
            return;
        }
        path.add(current);
        for(int neighbour: map.getOrDefault(current, new ArrayList<>())) {

            dfs(map, destination, path, neighbour);
            path.remove(path.size() - 1);
        }
    }


    public static void main(String args[]) {
        int[][] graph = {{1,2},{3},{3},{}};
        AllPaths allPaths = new AllPaths();
        allPaths.allPathsSourceTarget(graph);
    }
}
