package com.practice.tree;

public class Node {
    Node left;
    Node right;
    int element;
    Node(Node left, Node right, int element) {
        this.left = left;
        this.right = right;
        this.element = element;
    }
}
