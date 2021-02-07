package com.practice.disjointsets;

import java.util.*;

//https://leetcode.com/problems/number-of-islands-ii/
public class NumberOfIslands2 {
    Map<String, Node> map = new HashMap<>();
    int[][] dir = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        if(positions == null || positions.length < 0) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> list = new ArrayList<>();
        int[][] grid = new int[m][n];
        for(int[] currentPos: positions) {
            int x = currentPos[0];
            int y = currentPos[1];
            grid[x][y] = 1;
            String data = String.valueOf(x) + ":" + String.valueOf(y);
            Node node = new Node(data, 1, null);
            node.parent = node;
            map.put(data, node);
            System.out.println("current node: " + data);
            //do a union in all 4 directions and calculate number of nodes
            for(int[] currDir: dir) {
                int xx = x + currDir[0];
                int yy = y + currDir[1];
                if (!isValid(xx, yy, m, n) || grid[xx][yy] != 1) {
                    continue;
                }
                String neighbourData = String.valueOf(xx) + ":" + String.valueOf(yy);
                union(data, neighbourData);
            }
            System.out.println("Adding node");

            Set<Node> set = new HashSet<>();
            map.forEach((k,v) -> {
                System.out.println(k);
                System.out.println(v.parent.data);
                Node temp = v;
                while (temp != temp.parent) {
                    temp = temp.parent;
                }
                set.add(temp);
            });

            list.add(set.size());
        }
        return list;
    }

    boolean isValid(int x, int y, int rowLen, int colLen) {
        return  (x >= 0 && x < rowLen && y >=0 && y < colLen);
    }

    boolean union(String a, String b) {
        Node parent1 = findUtil(a);
        Node parent2 = findUtil(b);

        if(parent1 == parent2) {
            return false;
        }
        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    private Node findUtil(String a) {
        Node node = map.get(a);

        if (node == node.parent) {
            return node;
        }

        node.parent = findUtil(node.parent.data);
        return node.parent;
    }


    class Node {
        String data;
        Node parent;
        int rank;
        Node(String data, int rank, Node parent) {
            this.data = data;
            this.rank = rank;
            this.parent = parent;
        }
    }

    public static void main(String args[]) {
        int[][] pos = {{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        NumberOfIslands2 numberOfIslands2 = new NumberOfIslands2();
        System.out.println(numberOfIslands2.numIslands2(3,3,pos));
    }
}
