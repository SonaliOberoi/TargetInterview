package com.practice.arrays;

//https://leetcode.com/problems/rle-iterator/
class RLEIterator {

    int current;
    int[] copy;
    int arrLength;
    public RLEIterator(int[] A) {
        this.current = 0;
        this.copy = A;
        this.arrLength = A.length;
    }

    public int next(int n) {
        int temp = copy[current] - n;
        if(temp > 0) {
            copy[current] = temp;
            return copy[current + 1];
        }
        if(temp == 0) {
            int returnVal = copy[current + 1];
            //copy[current] = temp;
            current = current + 2;
            return returnVal;
        }
        while(current + 2 < arrLength - 1 && temp < 0) {
            current = current + 2;
            int overFlow = Math.abs(temp);
            temp = copy[current] - overFlow;
        }
        if(current >= arrLength || temp < 0) {
            return -1;
        } else {
            copy[current] = temp;
            return copy[current + 1];
        }

    }

    public static void main(String args[]) {
        int[] arr = {3,8,0,9,2,5};
        RLEIterator rleIterator = new RLEIterator(arr);
        rleIterator.next(2);
        rleIterator.next(1);
        rleIterator.next(1);
        rleIterator.next(2);
    }
}
