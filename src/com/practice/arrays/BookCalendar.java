package com.practice.arrays;

import java.util.*;

public class BookCalendar {
    class Helper {
        int count;
        int endTime;
        Helper(int count, int endTime) {
            this.count = count;
            this.endTime = endTime;
        }
    }
    Map<Integer, Helper> map = new HashMap<>();
    public boolean book(int start, int end) {
        for(Integer key: map.keySet()) {
            Helper value = map.get(key);
            if(key <= start || (key > start && key < value.endTime)) {
                if(value.count >= 2) {
                    return false;
                } else {
                    if(start < key && end > value.endTime) {
                        map.put(start, new Helper(1, key));
                        map.put(key, new Helper(2, value.endTime));
                        map.put(value.endTime, new Helper(1, end));
                    } else if(start > key && end < value.endTime) {
                        map.put(key, new Helper(1, start));
                        map.put(start, new Helper(2, end));
                        map.put(end, new Helper(1, value.endTime));
                    }
                }
            } else {
                map.put(start, new Helper(1, end));
            }
        }
        return true;
    }
}
