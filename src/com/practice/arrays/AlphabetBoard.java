package com.practice.arrays;

//https://leetcode.com/problems/alphabet-board-path/
public class AlphabetBoard {
    public static String alphabetBoardPath(String target) {
        if(target == null || target.length() < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int previousRow = 1;
        int previousCol = 1;
        char prev = 'a';
        for(int j=0;j<target.length();j++) {
            char current = target.charAt(j);
            int currentIndex = current - 'a' + 1;
            int currentRow = currentIndex/5 + (currentIndex%5 == 0 ? 0 : 1);
            int currentCol = currentIndex%5 == 0 ? 5 : currentIndex%5;

            if(currentRow == previousRow && currentCol == previousCol) {
                sb.append("!");
                continue;
            }

            int rowDiff = previousRow - currentRow;
            int colDiff = previousCol - currentCol;
            if(prev == 'z') {
                for(int i=0;i<Math.abs(rowDiff);i++) {
                    if(rowDiff < 0) {
                        sb.append("D");
                    } else {
                        sb.append("U");
                    }
                }
                for(int i=0;i<Math.abs(colDiff);i++) {
                    if(colDiff < 0) {
                        sb.append("R");
                    } else {
                        sb.append("L");
                    }
                }
            } else {
                for(int i=0;i<Math.abs(colDiff);i++) {
                    if(colDiff < 0) {
                        sb.append("R");
                    } else {
                        sb.append("L");
                    }
                }
                for(int i=0;i<Math.abs(rowDiff);i++) {
                    if(rowDiff < 0) {
                        sb.append("D");
                    } else {
                        sb.append("U");
                    }
                }
            }


            sb.append("!");
            //System.out.println("currentRow:" + currentRow);
            //System.out.println("currentCol:" + currentCol);
            previousRow = currentRow;
            previousCol = currentCol;
            prev = current;
        }
        return sb.toString();
    }
    public static void main(String args[]) {
        alphabetBoardPath("zdz");
    }
}
