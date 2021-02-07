package com.practice.arrays;

import java.util.*;

//https://leetcode.com/problems/strobogrammatic-number-ii/discuss/67280/AC-clean-Java-solution
public class Strobogrammatic {
    public static List<String> findStrobogrammatic(int n) {
        List<String> result = new ArrayList<>();

        if (n % 2 == 0) {
            result.add("");
        } else {
            result.add("0");
            result.add("1");
            result.add("8");
            n--;                // We added the middle digit when n is uneven. Note that n is the number of digits we still need to add.
        }

        while (n > 0) {
            List<String> next = new ArrayList<>();
            for (String curr : result) {
                if (n > 2) next.add("0" + curr + "0");
                next.add("1" + curr + "1");
                next.add("8" + curr + "8");
                next.add("6" + curr + "9");
                next.add("9" + curr + "6");
            }
            result = next;
            n -= 2;
        }

        return result;
    }

    public static void main(String args[]) {
        findStrobogrammatic(5);
    }
}
