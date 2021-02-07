package com.practice.disjointsets;

import java.util.*;

//https://leetcode.com/problems/friend-circles/
public class FriendCircle {
    Map<Integer, Node> nodeMap = new HashMap<>();

    public int findCircleNum(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int rowLen = M.length;
        int colLen = M[0].length;
        for(int i=0;i<M.length;i++) {
            for(int j=0;j<colLen;j++) {
                if(M[i][j]== 1) {
                    union(i, j);
                }
            }
        }
        Map<Node, Integer> parentMap = new HashMap<>();
        nodeMap.forEach((k,v) -> {
            Node parent = v.parent;
            while (parent != parent.parent) {
                parent = parent.parent;
            }
            parentMap.put(parent, k);
        });
        return parentMap.size();
    }

    private boolean union(int a, int b) {
        Node parent1 = find(a);
        Node parent2 = find(b);

        if(parent1 == parent2) {
            return false;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    private Node find(int a) {
        if (!nodeMap.containsKey(a)) {
            Node node = new Node(a,null);
            node.parent = node;
            nodeMap.put(a, node);
            return node;
        }
        Node node = nodeMap.get(a);
        if(node == node.parent) {
            return node;
        }
        node.parent = find(node.parent.data);
        return node.parent;
    }

    Map<Integer, Set<Integer>> graph = new HashMap<>();
    public int findCircleNumDFS(int[][] M) {
        if (M == null || M.length == 0) {
            return 0;
        }
        int rowLen = M.length;
        int colLen = M[0].length;
        for(int i=0;i<rowLen;i++) {
            for(int j=0;j<colLen;j++) {
                if(M[i][j]== 1) {
                    constructGraph(i, j);
                }
            }
        }
        int totalPeople = Math.max(rowLen, colLen);
        boolean[] visited = new boolean[totalPeople];
        int answer = 0;
        for(int i=0;i<totalPeople;i++) {
            if(!visited[i]) {
                dfs(visited, i);
                answer++;
            }
        }
        return answer;
    }

    private void dfs(boolean[] visited, int current) {
        if (!visited[current]) {
            visited[current] = true;
            for(Integer neighbor: graph.get(current)) {
                dfs(visited, neighbor);
            }
        }
    }

    public void constructGraph(int i, int j) {
        Set<Integer> fromItoJ = graph.getOrDefault(i, new HashSet<>());
        fromItoJ.add(j);
        graph.put(i, fromItoJ);

        Set<Integer> fromJtoI = graph.getOrDefault(j, new HashSet<>());
        fromJtoI.add(i);
        graph.put(j, fromJtoI);
    }

    class Node {
        int data;
        Node parent;
        Node(int data, Node parent) {
            this.data = data;
            this.parent = parent;
        }
    }
}
