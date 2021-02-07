package com.practice.dequeue;

import java.util.*;

//https://leetcode.com/problems/constrained-subsequence-sum/discuss/605822/Java-Decreasing-Monotonic-Queue-Clean-code-O(N)
public class ConstrainedSum {
    public static int constrainedSubsetSum(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        Deque<Integer> deque = new LinkedList<>();
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int max = Math.max(0, deque.isEmpty() ? 0 : dp[deque.peekFirst()]);
            dp[i] = arr[i] + max;
            ans = Math.max(ans, dp[i]);
            while (!deque.isEmpty() && dp[i] >= dp[deque.peekLast()]) { // If dp[i] >= deque.peekLast() -> Can discard the tail since it's useless
                deque.pollLast();
            }
            deque.addLast(i);
            if (i - deque.peekFirst() + 1 > k) { // remove the last element of range k
                deque.removeFirst();
            }
        }
        return ans;
    }

    public static int constrainedSubsetSumDP(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = Math.max(i - k, 0); j < i; j++) { // choose the max element in latest k elements, it's in range [i-k, i-1]
                max = Math.max(max, dp[j]);
            }
            dp[i] = arr[i] + max;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String args[]) {
        int[] arr = { 10,-2,-10,-5,20};
        constrainedSubsetSum(arr, 2);
    }
}
