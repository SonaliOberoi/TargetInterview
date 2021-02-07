package com.practice.greedy;

//https://leetcode.com/problems/two-city-scheduling/
public class MinCostCity {
    public static int twoCitySchedCost(int[][] costs) {
        if(costs == null || costs.length < 1) {
            return 0;
        }
        int totalA=0, totalB = 0;
        for(int i=0;i<costs.length;i++) {
            totalA = totalA + costs[i][0];
            totalB = totalB + costs[i][1];
        }

        int answer = Integer.MAX_VALUE;
        int aCitySize = costs.length/2;
        for(int i=0;i<aCitySize;i++) {
            int currentSumA = costs[i][0];
            int currentNotB = costs[i][1];
            int temSize = 1;
            for(int j=i+1;j<costs.length;j++) {
                if(temSize == aCitySize) {
                    answer = Math.min(answer, currentSumA + totalB - currentNotB);
                    currentSumA = costs[i][0];
                    currentNotB = costs[i][1];
                }
                currentSumA = currentSumA + costs[j][0];
                currentNotB = currentNotB + costs[j][1];
                temSize++;
            }
        }
        return answer;
    }

    public static void main(String args[]) {
        int[][] arr= {{10,20},{30,200},{400,50},{30,20}};
        System.out.println(twoCitySchedCost(arr));
    }
}
