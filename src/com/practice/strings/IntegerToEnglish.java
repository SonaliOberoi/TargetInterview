package com.practice.strings;

import java.util.*;

//https://leetcode.com/problems/integer-to-english-words/
public class IntegerToEnglish {
    public static String numberToWords(int num) {
        if(num == 0){
            return "Zero";
        }
        Map<Integer, String> map = new HashMap();
        map.put(1, " One");
        map.put(2, " Two");
        map.put(3, " Three");
        map.put(4, " Four");
        map.put(5, " Five");
        map.put(6, " Six");
        map.put(7, " Seven");
        map.put(8, " Eight");
        map.put(9, " Nine");
        map.put(10, " Ten");
        map.put(11, " Eleven");
        map.put(12, " Twelve");
        map.put(13, " Thirteen");
        map.put(14, " Fourteen");
        map.put(15, " Fifteen");
        map.put(16, " Sixteen");
        map.put(17, " Seventeen");
        map.put(18, " Eighteen");
        map.put(19, " Nineteen");
        map.put(20, " Twenty");
        map.put(30, " Thirty");
        map.put(40, " Forty");
        map.put(50, " Fifty");
        map.put(60, " Sixty");
        map.put(70, " Seventy");
        map.put(80, " Eighty");
        map.put(90, " Ninety");

        List<Integer> list = new ArrayList<>();
        while (num > 0) {
            list.add(num % 1000);
            num = num/1000;
        }

        Map<Integer, String> map1 = new HashMap<>();
        map1.put(1, " Thousand");
        map1.put(2, " Million");
        map1.put(3, " Billion");
        map1.put(4, " Trillion");

        //System.out.println("Size:" + list.size());

        String string = "";
        for(int i=0;i<list.size();i++) {
            StringBuilder sb = new StringBuilder();
            int current = list.get(i);
            if (current == 0) {
                continue;
            }
            if (current == 100) {
                sb.append(" One Hundred");
            }

            if (current > 100) {
                int hundredDigit = current / 100;
                sb.append(map.getOrDefault(hundredDigit, ""));
                sb.append(" Hundred");
                int twoDigit = current % 100;
                if (map.containsKey(twoDigit)) {
                    sb.append(map.get(twoDigit));
                } else {
                    sb.append(map.getOrDefault((twoDigit/10) * 10 , ""));
                    sb.append(map.getOrDefault(twoDigit%10, ""));
                }
            } else {
                if (map.containsKey(current)) {
                    sb.append(map.get(current));
                } else {
                    sb.append(map.getOrDefault((current/10) * 10 , ""));
                    sb.append(map.getOrDefault(current%10, ""));
                }
            }
            sb.append(map1.getOrDefault(i, ""));

            string = sb.toString() + string;
        }

        return string.trim();

    }

    public static void main(String args[]) {
        System.out.println(numberToWords(1234567891));
    }
}
