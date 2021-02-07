package com.practice.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

//https://leetcode.com/problems/find-k-th-smallest-pair-distance/
public class KthSmallest {
    public int smallestDistancePair(int[] nums, int k) {
        if(nums == null || nums.length < 1 ) {
            return -1;
        }
        Arrays.sort(nums);
        int numsLength = nums.length;
        int permutations = (numsLength *(numsLength - 1) )/2;
        if(k > permutations) {
            return -1;
        }

        int[] diffArr = new int[nums.length - 1];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0;i<nums.length - 1;i++) {
            diffArr[i] = nums[i+ 1] - nums[i];
        }
        int levelArr[] = new int[diffArr.length];
        levelArr[0] = numsLength - 1;
        for(int i = 1;i < diffArr.length;i++) {
            levelArr[i] = levelArr[i - 1] + levelArr[0] - i ;
        }
        //binary search to find at what level we will get our lowest number
        int level = binarySearch(levelArr, 0, levelArr.length - 1, k);
        k = level != 0 ? levelArr[level] - levelArr[level - 1] : k;

        for(int i=0;i<diffArr.length - level ;i++) {
            int diff = 0;
            for(int j=i;j<=i +level && j<diffArr.length;j++) {
                diff = diff + diffArr[j];
            }
            priorityQueue.offer(diff);
        }
        while (!priorityQueue.isEmpty()) {
            int current = priorityQueue.poll();
            k--;
            if(k==0) {
                return current;
            }
        }
        return diffArr[k + 1];
    }

    private int binarySearch(int[] arr, int start, int end, int k) {

        if(k <= arr[0]) {
            return 0;
        }
        while (start < end) {
            int mid = (start + end) /2;
            if(k <= arr[mid + 1] && k > arr[mid]) {
                return mid + 1;
            } else if(k > arr[mid + 1] && k > arr[mid]) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String args[]) {
        KthSmallest kthSmallest = new KthSmallest();
        int[] arr = {9,10,7,10,6,1,5,4,9,8};
        System.out.println(kthSmallest.smallestDistancePair(arr, 18));
    }
}
