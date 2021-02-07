package com.practice.queue;

import java.util.PriorityQueue;

//https://leetcode.com/problems/minimum-number-of-refueling-stops/
public class MinimumRefuellingStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if(stations.length <= 0) {
            return (startFuel >= target ? 0 : -1);
        }
        if(startFuel < stations[0][0]) {
            return -1;
        }
        int ans = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(stations.length, (a,b) -> b - a);
        int currentFuel = startFuel;
        for(int i = 0;i<stations.length;i++) {

            currentFuel = i !=0 ? currentFuel - (stations[i][0] - stations[i - 1][0])  : currentFuel - stations[i][0];
            if(currentFuel <= 0) {
                queue.add(stations[i][1]);
                while (!queue.isEmpty() && currentFuel <= 0) {
                    currentFuel = currentFuel + queue.poll();
                    ans++;
                }
                if(currentFuel < 0) {
                    return -1;
                }
            } else {
                queue.add(stations[i][1]);
            }
        }
        int distanceLeft = target - stations[stations.length - 1][0];
        if(distanceLeft <= currentFuel) {
            return ans;
        } else {
            while (!queue.isEmpty() && distanceLeft > currentFuel) {
                currentFuel = currentFuel + queue.poll();
                ans++;
            }
        }
        if(distanceLeft <= currentFuel) {
            return ans;
        }
        return -1;
    }

    public static void main(String args[]) {
        MinimumRefuellingStops minimumRefuellingStops = new MinimumRefuellingStops();
        int[][] stations = {{25,27},{36,187},{140,186},{378,6},{492,202},{517,89},
                {579,234},{673,86},{808,53},{954,49}};
        minimumRefuellingStops.minRefuelStops(1000, 83, stations);
    }
}
