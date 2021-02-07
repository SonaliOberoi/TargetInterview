package com.practice.recursion;

import java.util.ArrayList;
import java.util.List;

//https://www.geeksforgeeks.org/combinational-sum/
public class CombinationSum {

    private void combinationSumUtil(int arr[], int sum, List<Integer> sumList, int current) {
        if (sum < 0) {
            return;
        }
        if (sum == 0) {
            System.out.println("Combination found");
            for(int element: sumList) {
                System.out.print(element + " , ");
            }
            return;
        }

        while (current < arr.length && sum - arr[current] >= 0) {

            sumList.add(arr[current]);
            combinationSumUtil(arr, sum - arr[current], sumList, current);
            current ++;
            sumList.remove(sumList.get(sumList.size() - 1));
        }
    }
    public void combinationSum(int arr[]) {
        combinationSumUtil(arr, 8, new ArrayList<>(), 0);
    }
    public static void main(String args[]) {
        int arr[] = {1,2, 4,6,8};
        CombinationSum combinationSum = new CombinationSum();
        combinationSum.combinationSum(arr);
    }
}
