package com.practice.search;

//https://leetcode.com/problems/minimize-max-distance-to-gas-station/discuss/113629/Approach-the-problem-using-the-%22trial-and-error%22-algorithm
public class MinMaxGasStation {

    public static double minmaxGasDist(int[] stations, int K) {
        double l = 0, r = stations[stations.length - 1] - stations[0];
        // assuming the positions are sorted, or alternatively
        // we can go through the stations array to find the maximum distance between all adjacent stations

        while (r - l >= 1e-6) {
            double d = (r + l) / 2;

            int cnt = 0;

            for (int i = 0; i < stations.length - 1; i++) {
                cnt += Math.ceil((stations[i + 1] - stations[i]) / d) - 1;
            }

            if (cnt <= K) {
                r = d;
            } else {
                l = d;
            }
        }

        return l;
    }

    public static void main(String args[]) {
        int[] arr = {10,19,25,27,56,63,70,87,96,97};
        minmaxGasDist(arr, 3);
    }
}
