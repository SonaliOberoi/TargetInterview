package com.practice.graph;

import java.util.Collection;
import java.util.Iterator;

public class DFS {

    void DFS(int v, int numberOfVertices)
    {
        // Mark all the vertices as not visited(set as
        // false by default in java)
        boolean visited[] = new boolean[numberOfVertices];

        // Call the recursive helper function to print DFS traversal
        DFSUtil(v, visited, new Graph(true));
    }

    // A function used by DFS
    void DFSUtil(int v,boolean visited[], Graph graph)
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v+" ");

        // Recur for all the vertices adjacent to this vertex
        Collection<Vertex> i = graph.getAllVertex();
        Iterator iterator = i.iterator();
        while (iterator.hasNext())
        {
            int n = (Integer) iterator.next();
            if (!visited[n])
                DFSUtil(n, visited, graph);
        }
    }

    public static void main(String args[]) {
        Graph graph = new Graph(true);
        graph.addEdge(0, 2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(1,4);
        graph.addEdge(4,6);
        // graph.addEdge(6,5);
        graph.addEdge(5,4);

        DFS dfs = new DFS();
        dfs.DFS(0, 7);


    }
}
