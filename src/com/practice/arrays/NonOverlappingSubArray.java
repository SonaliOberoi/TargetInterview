package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/submissions/
public class NonOverlappingSubArray {
    // left[i] is the size of the min size subarray in arr[0, i] that sums up to target. Is Integer.MAX_VALUE if there is no subarray that sums up to target.
// right[i] is the size of the min size subarray in arr[i, arr.length - 1] that sums up to target.
// for (int i = 1; i < arr.length; ++i) result = Math.min(result, left[i - 1] + right[i]);
    public int minSumOfLengths(int[] arr, int target) {
        int[] left = new int[arr.length];
        int sum = 0, best = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < arr.length; ++i) {
            sum += arr[i];
            if (map.containsKey(sum - target)) {
                best = Math.min(best, i - map.get(sum - target));
            }
            left[i] = best;
            map.put(sum, i);
        }

        int[] right = new int[arr.length];
        sum = 0; best = Integer.MAX_VALUE;
        map = new HashMap<>();
        map.put(0, arr.length);
        for (int i = arr.length - 1; i >= 0; --i) {
            sum += arr[i];
            if (map.containsKey(sum - target)) {
                best = Math.min(best, map.get(sum - target) - i);
            }
            right[i] = best;
            map.put(sum, i);
        }

        int result = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; ++i) {
            if (left[i - 1] != Integer.MAX_VALUE && right[i] != Integer.MAX_VALUE) {
                result = Math.min(result, left[i - 1] + right[i]);
            }
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
