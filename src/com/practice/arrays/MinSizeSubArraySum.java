package com.practice.arrays;

public class MinSizeSubArraySum {
    public static int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length < 1) {
            return 0;
        }
        int start = 0;
        int end = 0;
        int answer = Integer.MAX_VALUE;
        int sumSoFar = 0;
        while(start < nums.length && end < nums.length) {
            sumSoFar = sumSoFar + nums[end];
            if(sumSoFar >= s) {
                while(sumSoFar - nums[start] >= s && start <= end) {
                    sumSoFar = sumSoFar - nums[start];
                    start++;
                }
                answer =  Math.min(answer, end - start + 1);
                start++;
            } else {
                end++;
            }
        }
        return answer;
    }

    public static void main(String args[]) {
        int[] arr = {2,3,1,2,4,3};
        minSubArrayLen(7, arr);
    }
}
