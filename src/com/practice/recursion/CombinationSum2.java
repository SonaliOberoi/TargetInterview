package com.practice.recursion;

import java.util.*;

//https://www.geeksforgeeks.org/all-unique-combinations-whose-sum-equals-to-k/
public class CombinationSum2 {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<Integer>(), candidates, target, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] cand, int remain, int start) {

        if(remain < 0) return; /** no solution */
        else if(remain == 0) {
            for(int i=0;i<tempList.size();i++) {
                System.out.print(tempList.get(i));
            }
            System.out.println();
            list.add(new ArrayList<>(tempList));}
        else{
            for (int i = start; i < cand.length; i++) {
                if(i > start && cand[i] == cand[i-1]) continue; /** skip duplicates */
                tempList.add(cand[i]);
                backtrack(list, tempList, cand, remain - cand[i], i+1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    // Driver code
    public static void main(String[] args)
    {
        int []arr = { 10, 1, 2, 7, 6, 1, 5 };

        int K = 8;
        combinationSum(arr, K);

    }
}
