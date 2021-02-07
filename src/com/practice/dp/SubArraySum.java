package com.practice.dp;

import java.util.Arrays;

public class SubArraySum {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        for(int i = 1;i<nums.length;i++) {
            nums[i] = nums[i] + nums[i -1];
        }
        System.out.println(Arrays.toString(nums));
        int ans = 0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i] == k) {
                ans = ans + 1;
            }
            for(int j=i + 1;j<nums.length;j++) {
                System.out.println(nums[i]);
                System.out.println(nums[j]);
                if(nums[j] - nums[i] == k) {
                    System.out.println("I am here");
                    ans = ans + 1;
                }

            }
        }
        return ans;
    }

    public static void main(String args[]) {
        SubArraySum subArraySum = new SubArraySum();
        int[] arr = {1,2,3};
        subArraySum.subarraySum(arr, 3);
    }

}
