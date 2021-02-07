package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/perfect-sum-problem-print-subsets-given-sum/
public class SubSetSumsAllCombinations {
    List<List<Integer>> answer = new ArrayList<>();
    List<List<Integer>> subsets(int[] arr, int sum) {
        subsetsUtil(arr, 0, 0, new ArrayList<>(), 10);
        return answer;
    }

    void subsetsUtil(int[] arr, int current, int sum, List<Integer> intermediate, int k) {
        if(current >= arr.length) {
            return;
        }
        if(sum == k) {
            for(int i=0;i<intermediate.size();i++) {
                System.out.print(intermediate.get(i));
            }
            System.out.println();
            answer.add(intermediate);
        }
        for(int i=current;i<arr.length;i++) {
            if(sum + arr[i] > k) {
                continue;
            }
            // Check if it is repeated or not
            if (i > current &&
                    arr[i] == arr[i - 1])
                continue;
            intermediate.add(arr[i]);
            subsetsUtil(arr, i++, sum + arr[current], intermediate, k);
            if(intermediate.size() > 1 ){
                intermediate.remove(intermediate.size() - 1);
            }
        }
    }

    public static void main(String args[]) {
        SubSetSumsAllCombinations subSetSumsAllCombinations = new SubSetSumsAllCombinations();
        int[] arr = {1,2,3,4,5};
        subSetSumsAllCombinations.subsets(arr, 10);
    }
}
