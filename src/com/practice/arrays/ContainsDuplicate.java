package com.practice.arrays;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.*;

//https://leetcode.com/problems/contains-duplicate-iii/
public class ContainsDuplicate {
    List<Integer> list = new ArrayList<>();
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {

        if(nums == null || nums.length < 1 ) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(nums[0], 0);

        for(int i=1;i<nums.length;i++) {
            int requiredSum = Math.abs(t - nums[i]);
            Map<Integer, Integer> ceilingMap = map.tailMap(requiredSum, true);
            Iterator it = ceilingMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if((Integer)pair.getValue() - i <= k) {
                    return true;
                }
                it.remove(); // avoids a ConcurrentModificationException
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String args[]) {
        int[] arr = {1,5,9,1,5,9};
        System.out.print(containsNearbyAlmostDuplicate(arr, 2, 3));
    }
}
