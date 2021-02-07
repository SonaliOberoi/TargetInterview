package com.practice.arrays;

import java.util.*;

public class ProductCount {
    public static List<Integer> itemsSort(List<Integer> items) {
        // Write your code here
        if(items == null || items.isEmpty()) {
            return new ArrayList();
        }
        Map<Integer, Integer> countMap = new HashMap();
        for(int current: items) {
            int currentElementCount = countMap.getOrDefault(current, 0);
            countMap.put(current, currentElementCount + 1);
        }
        TreeMap<Integer,List<Integer>> treeMap = new TreeMap();
        for(Integer key: countMap.keySet()) {
            int countValue = countMap.get(key);
            List<Integer> currentList = treeMap.getOrDefault(countValue, new ArrayList());
            currentList.add(key);
            treeMap.put(countValue, currentList);
        }
        List<Integer> answer = new ArrayList();

        for(Map.Entry<Integer, List<Integer>> entry : treeMap.entrySet()) {
            Collections.sort(entry.getValue());
            answer.addAll(entry.getValue());
        }

        return answer;

    }


    public static long kSub(int k, List<Integer> nums) {
        // Write your code here
        if(nums == null || nums.isEmpty()) {
            return 0;
        }
        int[] remainder = new int[k];
        Arrays.fill(remainder, 0);
        int sumSoFar = 0;
        for(int i=0;i<nums.size();i++) {
            sumSoFar = sumSoFar + nums.get(i);
            remainder[((sumSoFar % k) + k) % k]++;
        }
        long count = 0;
        for(int i=0;i<remainder.length;i++) {
            if(remainder[i] > 1) {
                int temp = (remainder[i] * remainder[i] - 1)/2;
                count = count + temp;
            }
        }
        count = count + remainder[0];
        return count;
    }

    static int subCount(int arr[], int n, int k)
    {

        // create auxiliary hash array to
        // count frequency of remainders
        int mod[] = new int[k];
        Arrays.fill(mod, 0);

        // Traverse original array and compute cumulative
        // sum take remainder of this current cumulative
        // sum and increase count by 1 for this remainder
        // in mod[] array
        int cumSum = 0;
        for (int i = 0; i < n; i++) {
            cumSum += arr[i];

            // as the sum can be negative, taking modulo twice
            mod[((cumSum % k) + k) % k]++;
        }

        // Initialize result
        int result = 0;

        // Traverse mod[]
        for (int i = 0; i < k; i++)

            // If there are more than one prefix subarrays
            // with a particular mod value.
            if (mod[i] > 1)
                result += (mod[i] * (mod[i] - 1)) / 2;

        // add the elements which are divisible by k itself
        // i.e., the elements whose sum = 0
        result += mod[0];

        return result;
    }

    public static void main(String args[]) {
        List<Integer> list = new ArrayList();
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(4);
        itemsSort(list);

        int[] arr = {5, 45, 15, 7, 8, 10};
        System.out.println(subCount(arr, 6, 5));
        //List<Integer> list = Arrays.asList(arr);
        //System.out.println(kSub(5, list));
    }
}
