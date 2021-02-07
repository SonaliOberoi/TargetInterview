package com.practice.dp;

import com.sun.source.tree.Tree;

import java.util.HashSet;
import java.util.*;

//https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
public class MaximumSumNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if(matrix == null || matrix.length < 1) {
            return 0;
        }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int answer = Integer.MIN_VALUE;
        for(int i=0;i<colLen;i++) {
            int[] temp = new int[rowLen];
            for(int j=i;j<colLen;j++) {
                for(int r=0;r<rowLen;r++) {
                    temp[r] = temp[r] + matrix[r][j];
                }
                answer = Math.max(answer, maxSumUtil(temp, k));
            }
        }

        return answer == Integer.MIN_VALUE ? k : answer;
    }
    private int maxSumUtil(int[] a, int k) {
        int max = Integer.MIN_VALUE;
        int sumj = 0;
        TreeSet<Integer> ts = new TreeSet();
        ts.add(0);

        for(int i=0;i<a.length;i++){
            sumj += a[i];
            Integer gap = ts.ceiling(sumj - k);
            if(gap != null) max = Math.max(max, sumj - gap);
            ts.add(sumj);
        }
        return max;
    }

   public static void main(String args[]) {
        MaximumSumNoLargerThanK maximumSumNoLargerThanK = new MaximumSumNoLargerThanK();
        int[][] matrix = {{2,2,-1}};
        System.out.println(maximumSumNoLargerThanK.maxSumSubmatrix(matrix, 0));
   }
}
