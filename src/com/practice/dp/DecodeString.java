package com.practice.dp;

//https://leetcode.com/problems/decode-ways/
public class DecodeString {

    public static int numDecodings(String s) {
        int[] dp = new int[s.length()];
        if (s.indexOf('0') > 0) {
            return 0;
        }
        dp[0] = 1;
        if (s.charAt(0) == '1' || (s.charAt(0) == '2' && s.charAt(0) < '7')) {
            dp[1] = 2;
        } else {
            dp[1] = 1;
        }
        for (int i = 2; i < s.length(); i++) {
            dp[i] = dp[i] + dp[i - 1];
            if (s.charAt(i-2) == '1' ||
                    (s.charAt(i-2) == '2' && s.charAt(i-1) < '7') ) {
                dp[i] = dp[i] + dp[i - 2];
            }
        }
        return dp[s.length() - 1];
    }

    public static int numDecodingsCorrect(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[] dp = new int[n + 1]; // with extra space for [0] base case
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for(int i = 2; i <= n; i++) {
            Character c1 = s.charAt(i - 1);
            if(c1 != '0') {
                dp[i] += dp[i - 1];
            }

            Character c2 = s.charAt(i - 2);
            if(c2 == '1' || (c2 == '2' && c1 <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public static void main(String args[]) {
        System.out.print(numDecodingsCorrect("1234"));
    }
}
