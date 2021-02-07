package com.practice.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/climbing-stairs/solution/
class ClimbStairs {
    public static int climbStairsUtil(int sum, int[] steps, int numberOfWays, List<Integer> pathList, Map<Integer, Integer> memoise) {
        if (sum < 0) {
            return -1;
        }

        if (sum == 0) {
            System.out.println("one possible list is:");
            for (Integer i: pathList) {
                System.out.print(i);
            }
            System.out.println();
            return numberOfWays + 1;
        }


        for (int i=0; i< steps.length && sum - steps[i] >= 0 ; i++) {
            pathList.add(steps[i]);
            numberOfWays = climbStairsUtil(sum - steps[i], steps, numberOfWays, pathList, memoise);
            pathList.remove(pathList.size() - 1);
        }

        memoise.put(sum, numberOfWays);
        return numberOfWays;
    }

    public static int climbStairs(int sum) {
        int[] steps = {1,2};
        return climbStairsUtil(sum, steps, 0, new ArrayList<>(), new HashMap<>());
    }

    public static void main(String args[]) {

        System.out.println(climbStairs(5));
    }
}