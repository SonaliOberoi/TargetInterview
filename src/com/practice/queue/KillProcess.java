package com.practice.queue;

import java.util.HashMap;
import java.util.List;
import java.util.*;

class KillProcess {

    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> ansList = new ArrayList<>();
        //special case where there is only one element
        if(ppid.size() <= 1) {
             ansList.addAll(pid);
             return ansList;
        }

        for(int i = 0; i< pid.size();i++) {
            if(map.containsKey(ppid.get(i))) {
                List<Integer> temp = map.get(ppid.get(i));
                temp.add(pid.get(i));
                map.put(ppid.get(i), temp);
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(pid.get(i));
                map.put(ppid.get(i), temp);
            }
        }
        if(map.containsKey(kill)) {
            ansList.add(kill);
            Queue<Integer> queue = new LinkedList();
            queue.addAll(map.get(kill));
            while(!queue.isEmpty()) {
                int poll = queue.poll();
                ansList.add(poll);
                if(map.containsKey(poll)) {
                    queue.addAll(map.get(poll));
                }
            }
        }

        //handle leaf node case
        List<Integer> leafNodes = new ArrayList<Integer>(pid);
        leafNodes.removeAll(ppid);
        for(int i=0; i<leafNodes.size(); i++) {
            if(kill == leafNodes.get(i)) {
                ansList.add(kill);
            }
        }
        return ansList;

    }
    public static void main(String args[]) {
        KillProcess killProcess = new KillProcess();
        Integer[] pid = {1,2,3};
        Integer[] ppid = {0,1,1};
        System.out.print(killProcess.killProcess(Arrays.asList(pid), Arrays.asList(ppid), 2));
    }
}