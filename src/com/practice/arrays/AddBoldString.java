package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/add-bold-tag-in-string/
public class AddBoldString {

    public String addBoldTag(String s, String[] dict) {
        List<int[]> list = new ArrayList<>();
        for(String current: dict) {
            int indexOf = s.indexOf(current);
            if (indexOf >= 0) {
                int[] arr = {indexOf, indexOf + current.length()};
                list.add(arr);
            }
        }
        if(list.isEmpty()) {
            return s;
        }
        Collections.sort(list, (a, b) -> a[0] - b[0]);
        Stack<int[]> merged = new Stack<>();
        merged.add(list.get(0));

        for(int i=1;i<list.size();i++) {
            int[] prev = merged.peek();
            int[] current = list.get(i);

            if (current[0] > prev[1]) {
                merged.add(current);
            } else {
                merged.pop();
                int[] mergedInterval = {prev[0], current[1]};
                merged.add(mergedInterval);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!merged.isEmpty()) {
            
        }

        return sb.toString();
    }
}
