package com.practice.backtracking;

import java.util.*;

//https://leetcode.com/problems/expression-add-operators/solution/
public class Operators {
    public List<String> addOperators(String num, int target) {
        if(num == null || num.length() < 1) {
            return new ArrayList();
        }
        int[] numArr = new int[num.length()];
        int j = num.length() - 1;
        int intNum = Integer.parseInt(num);
        while(intNum > 0) {
            numArr[j] = intNum % 10;
            intNum = intNum/10;
            j--;
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(String.valueOf(numArr[0]));
        int i = 1;
        while(i < numArr.length) {
            if(!queue.isEmpty()) {
                int size = queue.size();
                while(size > 0) {
                    String current = queue.poll();
                    String next = String.valueOf(numArr[i]);
                    queue.add(current + next);
                    queue.add(current + "+" + next);
                    queue.add(current + "*" + next);
                    queue.add(current + "-" + next);
                    size --;
                }
            }
            i++;
        }
        List<String> list = new ArrayList();
        while(!queue.isEmpty()) {
            System.out.println(queue.peek());
            list.add(queue.poll());
        }
        return list;
    }

    private int evaluate(String word) {
        StringBuilder sb = new StringBuilder();
        int index = word.indexOf("*");
        while (index >= 0) {
            int i = 1;
            while(Character.isDigit(word.charAt(index - i))) {

                i++;
            }
            index = word.indexOf("*", index + 1);
        }
        return -1;
    }

    public static void main(String args[]) {
        Operators operators = new Operators();
        operators.addOperators("105", 5);
    }
}
