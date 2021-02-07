package com.practice.queue;

import java.util.*;

public class LRUCache2 {
    Map<Integer, CacheValue> cacheMap = new HashMap<>();
    TreeMap<Long, Integer> timeStampMap = new TreeMap<>();
    int capacity;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        long timeStamp = System.currentTimeMillis();
        if (cacheMap.containsKey(key)) {
            CacheValue cacheValue = cacheMap.get(key);
            timeStampMap.remove(cacheValue.timestamp);
            timeStampMap.put(timeStamp, key);
            cacheMap.put(key, new CacheValue(cacheMap.get(key).val, timeStamp));
            return cacheMap.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        long timeStamp = System.currentTimeMillis();
        if (cacheMap.containsKey(key)) {
            CacheValue cacheValue = cacheMap.get(key);
            timeStampMap.remove(cacheValue.timestamp);
            timeStampMap.put(timeStamp, key);
            cacheMap.put(key, new CacheValue(value, timeStamp));
            return;
        }
        if (cacheMap.size() == capacity) {
            int removeKey  = timeStampMap.get(timeStampMap.firstKey());
            cacheMap.remove(removeKey);
            timeStampMap.remove(timeStampMap.firstKey());
        }
        timeStampMap.put(timeStamp, key);
        cacheMap.put(key, new CacheValue(value, timeStamp));
    }
    class CacheValue{
        int val;
        long timestamp;
        CacheValue(int val, long timestamp) {
            this.val = val;
            this.timestamp = timestamp;
        }
    }

    public static void main(String args[]) {
        LRUCache2 lruCache2 = new LRUCache2(2);
        lruCache2.put(1,1);
        lruCache2.put(2,2);
        System.out.println(lruCache2.get(1));
        lruCache2.put(3,3);
        System.out.println(lruCache2.get(2));
        lruCache2.put(4,4);
        System.out.println(lruCache2.get(1));
        System.out.println(lruCache2.get(3));
        System.out.println(lruCache2.get(4));
    }
}
