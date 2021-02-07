package com.practice.strings;

import java.util.*;

//https://leetcode.com/problems/find-duplicate-file-in-system/
public class FileDuplicate {
    Map<String, List<String>> map = new HashMap();
    public List<List<String>> findDuplicate(String[] paths) {
        if(paths == null || paths.length < 1) {
            return Collections.EMPTY_LIST;
        }
        for(String string: paths) {
            String[] splited = string.split("\\s+");
            for (int i = 1;i<splited.length;i++) {
                String currentFile = splited[i];
                String text = currentFile.substring(currentFile.indexOf('(') + 1, currentFile.length() - 1);
                List<String> list;
                if(map.containsKey(text)) {
                    list = map.get(text);

                } else {
                    list = new ArrayList<>();
                }
                list.add(splited[0]+ "/"+ currentFile.substring(0, currentFile.indexOf("(")));
                map.put(text, list);
            }

        }
        List<List<String>> answer = new ArrayList<>();
        map.forEach((k, v) -> {
            if(v.size() > 1) {
                answer.add(v);
            }

        });
        return answer;
    }

    public static void main(String args[]) {
        String test = "root/a 1.txt(abcd)";
        System.out.println(test.substring(0, test.indexOf("(")));

        String[] input = {"root/a 1.txt(abcd) 2.txt(efsfgh)","root/c 3.txt(abdfcd)","root/c/d 4.txt(efggdfh)"};
        FileDuplicate fileDuplicate = new FileDuplicate();
        fileDuplicate.findDuplicate(input);
    }
}
