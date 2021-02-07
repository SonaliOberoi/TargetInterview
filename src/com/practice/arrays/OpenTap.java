package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/minimum-number-of-taps-to-open-to-water-a-garden/
public class OpenTap {
    public int minTaps(int n, int[] ranges) {
        if (n == 0) {
            return 0;
        }
        if (ranges == null || ranges.length < 1 || n < 0) {
            return -1;

        }
        TreeMap<Integer, Integer> interval = new TreeMap();
        for(int i=0;i<ranges.length;i++) {
            if(ranges[i] == 0) {
                continue;
            }
            int start = i - ranges[i] < 0 ? 0 : i - ranges[i];
            int end = i + ranges[i] > n ? n : i + ranges[i];
            if(interval.containsKey(start)) {
                if(interval.get(start) < end) {
                    interval.remove(start);
                    interval.put(start, end);
                }
                continue;
            }
            Integer floorKey = interval.floorKey(start);
            if(floorKey != null && floorKey != start) {
                if (start <= interval.get(floorKey) && end <= interval.get(floorKey)) {
                    continue;
                }
            }
            interval.put(start, end);
        }
        int[] arr = new int[n + 1];
        Arrays.fill(arr, 0);
        for(Integer key: interval.keySet()) {
            arr[key] = interval.get(key);
        }
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i=0;i<=n;i++) {
            if(arr[i] == 0) {
                continue;
            }
            int j=i+1;
            for(;j<= arr[i] && j <= n;j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }

        for(int i=0;i<=n;i++) {
            if(dp[i] == Integer.MAX_VALUE) {
                return -1;
            }
        }
        return dp[n];
    }

    public static void main(String args[]) {
        int[] arr = {3,4,1,1,0,0};
        int[] arr2 = {1,2,1,0,2,1,0,1};
        OpenTap openTap = new OpenTap();
        System.out.println(openTap.minTaps(5, arr));
    }
}
