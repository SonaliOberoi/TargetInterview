package com.practice.dp;

import java.util.*;

//https://leetcode.com/problems/shortest-way-to-form-string/
public class ShortestString {

    public int shortestWay(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }
        int answer = 0;
        int tIndex = 0;

        while(tIndex < target.length()) {
            int startIndex = tIndex;
            for (int sIndex = 0;sIndex<source.length();sIndex++) {
                if (tIndex < target.length() && source.charAt(sIndex) == target.charAt(tIndex)) {
                    tIndex ++;
                }
            }

            if (tIndex == startIndex) {
                return -1;
            }
            answer++;
        }


        return answer;

    }


    public int shortestWay2(String source, String target) {
        if (source == null || target == null) {
            return -1;
        }


        Map<Character, TreeSet<Integer>> sourceMap = new HashMap<>();
        Map<Character, TreeSet<Integer>> targetMap = new HashMap<>();

        fillMap(source, sourceMap);
        fillMap(target, targetMap);

        int ans = 0;
        int previousInt = -1;
        for(int i=0;i<target.length();i++) {
            char current = target.charAt(i);
            if (!sourceMap.containsKey(current)) {
                return -1;
            }
            Set<Integer> currentSet = sourceMap.get(current);
            if (previousInt == -1) {
                previousInt = ((TreeSet<Integer>) currentSet).first();
                ans++;
            } else {
                for(Integer index: currentSet) {
                    if (index > previousInt) {
                        previousInt = index;
                    }
                }
            }

        }

        return ans;
    }

    private void fillMap(String str, Map<Character, TreeSet<Integer>> map) {

        for(int i=0;i<str.length();i++) {
            char ch = str.charAt(i);
            TreeSet<Integer> set = map.getOrDefault(ch, new TreeSet<>());
            set.add(i);
            map.put(ch, set);
        }
    }

}
