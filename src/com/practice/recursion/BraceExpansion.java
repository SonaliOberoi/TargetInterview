package com.practice.recursion;


import java.util.*;
import java.util.Stack;

//https://leetcode.com/problems/brace-expansion/
public class BraceExpansion {

    public void expand(String S) {
        Stack<Character> stack = new Stack<>();
        List<String> list = new ArrayList<>();
        String current = "";
        for (int i = 0;i<S.length();i++) {
            if (S.charAt(i) == '{') {
                while (!stack.isEmpty()) {
                    current = current + String.valueOf(stack.pop());
                }
                if (current != "") {
                    list.add(new StringBuilder(current).reverse().toString());
                }
                current = "";
            }
            if(S.charAt(i) == ',') {
                continue;
            }
            if(S.charAt(i) == '}') {
                while (stack.peek() != '{') {
                    current = current + String.valueOf(stack.pop());
                }
                stack.pop();
                list.add(new StringBuilder(current).reverse().toString());
                current = "";
                continue;
            }
            stack.push(S.charAt(i));
            continue;
        }
        list.add("f");
        queueBasedApproach(list, list.size());
    }

    private List<String> queueBasedApproach(final List<String> list, final int length) {
        List<String> answer = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        String firstString = list.get(0);
        for(int i=0;i<firstString.length();i++) {
            ((LinkedList<String>) queue).add(String.valueOf(firstString.charAt(i)));
        }
        for(int i = 1;i<length;i++) {
            String currentString = list.get(i);
            int currentStringLength = currentString.length();
            int currentQueueLength = queue.size();
            while (currentQueueLength > 0) {
                String currentQueueElement = queue.poll();
                for (int j = 0;j<currentStringLength;j++) {
                    String stringToAdd = currentQueueElement + String.valueOf(currentString.charAt(j));
                    if (stringToAdd.length() == length) {
                        System.out.println(stringToAdd);
                        answer.add(stringToAdd);
                    } else {
                        ((LinkedList<String>) queue).add(stringToAdd);
                    }
                }
                currentQueueLength--;
            }
        }
        return answer;
    }

    public static void main(String args[]) {
        BraceExpansion braceExpansion = new BraceExpansion();
        braceExpansion.expand("{a,b}c{d,e}f");
    }
}
