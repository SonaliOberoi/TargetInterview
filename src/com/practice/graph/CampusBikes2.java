package com.practice.graph;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//https://leetcode.com/problems/campus-bikes-ii/discuss/303422/Python-Priority-Queue
public class CampusBikes2 {
    public int calDist(int[] worker, int[] bikes){
        return Math.abs(worker[0] - bikes[0]) + Math.abs(worker[1] - bikes[1]);
    }
    public int assignBikes(int[][] workers, int[][] bikes) {
        dfs(workers, bikes, new HashSet(), 0, 0);
        return min;
    }

    int min = Integer.MAX_VALUE;
    private void dfs(int[][] workers, int[][] bikes, Set used, int sum, int currentWorker) {
        if(currentWorker == workers.length) {
            min = Math.min(min, sum);
            System.out.println(min);
            return;
        }
        for(int j = 0; j < bikes.length; j++){
            if(used.contains(j)) {
                continue;
            }
            int dist = calDist(workers[currentWorker],bikes[j]);
            used.add(j);
            dfs(workers, bikes, used, sum + dist, currentWorker + 1);
            used.remove(j);
        }
    }
    public static void main(String args[]) {
        int[][] workers ={{0,0},{1,1},{2,0}};
        int[][] bikes = {{1,0},{2,2},{2,1}};
        CampusBikes2 campusBike = new CampusBikes2();
        System.out.print(campusBike.assignBikesPQ(workers, bikes));
    }

    public int assignBikesPQ(int[][] workers, int[][] bikes) {
        PriorityQueue<node> pq = new PriorityQueue<node>((a, b)->Integer.compare(a.cost, b.cost));
        HashSet<String> visited = new HashSet();
        pq.add(new node(0,0,0));
        while(!pq.isEmpty()) {
            node cur = pq.remove();
            if(cur.id == workers.length) {
                return cur.cost;
            }
            if(visited.contains(cur.id+"_"+cur.mask)) {
                continue;
            }
            visited.add(cur.id+"_"+cur.mask);
            for(int j=0;j<bikes.length;j++){
                if((cur.mask & (1 << j))== 0) {
                    pq.offer(new node(cur.id+1, cur.cost + get(workers, bikes, cur.id, j), cur.mask | 1<<j));
                }
            }
        }
        return -1;
    }

    public int get(int[][] workers, int[][] bikes, int i, int j){
        return Math.abs(workers[i][0] - bikes[j][0]) +  Math.abs(workers[i][1] - bikes[j][1]);
    }

    class node {
        int id;
        int cost;
        int mask;
        public node(int id, int cost, int m) {
            this.id = id;
            this.cost = cost;
            mask = m;
        }
    }

}
