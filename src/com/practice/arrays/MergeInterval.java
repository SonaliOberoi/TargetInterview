package com.practice.arrays;

import java.util.*;
//https://leetcode.com/problems/merge-intervals/
public class MergeInterval {

    public static int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> list = new ArrayList<>();

        for (int[] current: intervals) {
            int start = current[0];
            int end = current[1];

            if (list.isEmpty()) {
                list.add(current);
            } else {
                int size = list.size();
                int lastEnd = list.get(size - 1)[1];
                int lastStart = list.get(size - 1)[0];
                if (start <= lastEnd && end > lastEnd) {
                    list.remove(size - 1);
                    int[] merged = {lastStart, end};
                    list.add(merged);
                }
                if(start > lastEnd) {
                    list.add(current);
                }

            }
        }

        int[][] answer = new int[list.size()][2];


        return answer;
    }

    public static void main(String args[]) {
        int[][] input = {{1,3}, {9,15}, {5,7}, {6, 10}, {2,8}};
        merge(input);


    }
}
