package com.practice.general;

import java.util.*;

//https://leetcode.com/problems/accounts-merge/
public class Accounts {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> map = new HashMap<>();
        Map<String, List<String>> accountMerge = new HashMap<>();
        int count = 0;
        for(List<String> list: accounts) {
            String name = list.get(0);
            for(int i=1;i<list.size();i++) {
                if (!map.containsKey(list.get(i))) {
                    map.put(list.get(i), name + ":" + String.valueOf(count));
                }
            }
        }

        map.forEach((k,v) -> {
            List<String> accountList = accountMerge.getOrDefault(v, new ArrayList<String>());
            accountList.add(k);
            accountMerge.put(v, accountList);
        });

        List<List<String>> answer = new ArrayList<>();

        accountMerge.forEach((k,v) -> {
            Collections.sort(v);
            v.add(0, k);
            answer.add(v);
        });
        return answer;
    }

    public static void main(String args[]) {
        Accounts accounts = new Accounts();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        list1.add("John"); list1.add("johnsmith@mail.com"); list1.add("john00@mail.com");
        list2.add("John"); list2.add("johnnybravo@mail.com");
        list3.add("John"); list3.add("johnsmith@mail.com"); list3.add("john_newyork@mail.com");

    }
}
