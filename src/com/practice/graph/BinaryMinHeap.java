package com.practice.graph;

import java.util.*;
//to be used for Dijkshtra algorithm
/**
 *
 * Data structure to support following operations
 * extracMin - O(logn)
 * addToHeap - O(logn)
 * containsKey - O(1)
 * decreaseKey - O(logn)
 * getKeyWeight - O(1)
 *
 * It is a combination of binary heap and hash map
 *
 */
public class BinaryMinHeap<T> {

    private List<Node> allNodes = new ArrayList<>();
    private Map<T,Integer> nodePosition = new HashMap<>();

    public class Node {
        int weight;
        T key;
    }

    public boolean contains(T key) {
        return nodePosition.containsKey(key);
    }

    private int getParentPosition(int childPos) {
        return (childPos -1) /2;
    }

    /**
     * Get the heap min without extracting the key
     */
    public T min(){
        return allNodes.get(0).key;
    }

    /**
     * Checks with heap is empty or not
     */
    public boolean empty(){
        return allNodes.size() == 0;
    }

    /**
     * Get the weight of given key
     */
    public Integer getWeight(T key) {
        Integer position = nodePosition.get(key);
        if( position == null ) {
            return null;
        } else {
            return allNodes.get(position).weight;
        }
    }

    private void changeNodePosition(T data1, T data2, int pos1, int pos2) {
        nodePosition.remove(data1);
        nodePosition.remove(data2);
        nodePosition.put(data1, pos1);
        nodePosition.put(data2, pos2);
    }
    public void add(int weight, T key) {
        Node node = new Node();
        node.key = key;
        node.weight = weight;
        allNodes.add(node);
        nodePosition.put(key, allNodes.size() - 1);
        int currentIndex = allNodes.size() - 1;
        int parentIndex = getParentPosition(currentIndex);
        while(parentIndex >= 0) {
            if(allNodes.get(currentIndex).weight < allNodes.get(parentIndex).weight) {
                swap(allNodes.get(currentIndex), allNodes.get(parentIndex));
                changeNodePosition(allNodes.get(parentIndex).key,allNodes.get(currentIndex).key,parentIndex,currentIndex);
            } else {
                break;
            }
            currentIndex = parentIndex;
            parentIndex = getParentPosition(currentIndex);
        }
    }

    private void swap(Node node1,Node node2){
        int weight = node1.weight;
        T data = node1.key;

        node1.key = node2.key;
        node1.weight = node2.weight;

        node2.key = data;
        node2.weight = weight;
    }

    /**
     * Decreases the weight of given key to newWeight
     */
    public void decrease(T data, int newWeight){
        Integer position = nodePosition.get(data);
        allNodes.get(position).weight = newWeight;
        int parent = (position -1 )/2;
        while(parent >= 0){
            if(allNodes.get(parent).weight > allNodes.get(position).weight){
                swap(allNodes.get(parent), allNodes.get(position));
                changeNodePosition(allNodes.get(parent).key,allNodes.get(position).key,parent,position);
                position = parent;
                parent = (parent-1)/2;
            }else{
                break;
            }
        }
    }

    /**
     * Returns the min node of the heap
     */
    public Node extractMinNode() {
        int size = allNodes.size() -1;
        Node minNode = new Node();
        minNode.key = allNodes.get(0).key;
        minNode.weight = allNodes.get(0).weight;

        int lastNodeWeight = allNodes.get(size).weight;
        allNodes.get(0).weight = lastNodeWeight;
        allNodes.get(0).key = allNodes.get(size).key;
        nodePosition.remove(minNode.key);
        nodePosition.remove(allNodes.get(0));
        nodePosition.put(allNodes.get(0).key, 0);
        allNodes.remove(size);

        int currentIndex = 0;
        size--;
        while(true){
            int left = 2*currentIndex + 1;
            int right = 2*currentIndex + 2;
            if(left > size){
                break;
            }
            if(right > size){
                right = left;
            }
            int smallerIndex = allNodes.get(left).weight <= allNodes.get(right).weight ? left : right;
            if(allNodes.get(currentIndex).weight > allNodes.get(smallerIndex).weight){
                swap(allNodes.get(currentIndex), allNodes.get(smallerIndex));
                changeNodePosition(allNodes.get(currentIndex).key,allNodes.get(smallerIndex).key,currentIndex,smallerIndex);
                currentIndex = smallerIndex;
            }else{
                break;
            }
        }
        return minNode;
    }
    /**
     * Extract min value key from the heap
     */
    public T extractMin(){
        Node node = extractMinNode();
        return node.key;
    }

    public void printHeap(){
        for(Node n : allNodes){
            System.out.println(n.weight + " " + n.key);
        }
    }
    private void printPositionMap(){
        System.out.println(nodePosition);
    }

    public static void main(String args[]){
        BinaryMinHeap<String> heap = new BinaryMinHeap<String>();
        heap.add(3, "Tushar");
        heap.add(4, "Ani");
        heap.add(8, "Vijay");
        heap.add(10, "Pramila");
        heap.add(5, "Roy");
        heap.add(6, "NTF");
        heap.add(2,"AFR");
        heap.decrease("Pramila", 1);
        heap.printHeap();
        heap.printPositionMap();
    }
}