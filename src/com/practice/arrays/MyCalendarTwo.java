package com.practice.arrays;

import java.util.TreeMap;

class MyCalendarTwo {

    TreeMap<Integer, Integer> delta;
    public MyCalendarTwo() {
        delta = new TreeMap();
    }

    public boolean book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (int d: delta.values()) {
            active += d;
            if (active >= 3) {
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if (delta.get(start) == 0)
                    delta.remove(start);
                return false;
            }
        }
        return true;
    }

    public static void main(String args[]) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        myCalendarTwo.book(10, 20); // returns true
        myCalendarTwo.book(50, 60); // returns true
        myCalendarTwo.book(10, 40); // returns true
        myCalendarTwo.book(5, 15); // returns false
        myCalendarTwo.book(5, 10); // returns true
        myCalendarTwo.book(25, 55); // returns true
    }
}
