package com.practice.dp;

//https://leetcode.com/problems/filling-bookcase-shelves/
public class BookShelf {
    public int minHeightShelves(int[][] books, int shelf_width) {
        int totalBooks = books.length;
        int[] dp = new int[totalBooks];
        dp[0] = books[0][1];

        for(int i=1;i<totalBooks;i++) {
            int height = books[i][1];
            int widthSoFar = books[i][0];
            dp[i] = dp[i - 1] + height;
            int backTrack = i - 1;
            while (backTrack>=0 && widthSoFar < shelf_width) {
                widthSoFar = widthSoFar + books[backTrack][0];
                if(widthSoFar <= shelf_width) {
                    height = Math.max(height, books[backTrack][1]);
                    dp[i] = Math.min(dp[i], backTrack - 1 >=0 ? dp[backTrack - 1] + height : height);
                }
                backTrack--;
            }
        }
        return dp[totalBooks - 1];
    }

    public static void main(String args[]) {
        int[][] books = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
        BookShelf bookShelf =  new BookShelf();
        System.out.println(bookShelf.minHeightShelves(books, 4));
    }
}
