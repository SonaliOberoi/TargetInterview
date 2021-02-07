package com.practice.recursion;

import java.util.HashSet;
import java.util.Set;

public class WordBreakRecursion {

    // set to hold dictionary values
    private static Set<String> dictionary = new HashSet<>();

    public static boolean wordBreak(String str) {
        if(str.length() == 0) {
            return true;
        }
        for(int i=0;i<str.length();i++) {
            if(dictionary.contains(str.substring(0, i)) && wordBreak(str.substring(i))) {
                return true;
            }
        }
        return false;
    }
    public static void main(String []args)
    {

        // array of strings to be added in dictionary set.
        String temp_dictionary[] = {"mobile","samsung","sam","sung",
                "man","mango","icecream","and",
                "go","i","like","ice","cream"};

        // loop to add all strings in dictionary set
        for (String temp :temp_dictionary)
        {
            dictionary.add(temp);
        }

        // sample input cases
        System.out.println(wordBreak("samsungandmangok"));
        System.out.println(wordBreak("ilikesamsung"));
        System.out.println(wordBreak("iiiiiiii"));
        System.out.println(wordBreak(""));
        System.out.println(wordBreak("ilikelikeimangoiii"));
        System.out.println(wordBreak("samsungandmango"));


    }

}
