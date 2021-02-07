package com.practice.queue;

import java.util.*;

public class Stream {
    Map<Integer, Integer> map = new LinkedHashMap();
    Set<Integer> set = new HashSet<>();

    public Stream() {
        // do intialization if necessary
    }

    /**
     * Adds integer num to a stream of integers.
     */
    public void add(int num) {
        // write your code here
        if (map.containsKey(num)) {
            map.remove(num);
            set.add(num);
            return;
        } else if (set.contains(num)) {
            return;
        }
        map.put(num,  num);
    }

    /**
     *  Returns the first unique integer in the stream if found else return null.
     */
    public Integer getFirstUnique() {
        if (map.isEmpty()) {
            System.out.println("null");
            return null;
        }
        Integer val = map.entrySet().iterator().next().getKey();
        System.out.println(val);
        return  val;
    }

    public static void main(String args[]) {
        Stream s = new Stream();
        s.add(2);
        s.getFirstUnique(); // 2
        s.add(2);
        s.getFirstUnique(); // null
        s.add(3);
        s.getFirstUnique(); // 3
        s.add(4);
        s.getFirstUnique(); // 3
        s.add(3);
        s.getFirstUnique(); // 4
    }
}
