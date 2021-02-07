package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/course-schedule/
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses < 2) {
            return true;
        }
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Set<Integer>> graph = new HashMap();
        for(int i=0;i<prerequisites.length;i++) {
            int from = prerequisites[i][0];
            int to = prerequisites[i][1];
            Set<Integer> set = graph.getOrDefault(from, new HashSet<>());
            set.add(to);
            graph.put(from, set);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i < numCourses;i++) {
            if(!indegree.containsKey(i)) {
                queue.add(i);
            }
        }
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i= 0;i<size;i++) {
                int current = queue.poll();
                count++;
                if (graph.containsKey(current)) {
                    for(Integer neighbour: graph.get(current)) {
                        indegree.put(neighbour, indegree.get(neighbour) - 1);
                        if(indegree.get(neighbour) == 0) {
                            queue.add(neighbour);
                        }
                    }
                }
            }
        }
        return count == numCourses;
    }

    //https://leetcode.com/problems/course-schedule-ii/
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        if(numCourses < 2) {
            int[] arr = {0,1};
            return arr;

        }
        int[] answer = new int[numCourses];
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Set<Integer>> graph = new HashMap();
        for(int i=0;i<prerequisites.length;i++) {
            int from = prerequisites[i][0];
            int to = prerequisites[i][1];
            Set<Integer> set = graph.getOrDefault(from, new HashSet<>());
            set.add(to);
            graph.put(from, set);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0;i < numCourses;i++) {
            if(!indegree.containsKey(i)) {
                queue.add(i);
            }
        }
        int count = 0;
        int k = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i= 0;i<size;i++) {
                int current = queue.poll();
                answer[k] = current;
                k++;
                count++;
                if (graph.containsKey(current)) {
                    for(Integer neighbour: graph.get(current)) {
                        indegree.put(neighbour, indegree.get(neighbour) - 1);
                        if(indegree.get(neighbour) == 0) {
                            queue.add(neighbour);
                        }
                    }
                }
            }
        }
        return count == numCourses ? answer : null;
    }

    //https://leetcode.com/problems/course-schedule-iii/
    public static int scheduleCourse(int[][] courses) {
        if (courses == null) {
            return -1;
        }

        if(courses.length == 1) {
            return 1;
        }

        Arrays.sort(courses, (a, b) -> {
            return a[1] - b[1];
        });

        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        int total = 0;
        for(int[] current: courses) {
            int time = current[0];
            int daysBeforeFinish = current[1];

            if (total + time <= daysBeforeFinish) {
                total = total + time;
                maxHeap.add(current);
            } else if (!maxHeap.isEmpty() && maxHeap.peek()[0] > time) {
                total = total - maxHeap.peek()[0];
                maxHeap.poll();
                total = total + time;
                maxHeap.add(current);
            }
        }

        System.out.println(maxHeap.size());
        return maxHeap.size();
    }

    public static void main(String args[]) {
        int[][] arr = {{5,5},{4,6},{2,6}};
        scheduleCourse(arr);

    }
}
