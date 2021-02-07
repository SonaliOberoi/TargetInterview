package com.practice.graph;

import com.practice.recursion.PhoneNumberCombination;

import java.util.*;
//https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class PhoneCombinationGraph {

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

    public List<String> letterCombinations(String digits) {
        letterCombinationsUtil(digits, "", digits);
       // result("",  digits);
        return output;
    }

    public void result(String combinationSoFar, String digits) {
        if (digits.length() == 0) {
            // we have explored all digits
            output.add(combinationSoFar);
            return;
        }
        String currentDigitToExplore = digits.substring(0, 1);
        String lettersOnDigit = phone.getOrDefault(currentDigitToExplore, "");
        for (int i = 0; i < lettersOnDigit.length(); i++){
            combinationSoFar = combinationSoFar + (lettersOnDigit.charAt(i));
            result(combinationSoFar, digits.substring(1));
            combinationSoFar = combinationSoFar.substring(0, combinationSoFar.length() - 1);
        }
    }

    private void letterCombinationsUtil(String digits, String stringSoFar, String currentDigit) {
        if(stringSoFar.length() == digits.length() || currentDigit.length() == 0) {
            output.add(stringSoFar);
            return;
        }
        String current = currentDigit.substring(0, 1);
        String leftOver = currentDigit.substring(1);
        String phoneLetters = phone.getOrDefault(current, "");
        for(int i = 0;i<phoneLetters.length();i++) {
            letterCombinationsUtil(digits, stringSoFar + phoneLetters.charAt(i), leftOver);
        }
    }

    public static void main(String args[]) {
        PhoneCombinationGraph phoneNumberCombination = new PhoneCombinationGraph();
        System.out.println(phoneNumberCombination.letterCombinations("1234"));
    }

}
