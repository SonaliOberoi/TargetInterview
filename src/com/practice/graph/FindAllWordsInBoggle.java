package com.practice.graph;

//https://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/
public class FindAllWordsInBoggle {

    static final String dictionary[] = { "GEEKS", "FOR", "QUIZ", "GUQ", "EE" };
    static final int n = dictionary.length;
    static final int M = 3, N = 3;

    static boolean isWord(String str)
    {
        // Linearly search all words
        for (int i = 0; i < n; i++)
            if (str.equals(dictionary[i]))
                return true;
        return false;
    }
    static void findWordsUtil(char[][] boggle, boolean[][] visited, int row, int col, String stringSoFar) {
        if (row < 0 || col <0 || row >= boggle.length || col >= boggle[row].length || visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        stringSoFar = stringSoFar + boggle[row][col];
        if (isWord(stringSoFar)) {
            System.out.println(stringSoFar);
            return;
        }
        findWordsUtil(boggle, visited, row - 1, col - 1, stringSoFar);
        findWordsUtil(boggle, visited, row - 1, col + 1, stringSoFar);
        findWordsUtil(boggle, visited, row - 1, col, stringSoFar);
        findWordsUtil(boggle, visited, row + 1, col - 1, stringSoFar);
        findWordsUtil(boggle, visited, row + 1, col + 1, stringSoFar);
        findWordsUtil(boggle, visited, row + 1, col, stringSoFar);
        findWordsUtil(boggle, visited, row, col - 1, stringSoFar);
        findWordsUtil(boggle, visited, row, col + 1, stringSoFar);

        visited[row][col] = false;
        stringSoFar = stringSoFar.substring(0, stringSoFar.length() - 1);

    }
    // Prints all words present in dictionary.
    static void findWords(char boggle[][])
    {
        // Mark all characters as not visited
        boolean visited[][] = new boolean[M][N];

        // Initialize current string
        String str = "";

        // Consider every character and look for all words
        // starting with this character
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                findWordsUtil(boggle, visited, i, j, str);
    }
    public static void main(String args[])
    {
        char boggle[][] = { { 'G', 'I', 'Z' },
                { 'U', 'E', 'K' },
                { 'Q', 'S', 'E' } };

        System.out.println("Following words of dictionary are present");
        findWords(boggle);
    }
}
