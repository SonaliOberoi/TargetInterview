package com.practice.tree;


import java.util.Arrays;

public class SegmentTree {

    int[] arr = {3,4,2,1,5,1};
    int[] segmentTree;

    SegmentTree() {
        int arrLength = arr.length;
        segmentTree = new int[2* nextPowerOf2(arrLength) - 1];
    }

    public void constructSegmentTree(int[] arr, int low, int high, int pos) {
        if(low == high) {
            segmentTree[pos] = arr[low];
            return;
        }
        int mid = (low + high)/2;
        constructSegmentTree(arr, low, mid, 2*pos + 1);
        constructSegmentTree(arr, mid + 1, high, 2*pos + 2);
        segmentTree[pos] = segmentTree[2*pos + 1] + segmentTree[2*pos + 2];
    }
    public int nextPowerOf2(int num){
        if(num ==0){
            return 1;
        }
        if(num > 0 && (num & (num-1)) == 0){
            return num;
        }
        while((num & (num-1)) > 0){
            num = num & (num-1);
        }
        return num<<1;
    }
    public static void main(String args[]) {
        SegmentTree segmentTree = new SegmentTree();
        segmentTree.constructSegmentTree(segmentTree.arr, 0, segmentTree.arr.length - 1, 0);
        System.out.println(Arrays.toString(segmentTree.segmentTree));
    }
}
