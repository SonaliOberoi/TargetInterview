package com.practice.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

//https://leetcode.com/problems/frog-jump/
public class FrogJump {

    public boolean canCross(int[] stones) {
        if (stones == null || stones[1] > 1) {
            return false;
        }
        int target = stones[stones.length - 1];
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> tempSet = new HashSet<>();
        tempSet.add(1);
        map.put(stones[1], tempSet);
        for (int i=2;i<stones.length;i++) {
            map.put(stones[i], new HashSet<>());
        }

        for(int i=1;i<stones.length;i++) {
            Set<Integer> set = map.get(stones[i]);
            if (set.isEmpty()) {
                return false;
            }
            for(Integer current: set) {
                updateMap(map, stones[i], current - 1);
                updateMap(map, stones[i], current);
                updateMap(map, stones[i], current + 1);
                if (map.get(target).size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateMap(Map<Integer, Set<Integer>> map, int key, int steps) {
        if(steps == 0) {
            return;
        }
        Set<Integer> integerSet = map.getOrDefault(key + steps, null);
        if (integerSet != null) {
            integerSet.add(steps);
            map.put(key + steps, integerSet);
        }
    }
}
