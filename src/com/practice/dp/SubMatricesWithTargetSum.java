package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
public class SubMatricesWithTargetSum {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        if(matrix == null) {
            return 0;
        }
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        int answer = 0;

        for(int left = 0;left < colLen;left++) {
            int[] tempArr = new int[rowLen];
            for(int right = left; right < colLen; right++) {
                for(int row = 0; row < rowLen; row++) {
                    tempArr[row] = tempArr[row] + matrix[row][right];
                }
                answer = answer + subarraySum(tempArr, target);
            }
        }
        return answer;
    }

    public int subarraySum(int[] arr, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int ans = 0;
        int sumSoFar = 0;
        for(int i=0;i<arr.length ;i++) {
            sumSoFar = sumSoFar + arr[i];
            int requiredSum = sumSoFar - target;
            if(map.containsKey(requiredSum)) {
                ans = ans + map.get(requiredSum);
            }
            map.put(sumSoFar, map.get(sumSoFar) != null ? map.get(sumSoFar) + 1 : 1);
        }
        return ans;
    }

    public static void main(String args[]) {
        int[][] arr = {{0,1,0},{1,1,1},{0,1,0}};
        SubMatricesWithTargetSum subMatricesWithTargetSum = new SubMatricesWithTargetSum();
        System.out.println(subMatricesWithTargetSum.numSubmatrixSumTarget(arr, 0));
    }
}
