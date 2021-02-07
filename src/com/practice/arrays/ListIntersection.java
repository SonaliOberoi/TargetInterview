package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/interval-list-intersections/
public class ListIntersection {

    //A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
    public static List<int[]> intervalIntersection(int[][] A, int[][] B) {
        if (A == null || B == null) {
            return null;
        }

        List<int[]> intersection = new ArrayList<>();
        int i = 0,j = 0;
        while(i < A.length && j < B.length) {
            int aStart = A[i][0];
            int aEnd = A[i][1];

            int bStart = B[j][0];
            int bEnd = B[j][1];

            if (bStart == aStart && bEnd == aEnd) {
                i++;
                j++;
                int[] add = {aStart, aEnd};
                intersection.add(add);
                continue;
            }
            if (bStart > aEnd) {
                i++;
                continue;
            } else if (aStart > bEnd) {
                j++;
                continue;
            } else if (bStart > aStart && bStart < aEnd) {
                i++;
                int[] add = {bStart, aEnd};
                intersection.add(add);
                continue;
            } else {
                j++;
                int[] add = {aStart, bEnd};
                intersection.add(add);
                continue;
            }
        }

        return intersection;
    }

    public static void main(String args[]) {
        int[][] A= {{0,2}, {5,10}, {13, 23}, {24,25}};
        int[][] B = {{1,5},{8,12},{15,24},{25,26}};
        intervalIntersection(A, B);
    }

}
