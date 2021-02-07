package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/random-pick-with-weight/discuss/154044/Java-accumulated-freq-sum-and-binary-search
public class RandomNum {
    Random random;
    int[] wSums;

    public RandomNum(int[] w) {
        this.random = new Random();
        for(int i=1; i<w.length; ++i)
            w[i] += w[i-1];
        this.wSums = w;
    }

    public int pickIndex() {
        int len = wSums.length;
        int idx = random.nextInt(wSums[len-1]) + 1;
        int left = 0, right = len - 1;
        // search position
        while(left < right){
            int mid = left + (right-left)/2;
            if(wSums[mid] == idx)
                return mid;
            else if(wSums[mid] < idx)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    public static void main(String args[]) {
        int w[] = {2,5,3,4};
        RandomNum randomNum = new RandomNum(w);
        randomNum.pickIndex();
        randomNum.pickIndex();
        randomNum.pickIndex();
        randomNum.pickIndex();
    }
}
