package com.practice.arrays;

import java.util.*;

class SnapshotArray {
        TreeMap<Integer, Map<Integer, Integer>> snapMap;
        int snap;
        public SnapshotArray(int length) {
                snap = 0;
                snapMap = new TreeMap();
        }

        public void set(int index, int val) {
                Map<Integer,Integer> tempMap = snapMap.getOrDefault(snap, new HashMap());
                tempMap.put(index, val);
                snapMap.put(snap, tempMap);
        }

        public int snap() {
                snap++;
                return snap - 1;
        }

        public int get(int index, int snap_id) {
                System.out.println("Snap ID:" + snap_id);
                if(snapMap.get(snap_id) != null) {

                        return snapMap.get(snap_id).getOrDefault(index, 0);
                }
                Integer floor = snapMap.floorKey(snap_id);
                if(floor != null) {
                        return snapMap.get(floor).getOrDefault(index, 0) ;
                }
                return 0;
        }

        public static void main(String args[]) {
                SnapshotArray snapshotArray = new SnapshotArray(3);
                snapshotArray.set(1,18);
                snapshotArray.set(1,4);
                snapshotArray.snap();
                System.out.println(snapshotArray.get(0,0));
                snapshotArray.set(0,20);
                snapshotArray.snap();
                snapshotArray.set(0,2);
                snapshotArray.set(1,1);
                System.out.println(snapshotArray.get(1,1));
                System.out.println(snapshotArray.get(1,0));

        }
}