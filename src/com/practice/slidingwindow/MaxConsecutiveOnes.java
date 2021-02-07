package com.practice.slidingwindow;

import java.util.*;

//https://leetcode.com/problems/max-consecutive-ones-iii/
public class MaxConsecutiveOnes {
    class Solution {
        public int longestOnes(int[] A, int K) {
            int left = 0, right;
            for (right = 0; right < A.length; right++) {
                // If we included a zero in the window we reduce the value of K.
                // Since K is the maximum zeros allowed in a window.
                if (A[right] == 0) K--;
                // A negative K denotes we have consumed all allowed flips and window has
                // more than allowed zeros, thus increment left pointer by 1 to keep the window size same.
                if (K < 0) {
                    // If the left element to be thrown out is zero we increase K.
                    if (A[left] == 0) K++;
                    left++;
                }
            }
            return right - left;
        }
    }

    //https://leetcode.com/problems/sliding-window-maximum/
    public static  int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < 1 || k > nums.length) {
            return nums;
        }
        Queue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return b[0] - a[0];
        });
        for(int i=0;i<k;i++) {
            int[] arr = {nums[i], i};
            pq.add(arr);
        }
        int[] answer = new int[nums.length - k + 1];
        answer[0] = pq.peek()[0];
        int j = 1;
        for(int i=k;i<nums.length;i++) {

            while(!pq.isEmpty() && pq.peek()[1] <  i - k + 1) {
                System.out.println(pq.peek()[1]);
                pq.poll();
            }

            int[] arr = {nums[i], i};
            pq.add(arr);
            answer[j] = pq.peek()[0];
            j++;
        }
        return answer;
    }
    public static void main(String args[]) {
        int[] arr ={1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxSlidingWindow(arr, 3)));
    }

}
