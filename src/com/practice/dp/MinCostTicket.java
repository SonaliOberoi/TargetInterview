package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/minimum-cost-for-tickets/
public class MinCostTicket {
    int[] costs;
    Integer[] memo;
    Set<Integer> dayset;

    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        memo = new Integer[366];
        dayset = new HashSet();
        for (int d: days) dayset.add(d);

        return dp(1);
    }

    public int dp(int i) {
        if (i > 365)
            return 0;
        if (memo[i] != null)
            return memo[i];

        int ans;
        if (dayset.contains(i)) {
            ans = Math.min(dp(i+1) + costs[0],
                    dp(i+7) + costs[1]);
            ans = Math.min(ans, dp(i+30) + costs[2]);
        } else {
            ans = dp(i+1);
        }

        memo[i] = ans;
        return ans;
    }

    public int mincostTicketsSonali(int[] days, int[] costs) {
        if(days == null || days.length < 1) {
            return 0;
        }
        if(days.length == 1) {
            return 2;
        }
        TreeMap<Integer, Integer> map = new TreeMap();
        for(int i=0;i<days.length;i++) {
            map.put(days[i], i);
        }
        int[][] dp = new int[3][days.length];
        dp[0][0] = costs[0];
        for(int i=1;i<days.length;i++) {
            dp[0][i] = dp[0][i-1] + costs[0];
        }

        for(int i=1;i<3;i++) {
            for(int j=0;j<days.length;j++) {
                if(dp[i-1][j] < costs[i]) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }
                Map.Entry<Integer,Integer> floor = null;
                if(i == 1) {
                    floor = map.floorEntry(days[j] - 7);
                } else {
                    floor = map.floorEntry(days[j] - 30);
                }
                if(floor == null) {
                    dp[i][j] = costs[i];
                } else {
                    if(i == 2) {
                        Map.Entry<Integer,Integer> floor7 = map.floorEntry(days[j] - 7);;
                        Integer valueGoingBack7Days = dp[i][floor7.getValue()] + costs[1];
                        Integer valueGoingBackOneDay = dp[i][j-1] + costs[0];
                        dp[i][j] = Math.min(dp[i][floor.getValue()] + costs[i], Math.min(valueGoingBack7Days, valueGoingBackOneDay));
                    } else {
                        dp[i][j] = Math.min(dp[i][floor.getValue()] + costs[i], dp[i][j - 1] + costs[0]);
                    }
                }
            }
        }

        return dp[2][days.length - 1];

    }

    public static void main(String args[]) {
        int[] days = {1,2,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20,21,24,25,27,28,29,30,31,34,37,38,39,41,43,44,45,47,48,49,54,57,60,62,63,66,69,70,72,74,76,78,80,81,82,83,84,85,88,89,91,93,94,97,99};
        int[] costs = {9,38,134};

        MinCostTicket minCostTicket = new MinCostTicket();
        System.out.println(minCostTicket.mincostTicketsSonali(days, costs));
    }
}
