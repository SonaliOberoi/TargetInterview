package com.practice.arrays;

import java.util.*;

public class OptimalAccountBalance {

    public static int minTransfers(int[][] transactions) {
        if(transactions == null || transactions.length < 1) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap();
        for(int[] current: transactions) {
            int from = current[0];
            int to = current[1];
            int money = current[2];
            int existingAmountFrom = map.getOrDefault(from, 0);
            int existingAmountTo = map.getOrDefault(to, 0);
            map.put(from, existingAmountFrom - money);
            map.put(to, existingAmountTo + money);
        }

        map.values().removeIf(o -> (o == 0));
        Integer answer = new Integer(0);
        TreeMap<Integer, Integer> treeMap = new TreeMap();
        for (Integer key : map.keySet()) {
            if(treeMap.containsKey(-map.get(key))) {
                answer++;
                int negativeCount = treeMap.get(-map.get(key));
                if(negativeCount == 1) {
                    treeMap.remove(-map.get(key));
                } else {
                    treeMap.put(-map.get(key), negativeCount - 1);
                }
            } else {
                treeMap.put(map.get(key), treeMap.getOrDefault(map.get(key), 0) + 1);
            }
        }



        while(!treeMap.isEmpty()) {
            Map.Entry<Integer, Integer> first = treeMap.pollFirstEntry();
            Map.Entry<Integer, Integer> last = treeMap.pollLastEntry();
            if(first != null && last != null  ) {
                if(first.getKey() + last.getKey() != 0) {
                    int sum = first.getKey() + last.getKey();
                    treeMap.put(sum, treeMap.getOrDefault(sum, 0) + 1);
                }
                if(first.getValue() > 1) {
                    treeMap.put(first.getKey(), first.getValue() - 1);
                }
                if(last.getValue() > 1) {
                    treeMap.put(last.getKey(), last.getValue() - 1);
                }
            }

            answer++;
        }
        return answer;

    }

    public static void main(String args[]) {
        int[][] arr = {{0,1,5},{0,2,5},{3,4,5},{3,5,5}};
        minTransfers(arr);
    }
}

