package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/triangle/
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(triangle.get(triangle.size() - 1));
        int j = 0;
        for(int i = triangle.size() - 2;i >=0 ;i--) {
            List<Integer> current = triangle.get(i);
            List<Integer> newList = new ArrayList<>();
            for(int k=0;k<current.size();k++) {
                newList.add(current.get(k) + Math.min(dp.get(j).get(k), dp.get(j).get(k+1)));
            }
            dp.add(newList);
            j++;
        }
        return dp.get(dp.size() - 1).get(0);
    }
}
