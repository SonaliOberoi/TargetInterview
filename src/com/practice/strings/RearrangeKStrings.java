package com.practice.strings;

import java.util.*;

//https://leetcode.com/problems/rearrange-string-k-distance-apart/
public class RearrangeKStrings {
    class Helper {
        Character character;
        int count;
        Helper(Character character, int count) {
            this.character =character;
            this.count = count;
        }
    }
    public String rearrangeString2(String s, int k) {
        PriorityQueue<Helper> priorityQueue = new PriorityQueue<>(new Comparator<Helper>() {
            @Override
            public int compare(Helper o1, Helper o2) {
                return o2.count - o1.count;
            }
        });
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0;i<s.length();i++) {
            if(map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            } else {
                map.put(s.charAt(i), 1);
            }
        }
        map.forEach((key,v) -> {
            priorityQueue.offer(new Helper(key, v));
        });
        String output = "";
        Queue<Helper> queue = new LinkedList<>();
        while(!priorityQueue.isEmpty()) {
            Helper current = priorityQueue.poll();
            output = output + current.character;
            current.count = current.count - 1;
            queue.offer(current);
            if(queue.size() < k) {
                continue;
            }
        }
        return "";
    }

    public String rearrangeString(String str, int k) {

        StringBuilder rearranged = new StringBuilder();
        //count frequency of each char
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }

        //construct a max heap using self-defined comparator, which holds all Map entries, Java is quite verbose
        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });

        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());

        while (!maxHeap.isEmpty()) {

            Map.Entry<Character, Integer> current = maxHeap.poll();
            rearranged.append(current.getKey());
            current.setValue(current.getValue() - 1);
            waitQueue.offer(current);

            if (waitQueue.size() < k) { // intial k-1 chars, waitQueue not full yet
                continue;
            }
            // release from waitQueue if char is already k apart
            Map.Entry<Character, Integer> front = waitQueue.poll();
            //note that char with 0 count still needs to be placed in waitQueue as a place holder
            if (front.getValue() > 0) {
                maxHeap.offer(front);
            }
        }

        return rearranged.length() == str.length() ? rearranged.toString() : "";
    }

    public static void main(String args[]) {
        RearrangeKStrings rearrangeKStrings = new RearrangeKStrings();
        rearrangeKStrings.rearrangeString("aaadbbcc", 2);
    }
}
