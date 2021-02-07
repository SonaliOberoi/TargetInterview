package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
public class ShortesSubArray {

    public int shortestSubarray(int[] A, int K) {
        if(A == null) {
            return -1;
        }
        if(A.length == 1) {
            if( A[0] >= K) {
                return 1;
            } else {
                return -1;
            }
        }
        Map<Integer, Integer> map = new TreeMap<>();
        map.put(0, 0);
        int answer = -1;
        int currentSum = 0;
        for(int i=0;i<A.length;i++) {
            currentSum = currentSum + A[i];
            int requiredSum = currentSum - K;
            Integer indexOfRequiredSum = map.get(requiredSum);
            if(indexOfRequiredSum != null) {
                if(answer == -1) {
                    answer = i - indexOfRequiredSum;
                }else {
                    answer = Math.min(answer, i - indexOfRequiredSum);
                }
            }
            Map<Integer, Integer> tempMap = ((TreeMap<Integer, Integer>) map).headMap(requiredSum, false);
            if(tempMap != null && tempMap.size() > 0) {
                Set keys = tempMap.keySet();
                for (Iterator iterator = keys.iterator(); iterator.hasNext();) {
                    Integer key = (Integer) iterator.next();
                    Integer index = (Integer) map.get(key);
                    if(answer == -1) {
                        answer = i - index + 1;
                    }else {
                        answer = Math.min(answer, i - index + 1);
                    }
                }

            }
            map.put(currentSum, i);
        }

        return answer;
    }

    public static void main(String args[]) {
        ShortesSubArray shortesSubArray = new ShortesSubArray();
        int[] arr = {17,85,93,-45,-21};
        System.out.println(shortesSubArray.shortestSubarray(arr, 150));
    }
}
