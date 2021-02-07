package com.practice.stacks;

import java.util.Stack;

//https://leetcode.com/problems/score-of-parentheses/
public class ParenthesisScore {
    public static  int scoreOfParentheses(String S) {
        if (S == null || S.length() < 2) {
            return 0;
        }
        String temp = "";
        for(int i=0;i<S.length();i++) {
            if (i+ 1< S.length() && S.charAt(i) == '(' && S.charAt(i + 1) == ')') {
                temp = temp + '1';
                i++;
            } else {
                temp = temp + S.charAt(i);
            }
        }
        if(temp.length() == 1) {
            return 1;
        }
        System.out.println(temp);
        Stack<String> stack = new Stack<>();

        int pointer = 0;
        while (temp.length() > pointer) {
            Character currentParsedCharacter = temp.charAt(pointer);
            if(currentParsedCharacter == ')') {
                int multiplier = 2;
                while (!"(".equals(stack.peek())) {
                    multiplier = multiplier * Integer.valueOf(stack.pop());
                }
                stack.pop();
                if(!stack.isEmpty() && !"(".equals(stack.peek()) && !"(".equals(stack.peek())) {
                    int top = Integer.valueOf(stack.pop());
                    multiplier = multiplier + top;
                }
                stack.push(String.valueOf(multiplier));
                pointer++;
                continue;
            }

            if(currentParsedCharacter != '(' && currentParsedCharacter != ')' && !stack.isEmpty() && !"(".equals(stack.peek())) {
                int current = Integer.valueOf(String.valueOf(currentParsedCharacter));
                int top = Integer.valueOf(stack.pop());
                stack.push(String.valueOf(current + top));
                pointer++;
                continue;
            }
            stack.push(String.valueOf(currentParsedCharacter));
            pointer++;
        }
        return Integer.valueOf(stack.pop());
    }
    public static void main(String args[]) {
        scoreOfParentheses("()()");
    }
}
