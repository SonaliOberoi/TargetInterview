package com.practice.dp;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {

    public int minCoins(int arr[], int sum){
        return minCoinsUtil(arr, new HashMap<>(), sum, 0);
    }

    private int minCoinsUtil(int arr[], Map<Integer, Integer> map, int sum, int current) {
        if (sum == 0) {
            return 0;
        }
        if (map.containsKey(sum)) {
            return map.get(sum);
        }

        int min = Integer.MAX_VALUE;
        while(current < arr.length && sum - arr[current] >= 0) {
            int val = minCoinsUtil(arr, map, sum - arr[current], current);
            current ++;

            min = Math.min(min, val);
        }
        min = (min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min + 1);
        System.out.println("Sum: " + sum + " min:" + min);
        map.put(sum, min);
        return min;
    }

    public static void main(String args[]) {
        CoinChange coinChange = new CoinChange();
        int arr[] = {2,3,6,7};
        System.out.println(coinChange.minCoins(arr, 13));
    }
}
