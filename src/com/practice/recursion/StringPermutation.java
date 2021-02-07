package com.practice.recursion;

public class StringPermutation {

    private void recPermuteUtil(String soFar, String rest) {
        if (rest.equals("")) {
            System.out.println(soFar);
        } else {
            for (int i = 0; i < rest.length(); i++) {
                String next = soFar + rest.charAt(i);
                String remaining = rest.substring(0, i) + rest.substring(i + 1);
                recPermuteUtil(next, remaining);
            }
        }
    }

    private void recSubsets(String soFar, String rest) {
        if (rest.equals("")) {
            System.out.println(soFar);
        } else {
            //add to subset remove the rest
            recSubsets(soFar + rest.charAt(0), rest.substring(1));
            //dont add to the subset and remove the rest
            recSubsets(soFar, rest.substring(1));
        }
    }

    public void recPermute(String s) {
        recPermuteUtil("", s);
    }

    public static void main(String args[]) {
        StringPermutation stringPermutation = new StringPermutation();
        stringPermutation.recPermute("abcd");
    }

}
