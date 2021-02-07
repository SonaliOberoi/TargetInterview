package com.practice.arrays;

import java.util.*;

class LFUCache {

    Map<Integer, Temp> cache;
    TreeMap<Long, Integer> treeMap;
    int capacity;
    int timeStamp;

    public LFUCache(int capacity) {
        cache = new HashMap();
        treeMap = new TreeMap();
        this.capacity = capacity;
        this.timeStamp = 0;
    }

    public int get(int key) {
        Temp valArr = cache.get(key);
        timeStamp++;
        if(valArr != null) {
            int value = valArr.val;
            long timeStamp = valArr.time;
            treeMap.remove(timeStamp);
            long timeStampSystem = System.currentTimeMillis() + timeStamp;
            treeMap.put(timeStampSystem, key);
            cache.put(key, new Temp(value, timeStampSystem));
            System.out.println(value);
            return value;
        }
        System.out.println(-1);
        return -1;
    }

    public void put(int key, int value) {
        timeStamp++;
        if(cache.size() >= capacity) {
            Map.Entry<Long, Integer> entry = treeMap.firstEntry();
            int treeMapKey = entry.getValue();
            long time = entry.getKey();
            treeMap.remove(time);
            cache.remove(treeMapKey);
        }
        long time = System.currentTimeMillis() + timeStamp;
        cache.put(key, new Temp(value, time));
        treeMap.put(time, key);
    }
}

class Temp {
    int val;
    long time;
    Temp( int val, long time) {
        this.val = val;
        this.time = time;
    }

    public static void main(String args[]) {
        LFUCache lfu = new LFUCache(2);
        lfu.put(1, 1);
        lfu.put(2, 2);
        lfu.get(1);      // return 1
        lfu.put(3, 3);   // evicts key 2
        lfu.get(2);      // return -1 (not found)
        lfu.get(3);      // return 3
        lfu.put(4, 4);   // evicts key 1.
        lfu.get(1);      // return -1 (not found)
        lfu.get(3);      // return 3
        lfu.get(4);      // return 4
    }
}
