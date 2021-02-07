package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/meeting-rooms-ii/
public class MinMeetingRooms {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Queue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];});
        for(int[] current: intervals) {
            if (queue.isEmpty()) {
                queue.add(current);
                continue;
            }
            if(queue.peek()[1] <= current[0]) {
                queue.poll();
            }
            queue.add(current);
        }
        return queue.size();
    }
}
