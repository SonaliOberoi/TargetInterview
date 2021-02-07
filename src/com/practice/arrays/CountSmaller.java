package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/count-of-smaller-numbers-after-self/submissions/
public class CountSmaller {
    public static List<Integer> countSmaller(int[] nums) {
        if(nums == null || nums.length < 1) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> answer = new ArrayList();
        TreeMap<Integer, Integer> map = new TreeMap();
        for(int i=nums.length -1;i>=0;i--) {
            if(map.isEmpty()) {
                answer.add(0);
                map.put(nums[i], i);
                continue;
            }
            int count = map.headMap(nums[i]).values().stream().reduce(0, Integer::sum);
            //int count = map.headMap(nums[i]).size();
            answer.add(0, count);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return answer;
    }

    public static void main(String args[]) {
        int[] arr = {26,78,27,100,33,67,90,23,66,5,38,7,35,23,52,22,
                83,51,98,69,81,32,78,28,94,13,2,97,3,76,99,51,9,21,84,66,65,36,100,41};
        countSmaller(arr);
    }
}
