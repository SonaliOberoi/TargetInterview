package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/top-k-frequent-elements/
public class TopKElements {

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int current: nums) {
            map.put(current, map.getOrDefault(current, 0) + 1);
        }

        Queue<Integer> queue = new PriorityQueue<Integer>((element1, element2) -> {
           return map.get(element1) - map.get(element2);
        });

        int[] top = new int[k];
        map.forEach((key, value) -> {
           queue.add(key);
           if(queue.size() > k) {
               queue.poll();
           }
        });

        for(int i=0;i<k;i++) {
            top[i] = queue.poll();
        }

        return top;
    }


    public static void main(String args[]) {
        TopKElements topKElements = new TopKElements();
        int[] arr = {1,1,2,2,2,2,3,3,3,3,3,3,4,5,5,5};
        System.out.println(topKElements.topKFrequent(arr, 3).toString());
    }
}
