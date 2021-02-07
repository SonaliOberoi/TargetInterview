package com.practice.tree;

import java.util.*;
import java.util.stream.Collectors;

class NAryNode{
    List<NAryNode> children;
    String value;
    NAryNode(List<NAryNode> children, String value) {
        this.children = children;
        this.value= value;
    }
}

public class SmallestRegion {

    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, NAryNode> map = new HashMap<>();
        NAryNode root = new NAryNode(new ArrayList<NAryNode>(), regions.get(0).get(0));
        map.put(regions.get(0).get(0), root);
        for(List<String> list: regions) {
            NAryNode parent;
            if(map.containsKey(list.get(0))) {
                parent = map.get(list.get(0));
            } else {
               parent = new NAryNode(new ArrayList<NAryNode>(), list.get(0));
               map.put(list.get(0), parent);
            }
            for(int i = 1;i< list.size();i++)  {
                NAryNode child;
                if(map.containsKey(list.get(i))) {
                    child = map.get(list.get(i));
                } else {
                    child = new NAryNode(new ArrayList<NAryNode>(), list.get(i));
                    map.put(list.get(i), child);
                }
                List<NAryNode> childList = parent.children;
                childList.add(child);
                parent.children = childList;
            }
        }
        //LCA code

        return lca(root, region1, region2).value;
    }
    private NAryNode lca(NAryNode root, String str1, String str2){
        if(root == null) {
            return null;
        }
        if(root.value == str1 || root.value == str2) {
            return root;
        }
        int count = 0;
        NAryNode temp = null;
        for(NAryNode currentChildren: root.children) {
             NAryNode result = lca(currentChildren, str1, str2);
            if(result != null)
            {
                count++;
                temp = result;
            }
        }
        if(count == 2)
            return root;

        return temp;

    }
    public static void main(String args[]) {
        String[][] regions = {{"Earth","North America","South America"},
                {"North America","United States","Canada"},
                {"United States","New York","Boston"},
                {"Canada","Ontario","Quebec"},
                {"South America","Brazil"}};
        List<List<String>> list = Arrays.stream(regions)
                .map(Arrays::asList)
                .collect(Collectors.toList());
        SmallestRegion smallestRegion = new SmallestRegion();
        System.out.print(smallestRegion.findSmallestRegion(list, "Quebec", "New York"));
    }

}

