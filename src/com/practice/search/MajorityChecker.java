package com.practice.search;

import java.util.*;
//https://leetcode.com/problems/online-majority-element-in-subarray/
public class MajorityChecker {
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[] arr;

    public MajorityChecker(int[] arr) {
        this.arr = arr;
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
    }

    private int getOccurrence(int left, int right, int a) {
        List<Integer> list = map.get(a);
        int i = Collections.binarySearch(list, left);
        if (i < 0) i = ~i;
        if (i == list.size()) return 0;
        int j = Collections.binarySearch(list, right);
        if (j < 0) j = ~j - 1;
        return j - i + 1;
    }

    private int getRandomNum(int l, int r) {
        Random rand = new Random();
        return rand.nextInt(r - l + 1) + l;
    }

    public int query(int left, int right, int threshold) {
        for (int i = 0; i < 10; i++) {
            int rand = getRandomNum(left, right);
            int a = arr[rand];
            if (getOccurrence(left, right, a) >= threshold)
                return a;
        }
        return -1;
    }

    public static void main(String args[]) {
        int[] arr = {1,1,2,2,1,1};
        MajorityChecker majorityChecker = new MajorityChecker(arr);
        majorityChecker.query(0,5,4);
        majorityChecker.query(0,3,3);
        majorityChecker.query(2,3,2);
    }
}
