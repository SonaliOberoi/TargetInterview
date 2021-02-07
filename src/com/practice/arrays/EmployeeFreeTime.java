package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/employee-free-time/
public class EmployeeFreeTime {
    class Interval {
        public int start;
        public int end;

        public Interval() {}

        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> totalList = new ArrayList<>();
        for(List<Interval> current: schedule) {
            totalList.addAll(current);
        }
        Collections.sort(totalList, (a, b) -> a.start - b.start);

        List<Interval> mergedList = new ArrayList<>();
        mergedList.add(totalList.get(0));
        for(int i = 1;i<totalList.size();i++) {
            int mergedListSize = mergedList.size();
            if(totalList.get(i).start >= mergedList.get(mergedListSize - 1).end) {
                int start = mergedList.get(mergedListSize - 1).start;
                mergedList.remove(mergedListSize - 1);
                mergedList.add(new Interval(start, totalList.get(i).end));
            } else {
                mergedList.add(totalList.get(i));
            }
        }
        if (mergedList.size() < 2) {
            return Collections.EMPTY_LIST;
        }
        List<Interval> answer = new ArrayList<>();
        int start = 1;
        for(Interval interval: mergedList) {
            int startInterval = interval.start;
            int endInterval = interval.end;
            if (start < startInterval) {
                answer.add(new Interval(start, startInterval));
            }
            start = endInterval;
        }

        return answer;
    }
}
