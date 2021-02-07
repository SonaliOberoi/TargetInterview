package com.practice.arrays;

import java.util.Arrays;
import java.util.Collections;

//https://leetcode.com/problems/meeting-rooms/
public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals == null || intervals.length<0)
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        for(int i = 1;i<intervals.length;i++) {
            if(intervals[i][0] < intervals[i-1][0]) {
                return false;
            }
        }
        return true;
    }
}
