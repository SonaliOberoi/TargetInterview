package com.practice.arrays;

//https://leetcode.com/problems/max-chunks-to-make-sorted/solution/
public class MaxChunks {
    public static int maxChunksToSorted(int[] arr) {
        int chunks = 0;
        for (int i = 0 ; i< arr.length;i++) {
            if(i != arr[i]) {
                int j = i + 1;
                int newMax = arr[i];
                while(j <= newMax) {
                    if (arr[j] > newMax) {
                        newMax = arr[j];
                    }
                    j = j + 1;
                }
                i = newMax;
                chunks = chunks + 1;
                continue;
            }
            chunks = chunks + 1;
        }
        return chunks;
    }

    public static void main(String args[]) {
        int[] arr = {1,4,0,2,3,5};
        System.out.print(maxChunksToSorted(arr));
    }
}
