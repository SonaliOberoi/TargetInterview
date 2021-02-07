package com.practice.arrays;

//https://leetcode.com/problems/range-sum-query-mutable/
public class RangeSumQuery {

    int N;
    int[] nums;
    int[] prefixArr;
    public RangeSumQuery(int[] nums) {
        this.nums = nums;
        this.prefixArr = new int[nums.length];
        for(int i=0;i<nums.length;i++){

        }
    }

    public void update(int i, int val) {

    }

    public int sumRange(int i, int j) {
        return -1;
    }

    public static void main(String args[]) {
        int[] arr = {
                1 ,    3    , 5    , 7    ,  8   ,   6    ,  3  ,     5   ,   6};
        RangeSumQuery rangeSumQuery = new RangeSumQuery(arr);
    }

}
