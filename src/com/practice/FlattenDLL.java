package com.practice;

import java.util.Stack;

//https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
public class FlattenDLL {
    public Node flatten(Node head) {
        if(head == null) {
            return null;
        }
        Node current = head;
        Stack<Node> stack = new Stack();
        while (current.next != null || !stack.isEmpty()) {
            if(current.child == null) {
                current = current.next;
            } else {
                stack.push(current.next);
                current.next = current.child;
                current.child = null;
            }
            if(current.next == null && !stack.isEmpty()) {
                current.next = stack.pop();
            }
        }
        return head;
    }
}
