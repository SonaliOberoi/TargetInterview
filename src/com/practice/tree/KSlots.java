package com.practice.tree;

import java.util.*;

//https://leetcode.com/problems/k-empty-slots/
public class KSlots {

    public int kEmptySlots(int[] bulbs, int K) {
        if(bulbs == null || bulbs.length < 1) {
         return -1;
        }
        TreeSet<Integer> set=new TreeSet<>();

        int answer = 0;
        for(int current: bulbs) {
            answer++;

            Integer floorKey = set.floor(current) != null ? set.floor(current) : current ;
            Integer ceilingKey = set.ceiling(current) != null ? set.ceiling(current) : current;

            if (current - floorKey - 1 == K || ceilingKey - current - 1 == K) {
                return answer;
            }
            set.add(current);
        }

        return -1;

    }

    public static void main(String args[]) {
        KSlots kSlots = new KSlots();
        int[] arr ={1,3,2};
        System.out.println(kSlots.kEmptySlots(arr, 1));
    }
}
