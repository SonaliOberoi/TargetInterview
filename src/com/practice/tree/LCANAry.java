package com.practice.tree;

import java.util.ArrayList;
import java.util.List;

public class LCANAry {
    public Node lca(Node root, Node n1, Node n2) {
        if(root == null) {
            return null;
        }

        if(root == n1 || root == n2) {
            return root;
        }

        List<Node> childrenFound = new ArrayList<>();
        for(Node currentChild: root.children) {
            Node child = lca(currentChild, n1, n2);
            if(child != null) {
                childrenFound.add(child);
                return child;
            }
        }
        if(childrenFound.size() > 2) {
            return root;
        } else {
            return childrenFound.size() > 0 ? childrenFound.get(0) : null;
        }
    }
    class Node {
        List<Node> children;
        int val;
        Node(int val) {
            children = new ArrayList<>();
            this.val = val;
        }
    }
}
