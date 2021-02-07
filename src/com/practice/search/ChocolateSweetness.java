package com.practice.search;

public class ChocolateSweetness {
    public static int maximizeSweetness(int[] s, int k) {
        if(s == null || s.length < k) {
            return -1;
        }

        long left = s[0];
        long right = s[0];
        for (int i=1;i<s.length;i++) {
            right = right + s[i];
            if(s[i] < left) {
                left = s[i];
            }
        }
        long ans = right;
        while(left < right) {
            long mid = (left + right) /2;
            int count = 0;
            long currentSweetness = 0;
            for (int i=0;i<s.length;i++) {
                if(currentSweetness + s[i] > mid) {
                    count ++;
                    currentSweetness = s[i];
                } else {
                    currentSweetness = currentSweetness + s[i];
                }
            }
            if (count <= k + 1) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return (int)left;

    }
    public static void main(String args[]) {
        int a[] = {1,2,3,4,5,6,7,8,9};
        maximizeSweetness(a, 5);
    }
}
