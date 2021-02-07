package com.practice.recursion;

import java.util.*;
import java.util.stream.Collectors;

//gives unique permutation order
public class SonaliPermutationNumber {
    List<List<Integer>> listOfList = new ArrayList();
    Set<String> set = new HashSet<>();
    public List<List<Integer>> permute(int[] nums) {
        if(nums == null || nums.length == 0) {
            return null;
        }
        if(nums.length == 1) {
            List<Integer> list = new ArrayList<>();
            list.add(nums[0]);
            listOfList.add(list);
            return listOfList;
        }
        Arrays.sort(nums);
        recur(Arrays.stream(nums).boxed().collect(Collectors.toList()), new ArrayList<>(), nums.length);
        System.out.println(listOfList);
        return listOfList;
    }

    private void recur(List<Integer> remaining, List<Integer> target, int length) {
        if(target.size() == length) {
            String str = target.toString();
            if(set.contains(str)) {
                return;
            }
            set.add(str);
            listOfList.add(new ArrayList<>(target));
            return;
        }
        for(int i=0;i<remaining.size();i++) {
            target.add(remaining.get(i));
            List<Integer> newRemainingList = new ArrayList<>(remaining);
            newRemainingList.remove(i);
            recur(newRemainingList, target, length);
            target.remove(target.size() - 1);
        }
    }

    public static void main(String args[]) {
        int[] arr = {1,1,3};
        SonaliPermutationNumber sonaliPermutationNumber = new SonaliPermutationNumber();
        sonaliPermutationNumber.permute(arr);
    }
}
