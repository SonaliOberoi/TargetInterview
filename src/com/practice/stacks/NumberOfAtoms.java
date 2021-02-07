package com.practice.stacks;

import java.util.*;
//https://leetcode.com/problems/number-of-atoms/
public class NumberOfAtoms {
    class Pair{
        char element;
        int count;
        Pair(char element, int count) {
            this.count = count;
            this.element = element;
        }
    }
    public String countOfAtoms(String formula) {
        if(formula.length() <= 1) {
            return formula;
        }
        Map<Character, Integer> map = new HashMap<>();
        PriorityQueue<Pair> queue = new PriorityQueue<>(formula.length(), (a,b) -> a.element - b.element);
        Stack<Pair> stack = new Stack<>();
        Stack<Pair> balancingStack = new Stack<>();
        for(int i = 0;i<formula.length();i++) {
            int chNext = i < formula.length() - 1 ? formula.charAt(i + 1) : '@';
            int multiple = Character.isDigit(chNext) ? Character.getNumericValue(chNext) : 1;
            if(multiple == 1) {

            }
            if(formula.charAt(i) == ')') {

                while (!stack.empty() && stack.peek().element != '(') {
                    Pair current = stack.pop();
                    current.count = current.count * multiple;
                    balancingStack.push(current);
                }
                stack.pop();
                while(!balancingStack.empty()) {
                    stack.push(balancingStack.pop());
                }
            } else if(!Character.isDigit(formula.charAt(i))) {
                stack.push(new Pair(formula.charAt(i), multiple));
            }
        }
        while(!stack.isEmpty()) {
            Pair current = stack.pop();
            if(map.containsKey(current.element)) {
                map.put(current.element, current.count + map.get(current.element));
            } else {
                map.put(current.element, current.count);
            }
        }
        map.forEach((k,v)-> {
            queue.add(new Pair(k, v));
        });
        String output = "";
        while (!queue.isEmpty()) {
            Pair element = queue.poll();
            output = element.count > 1 ? output + element.element + element.count : output + element.element;
        }
        return output;
    }

    public static void main(String args[]) {
        NumberOfAtoms numberOfAtoms = new NumberOfAtoms();
        System.out.println(numberOfAtoms.countOfAtoms("K4(ON(SO3)2)2"));
    }
}
