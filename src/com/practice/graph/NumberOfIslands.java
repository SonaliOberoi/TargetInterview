package com.practice.graph;

public class NumberOfIslands {

    private void countUtil(int[][] graph, boolean[][] visited, int i, int j) {
        if (i < 0 || j <0 || i >= graph.length || j >= graph[i].length) {
            return;
        }
        visited[i][j] = true;
        if(graph[i][j] == 0){
            return;
        }
        countUtil(graph,visited,i,j+1);
        countUtil(graph,visited,i+1,j);
        countUtil(graph,visited,i+1,j+1);
        countUtil(graph,visited,i-1, j+1);

    }

    public int count(int[][] graph) {
        boolean[][] visited = new boolean[graph.length][graph.length];
        int count = 0;
        for(int i=0; i < graph.length ; i ++){
            for(int j =0 ; j < graph[i].length ; j++){
                if(visited[i][j] == false && graph[i][j] == 1) {
                    count++;
                    countUtil(graph,visited,i,j);
                }
            }
        }
        return count;
    }

    public static void main(String args[]) {
        int matrix[][] = {{1,1,0,1,0},
                          {1,0,0,1,1},
                          {0,0,0,0,0},
                          {1,0,1,0,0},
                          {1,0,0,0,0}
        };

        NumberOfIslands numberOfIslands = new NumberOfIslands();
        System.out.println(numberOfIslands.count(matrix));

    }
}
