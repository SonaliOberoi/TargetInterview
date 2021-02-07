package com.practice.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/path-sum-ii/
public class PathSum2 {
    List<List<Integer>> outputList = new ArrayList<>();
    private boolean isLeaf(Node node) {
        if (node != null && node.left == null && node.right == null) {
            return true;
        }
        return false;
    }
    private List<List<Integer>> pathSumUtil(Node root, int sum,
                                            List<Integer> list){
        if (root == null ) {
            return outputList;
        }

        if (root.element == sum && isLeaf(root)) {
            list.add(root.element);
            outputList.add(list);
            System.out.println(Arrays.toString(list.toArray()));
            list.remove(list.get(list.size() - 1));
            return outputList;
        }

        list.add(root.element);
        pathSumUtil(root.left, sum - root.element, list);
        pathSumUtil(root.right, sum - root.element, list);

        list.remove(list.get(list.size() - 1));

        return outputList;

    }
    public List<List<Integer>> pathSum(Node root, int sum) {
        return pathSumUtil(root, sum, new ArrayList<>());
    }

    public static void main(String args[]) {
        /**
         *       5
         *      / \
         *     4   8
         *    /   / \
         *   11  13  4
         *  /  \    / \
         * 7    2  5   1
         */
        Node root = new Node(null, null, 5);
        root.left = new Node(null, null, 4);
        root.right = new Node(null, null, 8);
        root.left.left = new Node(null, null, 11);
        root.left.left.left = new Node(null, null, 7);
        root.left.left.right = new Node(null, null, 2);
        root.right.left = new Node(null, null, 13);
        root.right.right = new Node(null, null, 4);
        root.right.right.left = new Node(null, null, 5);
        root.right.right.right = new Node(null, null, 1);

        PathSum2 pathSum2 = new PathSum2();

        List<List<Integer>> list =pathSum2.pathSum(root, 22);
        for (int i = 0; i< list.size(); i++) {
            System.out.println(Arrays.toString(list.get(0).toArray()));
        }
    }
}
