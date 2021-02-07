package com.practice.stacks;

import java.util.HashMap;
import java.util.Stack;

//https://leetcode.com/problems/basic-calculator-iii/
public class BasicCalculator {
    HashMap<Character, Integer> map = new HashMap<>();
    BasicCalculator() {
        map.put('(', 0);
        map.put('/', 0);
        map.put('*', 0);
    }
    public int calculate(String s) {
        s.replaceAll("\\s+","");
        String copy;
        for(int i = 0;i<s.length();i++) {

        }
        return -1;
    }
}
