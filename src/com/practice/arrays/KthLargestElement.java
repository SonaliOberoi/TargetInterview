package com.practice.arrays;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://leetcode.com/problems/kth-largest-element-in-an-array/
public class KthLargestElement {
    //3,2,3,1,2,4,5,5,6
    // 1 2 2 3 3 4 5 5 6
    //k=5 (3)
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length < k) {
            return -1;
        }
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for(int i = 0;i<nums.length;i++) {
            pq.add(nums[i]);
            if(pq.size() > k) {
                pq.poll();
            }
        }
        int ans = -1;
        while (!pq.isEmpty()) {
            ans = pq.poll();
        }
        return ans;
    }

    public static void main(String args[]) {
        KthLargestElement kthSmallest =new KthLargestElement();
        int[] arr = {3,2,1,5,6,4};
        System.out.println(kthSmallest.findKthLargest(arr, 2));
    }
}
