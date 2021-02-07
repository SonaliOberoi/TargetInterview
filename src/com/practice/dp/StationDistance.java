package com.practice.dp;

import java.util.Arrays;

public class StationDistance {
    public static double minmaxGasDist(int[] stations, int K) {
        int lo = 0, hi = 87;
        while (hi  > lo) {
            int mi = (lo + hi) / 2;
            if (possible(mi, stations, K))
                hi = mi;
            else
                lo = mi;
        }
        return lo;
    }

    public static boolean possible(double D, int[] stations, int K) {
        int used = 0;
        for (int i = 0; i < stations.length - 1; ++i)
            used += (int) ((stations[i+1] - stations[i]) / D);
        return used <= K;
    }

    public static double minmaxGasDistBruteFiorce(int[] stations, int K) {
        int N = stations.length;
        double[] deltas = new double[N-1];
        for (int i = 0; i < N-1; ++i)
            deltas[i] = stations[i+1] - stations[i];

        int[] count = new int[N-1];
        Arrays.fill(count, 1);

        for (int k = 0; k < K; ++k) {
            // Find interval with largest part
            int best = 0;
            for (int i = 0; i < N-1; ++i)
                if (deltas[i] / count[i] > deltas[best] / count[best])
                    best = i;

            // Add gas station to best interval
            count[best]++;
        }

        double ans = 0;
        for (int i = 0; i < N-1; ++i)
            ans = Math.max(ans, deltas[i] / count[i]);

        return ans;
    }

    public static void main(String args[]) {
        int[] stations = {10,19,25,27,56,63,70,87,96,97};
        System.out.print(minmaxGasDist(stations, 3));
    }
}
