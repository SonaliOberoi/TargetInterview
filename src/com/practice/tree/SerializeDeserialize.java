package com.practice.tree;


import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerializeDeserialize {

    String delimeter = ",";
    public String serailize(TreeNode root) {
        if(root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        utilSerialize(root, sb);
        return sb.toString();
    }

    public TreeNode deserialise(String string) {
        if(string == null || string.isEmpty()) {
            return null;
        }
        ArrayList aList= new ArrayList(Arrays.asList(string.split(delimeter)));
        return utilDeserialise(aList);
    }

    private TreeNode utilDeserialise(List<String> list) {
        if(list == null || list.isEmpty()) {
            return null;
        }
        int current = Integer.valueOf(list.remove(0));
        int childCount = Integer.valueOf(list.remove(0));
        TreeNode node = new TreeNode(current);
        for(int i=0;i<childCount;i++) {
            node.children.add(utilDeserialise(list));
        }
        return node;
    }

    private void utilSerialize(TreeNode root, StringBuilder sb) {
        if(root ==  null) {
            return;
        }
        sb.append(root.val);
        sb.append(delimeter);
        sb.append(root.children.size());
        sb.append(delimeter);
        for(TreeNode node: root.children) {
            utilSerialize(node, sb);
        }
    }

    public static void main(String args[]) {
        SerializeDeserialize serializeDeserialize = new SerializeDeserialize();
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(6);

        root.children.add(n1);
        root.children.add(n2);
        root.children.add(n3);

        root.children.get(0).children.add(n4);
        root.children.get(0).children.add(n5);

        String data = serializeDeserialize.serailize(root);
        System.out.println(data);
        serializeDeserialize.deserialise(data);
    }


}
class TreeNode {
    int val;
    List<TreeNode> children;
    TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}