package com.practice.search;

public class ShipCapacity {
    public static int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w: weights) {
            left = Math.max(left, w);
            right = right + w;
        }
        while (left < right) {
            int mid = (left + right) / 2, need = 1, cur = 0;
            for (int w: weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (need > D) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    public static void main(String args[]) {
        int weights[] = {1,2,3,4,5,6,7,8,9,10};
        shipWithinDays(weights, 5);
    }
}
