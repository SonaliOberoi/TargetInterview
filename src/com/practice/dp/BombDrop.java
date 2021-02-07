package com.practice.dp;

//https://leetcode.com/problems/bomb-enemy/submissions/
public class BombDrop {
        // Let F(i, j) -> (top, bottom, left, right) be max enemy to kill at each direction if bomb at (i, j)
        // F(i, j).top = F(i-1, j).top + 1 if grid[i][j] = 'E'
        // F(i, j).left = F(i, j-1).left + 1 if grid[i][j] = 'E'
        // F(i, j).right = F(i, j+1).right + 1 if necessary
        // F(i, j).bottom = F(i+1, j).bottom + 1 if necessary

        // Therefore we need to start build dp array from both top left and bottom right corner
        // Once have we have # of enemy at 4 direction, we can find max # of enemy
        public int maxKilledEnemies(char[][] grid) {
            if (grid.length == 0 || grid[0].length == 0) {
                return 0;
            }

            int M = grid.length;
            int N = grid[0].length;

            // Count of enemy killed at each direction if bomb is dropped at grid[i][j]
            Count[][] dp = new Count[M][N];
            int answer = 0;

            // First pass. Count left and top
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    dp[i][j] = new Count();
                    if (grid[i][j] == 'W') {
                        continue;
                    }

                    // count.left and count.top includes enemy at grid[i][j]
                    if (grid[i][j] == 'E') {
                        dp[i][j].left++;
                        dp[i][j].top++;
                    }

                    dp[i][j].left += (j == 0) ? 0 : dp[i][j - 1].left;
                    dp[i][j].top += (i == 0) ? 0 : dp[i - 1][j].top;
                }
            }

            // Second pass. Count right and bottom. Find answer
            for (int i = M - 1; i >= 0; i--) {
                for (int j = N - 1; j >= 0; j--) {
                    if (grid[i][j] == 'W') {
                        continue;
                    }

                    // count.right and count.bottom includes enemy at grid[i][j]
                    if (grid[i][j] == 'E') {
                        dp[i][j].right++;
                        dp[i][j].bottom++;
                    }

                    dp[i][j].right += (j == N - 1) ? 0 : dp[i][j + 1].right;
                    dp[i][j].bottom += (i == M - 1) ? 0 : dp[i + 1][j].bottom;

                    // Find answer
                    if (grid[i][j] == '0') {
                        answer = Math.max(answer, dp[i][j].top + dp[i][j].bottom + dp[i][j].left + dp[i][j].right);
                    }
                }
            }

            return answer;
        }

        // # of enemy killed to each direction, include killed at current position
        private class Count {
            int left;
            int top;
            int right;
            int bottom;
        }

}
