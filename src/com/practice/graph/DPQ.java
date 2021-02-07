package com.practice.graph;
// Java implementation of Dijkstra's Algorithm
// using Priority Queue
import java.util.*;
public class DPQ {
    private int dist[];
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int V; // Number of vertices
    List<List<Node> > adj;

    public DPQ(int V)
    {
        this.V = V;
        dist = new int[V];
        for(int i = 0;i<V;i++) {
            dist[i] = -1;
        }
        settled = new HashSet<Integer>();
        pq = new PriorityQueue<Node>(V, new Node());
    }

    // Function for Dijkstra's Algorithm
    public void dijkstra(List<List<Node> > adj, int src) {
        this.adj = adj;
        dist[src] = 0;
        pq.add(new Node(src, 0));
        while(settled.size() != V) {
            int current = pq.remove().node;
            exploreNeighbours(current);
        }
    }

    // Function to process all the neighbours
    // of the passed node
    private void exploreNeighbours(int u) {
       settled.add(u);
       for(Node neighbor: adj.get(u)) {
           if(settled.contains(neighbor.node)) {
               continue;
           }
           if(dist[neighbor.node] == -1) {
               dist[neighbor.node] = neighbor.cost;
           } else {
               if(dist[neighbor.node] > dist[neighbor.node] + neighbor.cost) {
                   dist[neighbor.node] = dist[neighbor.node] + neighbor.cost;
               }
           }
           pq.add(neighbor);
       }
    }

    // Driver code
    public static void main(String arg[])
    {
        int V = 5;
        int source = 0;

        // Adjacency list representation of the
        // connected edges
        List<List<Node> > adj = new ArrayList<List<Node> >();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<Node>();
            adj.add(item);
        }

        // Inputs for the DPQ graph
        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

        // Calculate the single source shortest path
        DPQ dpq = new DPQ(V);
        dpq.dijkstra(adj, source);

        // Print the shortest path to all the nodes
        // from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }
}

// Class to represent a node in the graph
class Node implements Comparator<Node> {
    public int node;
    public int cost;

    public Node()
    {
    }

    public Node(int node, int cost)
    {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.cost < node2.cost)
            return -1;
        if (node1.cost > node2.cost)
            return 1;
        return 0;
    }
}
