package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
public class DivideArray {
    public static boolean isPossibleDivide(int[] nums, int k) {
        if(nums == null || nums.length % k != 0) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int curr: nums) {
            map.put(curr, map.getOrDefault(curr, 0) + 1);
        }
        Queue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((entry1, entry2) -> entry1.getKey() - entry2.getKey());
        queue.addAll(map.entrySet());


        while (!queue.isEmpty()) {
            if(queue.size() < k) {
                return false;
            }
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
            int prev = -1;
            for(int i=0;i<k;i++) {
                Map.Entry<Integer, Integer> curr = queue.poll();
                if(i != 0 && prev != curr.getKey() - 1) {
                    return false;
                }
                curr.setValue(curr.getValue() - 1);
                if(curr.getValue() > 0) {
                    list.add(curr);
                }
                prev = curr.getKey();
            }
            queue.addAll(list);
        }


        return true;
    }

    public static void main(String args[]) {
        int[] arr = {3,2,1,2,3,4,3,4,5,9,10,11};
        isPossibleDivide(arr, 3);
    }
}
