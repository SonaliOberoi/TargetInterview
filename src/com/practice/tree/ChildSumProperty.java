package com.practice.tree;

import java.util.LinkedList;
import java.util.Queue;

public class ChildSumProperty {


    public Node convertTreeToChildSum(Node root) {
        if (root == null) {
            return null;
        }

        convertTreeToChildSum(root.left);
        convertTreeToChildSum(root.right);

        int left_data = 0;
        int right_data = 0;
        if (root.left != null) {
            left_data = root.left.element;
        }
        if (root.right != null) {
            right_data = root.right.element;
        }

        if (root.element < left_data + right_data) {
            root.element = left_data + right_data;
        } else {
            int diff = root.element - (left_data + right_data);
            passOnTheDiff(root, diff);
        }
        return root;
    }

    private void passOnTheDiff(Node root, int diff) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            root.left.element += diff;
            passOnTheDiff(root.left, diff);
        } else if (root.right != null) {
            root.right.element += diff;
            passOnTheDiff(root.right, diff);
        }
    }

    public void inOrderTraversal(Node root) {
        if(root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.element);
        inOrderTraversal(root.right);
    }

    public void levelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while(!queue.isEmpty()) {
            System.out.println(queue.peek().element);
            Node current = queue.poll();
            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    public static void main(String args[]) {
        Node root = new Node(null, null, 50);
        root.left = new Node(null, null, 8);
        root.right = new Node(null, null, 31);
        root.left.left = new Node(null, null, 3);
        root.left.right = new Node(null, null, 5);
        root.right.left = new Node(null, null, 1);
        root.right.right = new Node(null, null, 30);
        root.right.right.left = new Node(null, null, 70);

        ChildSumProperty childSumProperty = new ChildSumProperty();
        childSumProperty.levelOrder(childSumProperty.convertTreeToChildSum(root));
    }
}
