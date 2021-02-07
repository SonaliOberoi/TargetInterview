package com.practice.strings;

import java.util.Map;
import java.util.TreeMap;

//https://leetcode.com/problems/bold-words-in-string/
public class BoldWords {
    public String boldWords(String[] words, String S) {
        if(S == null || S.length() < 1) {
            return null;
        }

        Map<Integer, Integer> startEnd = new TreeMap<>();

        for(String currentWord: words) {
            int indexOf = S.indexOf(currentWord);
            while(indexOf >= 0) {
                Integer floorKey = ((TreeMap<Integer, Integer>) startEnd).floorKey(indexOf);
                if(floorKey != null && startEnd.get(floorKey) >= indexOf ) {
                    startEnd.put(floorKey, indexOf + currentWord.length() -1 > startEnd.get(floorKey) ? indexOf + currentWord.length() -1 : startEnd.get(floorKey));
                } else {
                    startEnd.put(indexOf, indexOf + currentWord.length() -1);
                }
                indexOf = S.indexOf(currentWord, indexOf+1);
            }
        }
        if(startEnd.isEmpty()) {
            return S;
        }
        String solution = "";
        int index = 0;
        for (Integer key : startEnd.keySet()) {
            if(index > key) {
                continue;
            }
            solution = solution+ S.substring(index, key) + "<b>" + S.substring(key, startEnd.get(key) + 1) + "</b>";
            index = startEnd.get(key) + 1;
        }
        if(index <= S.length()) {
            solution = solution + S.substring(index, S.length());
        }
        solution = solution.replaceAll("</b><b>", "");
        return solution;
    }

    public static void main(String args[]) {
        BoldWords boldWords = new BoldWords();
        String[] words = {"e","ad","ce","a","b"};
        System.out.println(boldWords.boldWords(words, "adcaddeced"));
    }
}
