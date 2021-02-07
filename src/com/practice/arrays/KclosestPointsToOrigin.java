package com.practice.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/k-closest-points-to-origin/
public class KclosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length < K) {
            return null;
        }
        Map<int[], Integer> map = new HashMap<>();
        for(int[] currentCoordinate: points) {
            int x = currentCoordinate[0];
            int y = currentCoordinate[1];
            int dist = distance(x, 0, y, 0);
            map.put(new int[]{x, y}, dist);
        }
        Queue<int[]> maxHeap = new PriorityQueue<>((p1, p2) -> {
           return map.get(p2) - map.get(p1);
        });

        int[][] answer = new int[2][K];

        map.forEach((key, value) -> {
            maxHeap.add(key);
            if(maxHeap.size() > K) {
                maxHeap.poll();
            }
        });

        for (int i=0;i<K;i++) {
            answer[i] = maxHeap.poll();
        }
        return answer;
    }

    private int distance(int x1, int x2, int y1, int y2) {
        int xSquare = (x2 - x1)*(x2 - x1);
        int ySquare = (y2 -y1)*(y2 -y1);
        return  xSquare + ySquare;
    }


    public static void main(String args[]) {
        KclosestPointsToOrigin kclosestPointsToOrigin = new KclosestPointsToOrigin();

        int[][] arr = {{3,3},{5,-1},{-2,4}};

        kclosestPointsToOrigin.kClosest(arr, 2);
    }
}
