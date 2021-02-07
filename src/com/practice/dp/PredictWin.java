package com.practice.dp;

import java.util.PriorityQueue;

//https://leetcode.com/problems/predict-the-winner/
public class PredictWin {
    public static boolean predictTheWinner(int[] nums) {
        if(nums == null || nums.length < 1) {
            return false;
        }
        if(nums.length == 1) {
            return true;
        }
        return canP1Win(nums, 0, nums.length - 1, 0, 0, true);
    }

    private static boolean canP1Win(int[] nums, int start, int end, int p1_total,
                             int p2_total, boolean isP1Turn) {
        if(nums == null || nums.length < 1 || start > end) {
            return (p1_total >= p2_total);

        }

        if(isP1Turn) {
            return canP1Win(nums, start + 1, end, p1_total + nums[start], p2_total, false)
                    || canP1Win(nums, start, end - 1, p1_total + nums[end], p2_total, false);
        } else {
            return canP1Win(nums, start + 1, end, p1_total, p2_total + nums[start], true)
                    && canP1Win(nums, start, end - 1, p1_total, p2_total + nums[end], true);
        }
    }

    public static void main(String args[]) {
        int[] arr = {1,5,2};
        System.out.println(predictTheWinner(arr));
    }
}
