package com.practice.queue;

import java.util.*;

//https://leetcode.com/problems/video-stitching/
public class VideoStitching {
    public int videoStitching(int[][] clips, int T) {
        if(clips == null || clips.length <=0) {
            return -1;
        }
        Arrays.sort(clips, (a, b) -> Integer.compare(a[0], b[0]));
        if(clips[clips.length - 1][1] < T) {
            return  -1;
        }
        List<int[]> list = new ArrayList<>();
        list.add(clips[0]);
        for(int i=1;i<clips.length;i++) {
            int start = clips[i][0];
            int end = clips[i][1];

            int prevStart = list.get(list.size() - 1)[0];
            int prevEnd = list.get(list.size() - 1)[1];

            if(prevStart == start) {
                if(end > prevEnd) {
                    list.remove(list.size() - 1);
                    list.add(clips[i]);
                }
                continue;
            }
            if (start <= prevEnd && end <= prevEnd) {
                continue;
            }
            list.add(clips[i]);
        }
        int[] dp = new int[T + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        int[] jump = new int[T + 1];
        Arrays.fill(jump, 0);
        for(int[] current: list) {
            if(current[0] >= T) {
                continue;
            }
            jump[current[0]] = current[1];
        }

        for(int i=0;i<T;i++) {
            if(jump[i] == 0) {
                continue;
            }
            int j = i + 1;
            for(;j <= jump[i] && j <= T;j++) {
                dp[j] = Math.min(dp[j], 1 + dp[i]);
            }

        }
        for(int i=0;i<=T;i++) {
            if(dp[i] == Integer.MAX_VALUE) {
                return -1;
            }
        }
        return dp[T] == Integer.MAX_VALUE ? -1 : dp[T];
    }

    public static void main(String args[]) {
        VideoStitching videoStitching =  new VideoStitching();
        int[][] arr = {{0,1},{6,8},{0,2},{5,6},{0,4},{0,3},{6,7},{1,3},{4,7},{1,4},{2,5},{2,6},{3,4},{4,5},{5,7},{6,9}};

        videoStitching.videoStitching(arr, 9);
    }
}
