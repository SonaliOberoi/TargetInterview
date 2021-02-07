package com.practice.stacks;

import java.util.Stack;

public class SpecialStack {
    Integer min;
    Stack<Integer> stack;

    SpecialStack() {
        stack = new Stack<Integer>();
        min = new Integer(-1);
    }
    public void push(Integer element) {
        if (stack.isEmpty()) {
            this.min = element;
            stack.push(element);
            return;
        }
        if (element >= min) {
            stack.push(element);
        } else {
            stack.push(2*element - min);
            this.min = element;
        }
    }

    public void pop() {
        if (stack.peek() < this.min) {
            System.out.println("pop " + (this.min));
            this.min = 2* this.min - stack.pop();
            return;
        }
        System.out.println("pop " + stack.pop());
    }

    public void getMin() {
        System.out.println("Min element is: " + this.min);
    }
    public static void main(String args[]) {
        SpecialStack specialStack = new SpecialStack();
        specialStack.push(3);
        specialStack.push(5);
        specialStack.getMin();
        specialStack.push(9);
        specialStack.getMin();
        specialStack.push(2);
        specialStack.getMin();
        specialStack.push(7);
        specialStack.push(1);
        specialStack.push(8);
        specialStack.pop();
        specialStack.getMin();
        specialStack.pop();
        specialStack.getMin();
        specialStack.pop();
        specialStack.getMin();
        specialStack.pop();
        specialStack.getMin();
        specialStack.pop();
        specialStack.getMin();
        specialStack.pop();
        specialStack.getMin();
    }
}
