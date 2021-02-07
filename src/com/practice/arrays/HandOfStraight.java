package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/hand-of-straights/
public class HandOfStraight {
    public static boolean isNStraightHand(int[] hand, int W) {
        if(hand == null || hand.length < 1 || hand.length % W != 0) {
            return false;
        }
        if(W == 1) {
            return true;
        }
        TreeMap<Integer, Integer> map = new TreeMap();
        for(int curr: hand) {
            int count = map.getOrDefault(curr, 0);
            map.put(curr, count + 1);
        }
        int count = 1;
        Integer previous = null;
        while(map.size() > 0) {
            if(count == 1 || count == W + 1) {
                previous = map.firstKey();
                int num =  map.get(previous);
                if(num == 1) {
                    map.remove(previous);
                } else {
                    map.put(previous, num - 1);
                }
                count = 1;
            } else {
                Integer ceilingKey = map.higherKey(previous);
                if(ceilingKey == null) {
                    return false;
                }
                if(ceilingKey - previous != 1) {
                    return false;
                }
                int num =  map.get(ceilingKey);
                if(num == 1) {
                    map.remove(ceilingKey);
                } else {
                    map.put(ceilingKey, num - 1);
                }
                previous = ceilingKey;
            }

            count++;
        }
        return false;
    }
    public static void main(String args[]) {
        int[] arr = {1,2,3,6,2,3,4,7,8};
        isNStraightHand(arr, 3);
    }
}
