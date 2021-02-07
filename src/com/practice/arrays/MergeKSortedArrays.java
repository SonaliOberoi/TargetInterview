package com.practice.arrays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//https://www.geeksforgeeks.org/merge-k-sorted-arrays-set-2-different-sized-arrays/
public class MergeKSortedArrays {
    class ElementInfo {
        int element;
        int row;
        int col;
        ElementInfo(int element, int row, int col) {
            this.element = element;
            this.row = row;
            this.col = col;
        }
    }

    public void mergeKSortedArrays(int[][] matrix, int rows, int cols, List<Integer> outputArray) {
        PriorityQueue<ElementInfo> queue = new PriorityQueue<>(new Comparator<ElementInfo>() {
            public int compare(ElementInfo a, ElementInfo b)
            {
                return a.element - b.element;
            }
        });
        for (int i= 0; i < rows ; i++) {
            queue.add(new ElementInfo(matrix[i][0], i, 0));
        }
        while(!queue.isEmpty() || queue.peek().element != Integer.MAX_VALUE) {
            ElementInfo current = queue.poll();
            outputArray.add(current.element);
            if (current.col + 1 < matrix[current.row].length) {
                queue.add(new ElementInfo(matrix[current.row][current.col + 1], current.row, current.col + 1));
            } else {
                queue.add(new ElementInfo(Integer.MAX_VALUE, current.row, Integer.MAX_VALUE));
            }

        }
    }
    public static void main(String args[]) {
        int[][] arr= {{2, 6, 12},
                      {1, 9, 20, 1000},
                      {23, 34, 90, 2000 } };

        System.out.println("Merged array is :");
        MergeKSortedArrays mergeKSortedArrays = new MergeKSortedArrays();
        mergeKSortedArrays.mergeKSortedArrays(arr, arr.length, arr[0].length, new ArrayList<>());
    }
}

