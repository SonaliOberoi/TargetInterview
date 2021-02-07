package com.practice.dp;

import java.util.*;

public class CoinPath {
    public List<Integer> cheapestJump(int[] A, int B) {
        List<Integer> list = new ArrayList<>();
        if(A == null || A.length < 0||B < 1) {
            return list;
        }
        int[] dp = new int[A.length];
        int[] index =new int[A.length];
        dp[0] = A[0];
        index[0] = -1;
        for(int i=1;i<dp.length;i++) {
            dp[i] = Integer.MAX_VALUE;
            index[i] = -1;
        }
        for(int i=0;i<A.length - 1;i++) {
            if (A[i] == -1) {
                continue;
            }
            for(int j=1;j<=B && i+j < A.length;j++) {
                if(A[i+j] != -1) {
                    if(dp[i+j] > dp[i] + A[i+j]) {
                        dp[i+j] = dp[i] + A[i+j];
                        index[i+j] = i;
                    }
                    System.out.println("dp:" + dp[i+j]);
                    System.out.println("index:" + index[i+j]);
                }
            }
        }
        System.out.println(Arrays.toString(index));
        System.out.println(Arrays.toString(dp));
        if(index[A.length - 1] == -1) {
            return list;
        } else {
            for(int i = index.length -1;i>=0;i--) {
                list.add(index[i] + 1);
                i = index[i];
            }
            Collections.reverse(list);
            return list;
        }
    }

    public static void main(String args[]) {
        CoinPath coinPath = new CoinPath();
        int[] Arr = {1,2,4,-1,2};
        coinPath.cheapestJump(Arr,2);
    }
}
