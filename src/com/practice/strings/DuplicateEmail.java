package com.practice.strings;

import java.util.*;

public class DuplicateEmail {
    public int numUniqueEmails(String[] emails) {
        if(emails == null || emails.length < 1) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for(String current: emails) {
            StringBuilder sb = new StringBuilder();
            String subStr = current.substring(0, current.indexOf("@"));
            subStr.replaceAll(".", "");
            String plus = "\\+";
            subStr.replaceAll(plus, "");
            sb.append(subStr);
            sb.append(current.substring(current.indexOf("@"), current.length()));
            set.add(sb.toString());
        }
        return set.size();
    }
}
