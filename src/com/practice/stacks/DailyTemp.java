package com.practice.stacks;

import java.util.Stack;

//https://leetcode.com/problems/daily-temperatures/
public class DailyTemp {
    public int[] dailyTemperatures(int[] T) {
        if(T == null || T.length < 1) {
            return new int[0];
        }
        Stack<int[]> stack = new Stack<>();
        int[] answer = new int[T.length];
        for(int i = 0;i<T.length;i++) {
            while (!stack.isEmpty() && stack.peek()[1] < T[i]) {
                answer[stack.peek()[0]] = i - stack.peek()[0];
                stack.pop();
            }
            int[] temp = {i, T[i]};
            stack.add(temp);
        }
        return answer;
    }
}
