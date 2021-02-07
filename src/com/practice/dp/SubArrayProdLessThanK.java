package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/subarray-product-less-than-k/
public class SubArrayProdLessThanK {
    /**
     * Suppose nums[start : end - 1] has n elements in it, and the number of all possible subarrays is: n * (n - 1) / 2
     * So nums[start : end] has (n + 1) elements in it, and the number of all possible subarrays is : (n + 1) * (n ) / 2
     * Thus the number of all newly added subarrays: n (end - start + 1)
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1) return 0;
        int left = 0, right = 0, prod = 1, ans = 0;
        while(right<nums.length) {
            prod = prod * nums[right];
            while(prod >= k) {
                prod = prod/nums[left];
                left++;
            }
            ans = ans + (right - left + 1);
            right++;
        }
        return ans;
    }

    public static void main(String args[]) {
       // int[] arr = {10,9,10,4,3,8,3,3,6,2,10,10,9,3};
        int[] arr = {1,2,3,4};
        SubArrayProdLessThanK subArrayProdLessThanK = new SubArrayProdLessThanK();
        System.out.println(subArrayProdLessThanK.numSubarrayProductLessThanK(arr, 19));
    }
}
