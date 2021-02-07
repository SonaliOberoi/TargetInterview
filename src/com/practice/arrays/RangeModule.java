package com.practice.arrays;

import java.util.*;

public class RangeModule {
    TreeMap<Integer, Integer> map;
    public RangeModule() {
        map = new TreeMap();
    }

    public void addRange(int left, int right) {
        Integer min = map.lowerKey(left);
        Integer max =  map.lowerKey(right);

        if(min != null && max == min) {
            map.remove(min);
            map.put(min, right);
            return;
        }
        if (min != null && max != min) {
            Integer lower = map.lowerKey(max);
            while (lower != null) {
                map.remove(lower);
            }
            map.put(min, right);
            return;
        }
        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        Integer min = map.lowerKey(left);
        Integer max =  map.lowerKey(right);
        return min != null && max != null;
    }

    public void removeRange(int left, int right) {

    }
}
