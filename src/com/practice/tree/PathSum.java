package com.practice.tree;

import java.util.*;
//https://leetcode.com/problems/path-sum-iv/
public class PathSum {
    class Pair{
        int num;
        int index;
        Pair(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }
    HashMap<Integer, Pair> map = new HashMap<>();
    public int pathSum(int[] nums) {
        for(int i=0;i<nums.length;i++) {
            map.put(nums[i] / 10, new Pair(i, nums[i]));
        }
        pathUtil(nums, 0, nums.length, 0);
        return answer;
    }
    Integer answer = new Integer(0);
    private void pathUtil(int[] nums, int currentIndex, int numSize, int sum) {
        if(currentIndex > numSize - 1) {
            return;
        }
        int level = nums[currentIndex] / 100;
        int position = (nums[currentIndex] /10) % 10;

        int left = (level + 1) * 10 + (2*position - 1);
        int right = (level + 1) * 10 + (2* position);
        //leaf node
        if(!map.containsKey(left) && !map.containsKey(right)) {
            sum = sum + nums[currentIndex] % 10;
            answer = answer + sum;
            return;
        }
        //left
        if(map.containsKey(left)) {
            pathUtil(nums,  map.get(left).index, numSize, sum + nums[currentIndex]%10);
        }
        if(map.containsKey(right)) {
            pathUtil(nums,  map.get(right).index, numSize, sum + nums[currentIndex]%10);
        }
    }

    public static void main(String args[]) {
        PathSum pathSum = new PathSum();
        int[] nums = {113, 215, 221};
        System.out.print(pathSum.pathSum(nums));
    }
}
