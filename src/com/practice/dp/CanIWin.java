package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/can-i-win/
public class CanIWin {
    public boolean canIWin(int max, int target) {
        if (target < 0) {
            return true;
        }

        if (max * (max + 1) / 2 < target) {
            return false;
        }

        Map<String, Boolean> map = new HashMap<String, Boolean>();
        boolean[] visited = new boolean[max];

        return helper(max, target, map, visited);
    }

    private boolean helper(int max, int target, Map<String, Boolean> map, boolean[] visited) {
        String str = Arrays.toString(visited);
        if(target <= 0) {
            return true;
        }
        /*if (map.containsKey(str)) {
            return map.get(str);
        }*/

        for (int i = 1; i <= max; i++) {
            if (!visited[i - 1]) {
                visited[i - 1] = true;

                if (i >= target || !helper(max, target - i, map, visited)) {
                    map.put(str, true);
                    visited[i - 1] = false;
                    return true;
                }

                visited[i - 1] = false;
            }
        }

        map.put(str, false);
        return false;
    }


    private int getHash(boolean[] used) {
        int hash = 0;
        for (int i = 0; i < used.length; i++) {
            if (used[i]) {
                hash |= (1 << i);
            }
        }
        return hash;
    }
    public static void main(String args[]) {
        CanIWin canIWin = new CanIWin();
        System.out.println(canIWin.canIWin(4, 6));
    }
}
