package com.practice.disjointsets;

import com.practice.general.Accounts;

import java.util.ArrayList;
import java.util.*;

//https://leetcode.com/problems/accounts-merge/
public class AccountsMerge {
    Map<String, Node> map = new HashMap<>();

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        if(accounts == null || accounts.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        List<List<String>> answer = new ArrayList<>();

        for (List<String> list: accounts) {
            String name = list.get(0);
            if (!map.containsKey(list.get(1))) {
                Node node = new Node(null, list.get(1), name);
                node.parent = node;
                map.put(list.get(1), node);
            }
            for(int i=2;i<list.size();i++) {
                union(list.get(i-1), list.get(i), name);
            }
        }

        Map<Node, List<String>> answerMap = new HashMap<>();
        map.forEach((k,v) -> {
            Node parent = v.parent;
            while (parent.parent != parent) {
                parent = parent.parent;
            }
            List<String> list = answerMap.getOrDefault(parent, new ArrayList<>());
            list.add(k);
            answerMap.put(parent, list);
        });

        answerMap.forEach((k,v) -> {
            Collections.sort(v);
            v.add(0, k.name);
            answer.add(v);
        });

        return answer;
    }
    private boolean union(String mail1, String mail2, String name) {
        Node parent1 = findUtil(mail1, name);
        Node parent2 = findUtil(mail2, name);

        if (parent1 == parent2) {
            return false;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    private Node findUtil(String mail, String name) {
        if (!map.containsKey(mail)) {
            Node node = new Node(null, mail, name);
            node.parent = node;
            map.put(mail, node);
            return node;
        }
        Node node = map.get(mail);
        if(node.parent == node) {
            return node;
        }
        node.parent = findUtil(node.parent.email, name);
        return node.parent;
    }
    class Node {
        Node parent;
        String email;
        String name;
        Node(Node parent, String email, String name) {
            this.parent = parent;
            this.email = email;
            this.name = name;
        }
    }

    public static void main(String args[]) {
        AccountsMerge accounts = new AccountsMerge();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list1.add("John"); list1.add("johnsmith@mail.com"); list1.add("john00@mail.com");
        list2.add("John"); list2.add("johnnybravo@mail.com");
        list3.add("John"); list3.add("johnsmith@mail.com"); list3.add("john_newyork@mail.com");
        list4.add("Mary"); list4.add("mary@mail.com");

        List<List<String>> lists = new ArrayList<>();
        lists.add(list1);
        lists.add(list2);
        lists.add(list3);
        lists.add(list4);
        System.out.println(accounts.accountsMerge(lists));

    }
}
