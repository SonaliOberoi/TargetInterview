package com.practice.arrays;

import java.util.*;
import java.util.stream.Collectors;


//https://leetcode.com/problems/kth-largest-element-in-an-array/
public class KthLargestElementInArray {
    public static int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return -1;
        }

        Queue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> {
            return n1 - n2;
        });


       for(int current: nums) {
           minHeap.add((Integer) current);
           if(minHeap.size() > k) {
               minHeap.poll();
           }
       }

        return minHeap.poll();

    }

    public static void main(String args[]) {
        int[] arr = {3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(arr, 4));
    }
}
