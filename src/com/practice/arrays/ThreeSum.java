package com.practice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length < 3) {
            return Collections.EMPTY_LIST;
        } else if(nums.length == 3) {
            if(nums[0] + nums[1] + nums[2] == 0) {
                List<List<Integer>> output = new ArrayList<>();
                List<Integer> list = new ArrayList<>();
                list.add(nums[0]);
                list.add(nums[1]);
                list.add(nums[2]);
                output.add(list);
                return output;
            } else {
                return Collections.EMPTY_LIST;
            }
        }
        List<List<Integer>> output = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length - 1;i++){
            int current = nums[i];
            int required = 0 - nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while(left != right) {
                if(nums[left] + nums[right] == required) {
                    List<Integer> list = new ArrayList<>();
                    list.add(current);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    output.add(list);
                    break;
                } else if(nums[left] + nums[right] > required) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return output;
    }
}
