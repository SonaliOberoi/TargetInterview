package com.practice.misc;

import java.util.*;
import java.util.stream.IntStream;

//https://leetcode.com/problems/my-calendar-ii/
class MyCalendarTwo {

    TreeMap<Integer, Integer> calendar = new TreeMap<>();
    TreeMap<Integer, Interval> calendar2 = new TreeMap<>();

    Map<String, Integer> frequencyMap = new HashMap();

    public MyCalendarTwo() {

    }

    public boolean bookOne(int start, int end) {
        Integer prev = calendar.floorKey(start),
                next = calendar.ceilingKey(start);
        if ((prev == null || calendar.get(prev) <= start) &&
                (next == null || end <= next)) {
            calendar.put(start, end);
            return true;
        }
        return false;

    }

    public int book(int start, int end) {
        int sum =calendar.values().parallelStream().reduce((a,b)->a+b).get();
        calendar.headMap(12).clear();

        Integer min = calendar2.ceilingKey(end);
        //when start and end is less then the very first range
        //for instance our calendar had [10, 20) [20, 30) and we try to insert [1-5)
        if(min == null) {
            calendar2.put(start, new Interval(start, end, 1));
            return 1;
        }

        Integer frequency = Integer.valueOf(1);
        calendar2.forEach((k, v) -> {
            if(start <= v.start && end > v.start && end < v.end) {
                int freq = v.freq;
                calendar2.remove(start);
                if(start == v.start) {
                    calendar2.put(start, new Interval(start, end, freq + 1));
                    calendar2.put(end, new Interval(end, v.end, freq));
                } else {
                    calendar2.put(start, new Interval(start, v.start, 1));
                    calendar2.put(v.start, new Interval(v.start, end, freq++));
                    calendar2.put(end, new Interval(end, v.end, 1));
                }
            }
        });
        return frequency;
    }

    public static void main(String args[]) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        System.out.println(myCalendarTwo.bookOne(10,20));
        System.out.println(myCalendarTwo.bookOne(15,25));
        System.out.println(myCalendarTwo.bookOne(20,30));
        System.out.println(myCalendarTwo.bookOne(15, 18));

    }
    class Interval {
        int start;
        int end;
        int freq;
        Interval(int start, int end, int freq) {
            this.start = start;
            this.end = end;
            this.freq = freq;
        }
    }
}