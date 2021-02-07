package com.practice.recursion;

import java.util.*;

public class PhoneNumberCombination {
    Map<String, String> phone = new HashMap<>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    List<String> output = new ArrayList<>();

    public void result(String combinationSoFar, String digits) {
        if (digits.length() == 0) {
            // we have explored all digits
            output.add(combinationSoFar);
            return;
        }
        String currentDigitToExplore = digits.substring(0, 1);
        String lettersOnDigit = phone.get(currentDigitToExplore);
        for (int i = 0; i < lettersOnDigit.length(); i++){
            combinationSoFar = combinationSoFar + (lettersOnDigit.charAt(i));
            result(combinationSoFar, digits.substring(1));
            combinationSoFar = combinationSoFar.substring(0, combinationSoFar.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        result("", digits);
        return output;
    }

    static ArrayList<String> letterCombinationsUtil(int[] number, int n,
                                                    String[] table)
    {
        // To store the generated letter combinations
        ArrayList<String> list = new ArrayList<>();

        Queue<String> q = new LinkedList<>();
        q.add("");

        while(!q.isEmpty())
        {
            String s = q.remove();

            // If complete word is generated
            // push it in the list
            if (s.length() == n)
                list.add(s);
            else
            {
                String val = table[number[s.length()]];
                for (int i = 0; i < val.length(); i++)
                {
                    q.add(s + val.charAt(i));
                }
            }
        }
        return list;
    }

    // Function that creates the mapping and
    // calls letterCombinationsUtil
    static void letterCombinations(int[] number, int n)
    {
        // table[i] stores all characters that
        // corresponds to ith digit in phone
        String[] table = { "", "", "abc", "def", "ghi", "jkl",
                "mno", "pqrs", "tuv", "wxyz" };

        ArrayList<String> list =
                letterCombinationsUtil(number, n, table);

        // Print the contents of the list
        for (int i = 0; i < list.size(); i++)
        {
            System.out.print(list.get(i) + " ");
        }
    }


    public static void main(String args[]) {
        PhoneNumberCombination phoneNumberCombination = new PhoneNumberCombination();

        System.out.println(phoneNumberCombination.letterCombinations( "239"));

        int[] number = { 1, 3, 4 };
        int n = number.length;
        letterCombinations(number, n);

    }
}
