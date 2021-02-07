package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/parallel-courses/
public class ParallelCourses {
    public int minimumSemesters(int N, int[][] relations) {
        int minSemesters = 0;
        Map<Integer, Set<Integer>> graph = new HashMap<>(); // key: prerequisite, value: course list.
        for (int[] r : relations) {
            graph.computeIfAbsent(r[0], l -> new HashSet<>()).add(r[1]); // construct graph.
        }

        int[] inDegree = new int[N + 1];
        graph.forEach((node, neighborList) -> {
            for(Integer neighbor: neighborList) {
                inDegree[neighbor] = inDegree[neighbor] + 1;
            }
        });
        // Create a queue and enqueue all vertices with
        // indegree 0
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 1;i <= N; i++)
        {
            if(inDegree[i]==0){
                queue.add(i);
            }
        }
        minSemesters = minSemesters + 1;

        // Initialize count of visited vertices
        int cnt = 0;

        // Create a vector to store result (A topological
        // ordering of the vertices)
        Vector <Integer> topOrder=new Vector<Integer>();
        while(!queue.isEmpty())
        {
            // Extract front of queue (or perform dequeue)
            // and add it to topological order
            int current = queue.poll();
            topOrder.add(current);

            // Iterate through all its neighbouring nodes
            // of dequeued node u and decrease their in-degree
            // by 1
            for(int node : graph.get(current) != null ? graph.get(current) : new ArrayList<Integer>())
            {
                // If in-degree becomes zero, add it to queue
                if(--inDegree[node] == 0){
                    queue.add(node);
                }
            }
            cnt++;
        }

        // Check if there was a cycle
        if(cnt != N)
        {
            System.out.println("There exists a cycle in the graph");
            return -1;
        }

        // Print topological order
        for(int i : topOrder)
        {
            System.out.print(i+" ");
        }

        return minSemesters;
    }

    public int minimumSemestersLeetCode(int N, int[][] relations) {
        Map<Integer, List<Integer>> g = new HashMap<>(); // key: prerequisite, value: course list.
        int[] inDegree = new int[N + 1]; // inDegree[i]: number of prerequisites for i.
        for (int[] r : relations) {
            g.computeIfAbsent(r[0], l -> new ArrayList<>()).add(r[1]); // construct graph.
            ++inDegree[r[1]]; // count prerequisites for r[1].
        }
        Queue<Integer> q = new LinkedList<>(); // save current 0 in-degree vertices.
        for (int i = 1; i <= N; ++i)
            if (inDegree[i] == 0)
                q.offer(i);
        int semester = 0;
        while (!q.isEmpty()) { // BFS traverse all currently 0 in degree vertices.
            for (int sz = q.size(); sz > 0; --sz) { // sz is the search breadth.
                int c = q.poll();
                --N;
                if (!g.containsKey(c)) continue; // c's in-degree is currently 0, but it has no prerequisite.
                for (int course : g.remove(c))
                    if (--inDegree[course] == 0) // decrease the in-degree of course's neighbors.
                        q.offer(course); // add current 0 in-degree vertex into Queue.
            }
            ++semester; // need one more semester.
        }
        return N == 0 ? semester : -1;
    }
    public static void main(String args[]) {
        ParallelCourses parallelCourses = new ParallelCourses();
        int[][] relation = {{1,3},
                            {2,3}};
        System.out.print(parallelCourses.minimumSemestersLeetCode(3, relation));

    }
}
