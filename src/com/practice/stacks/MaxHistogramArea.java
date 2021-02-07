package com.practice.stacks;

import java.util.*;

//
public class MaxHistogramArea {

    public static int maxArea(int[] arr) {
        if(arr == null || arr.length< 1) {
            return 0;
        }
        Stack<Integer> position = new Stack();
        Stack<Integer> height = new Stack();
        int maxArea = 0;
        for(int i=0;i<arr.length;i++) {
            if(position.isEmpty() || height.peek() < arr[i]) {
                position.push(i);
                height.push(arr[i]);
                continue;
            }
            if(height.peek() == arr[i]) {
                continue;
            }
            int lastPos = -1;
            while(!height.isEmpty() && height.peek() > arr[i]) {
                int width = i - position.peek();
                int currentArea = width * height.peek();
                maxArea = Math.max(maxArea, currentArea);
                lastPos = position.pop();
                height.pop();
            }
            if(!height.isEmpty()) {
                if(height.peek() == arr[i]) {
                    continue;
                }
            }
            position.push(lastPos);
            height.push(arr[i]);
        }
        int width = 1;
        while (!position.isEmpty()) {
            maxArea = Math.max(maxArea, width * height.pop());
            width = position.pop() - (!position.isEmpty() ? position.peek() : 0) + 1;
        }

        return maxArea;
    }

    public static void main(String args[]) {
        int[] arr = {2,2,1,3,4,1,2};
        System.out.println(maxArea(arr));
    }

}
