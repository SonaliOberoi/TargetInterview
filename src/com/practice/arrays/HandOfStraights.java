package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/hand-of-straights/
public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int W) {
        if(hand.length == 0) {
            return false;
        }
        if(hand.length % W != 0) {
            return false;
        }
        SortedMap<Integer, Integer> map = new TreeMap<>();
        for(int i=0;i<hand.length;i++) {
            map.put(hand[i], map.getOrDefault(hand[i], 0) + 1);
        }
        Set<Integer> tempSet = new HashSet<>();
        while (!map.isEmpty()) {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if(value - 1 == 0) {
                    map.remove(key);
                } else {
                    map.put(key, value - 1);
                }
                if(tempSet.contains(key)) {
                    return false;
                } else {
                    tempSet.add(key);
                    if(tempSet.size() == W) {
                        tempSet.clear();
                    }
                }
            }
        }
        return true;
    }
}
