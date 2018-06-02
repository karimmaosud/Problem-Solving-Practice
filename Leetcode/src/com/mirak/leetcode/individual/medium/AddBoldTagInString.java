package com.mirak.leetcode.individual.medium;

public class AddBoldTagInString {
  public String addBoldTag(String s, String[] dict) {

    int[] mark = new int[s.length() + 2];

    for(String str: dict) {
      int idx = s.indexOf(str);
      while(idx != -1) {
        mark[idx] += 1;
        mark[idx + str.length()] -= 1;
        idx = s.indexOf(str, idx + 1);
      }
    }

    for(int i = 1; i < mark.length; i++) {
      mark[i] += mark[i - 1];
    }

    StringBuilder builder = new StringBuilder();
    int idx = 0;
    while(idx < s.length()) {
      if(mark[idx] != 0) {
        builder.append("<b>");
        while(idx < s.length() && mark[idx] != 0) {
          builder.append(s.charAt(idx++));
        }
        builder.append("</b>");
      }else{
        builder.append(s.charAt(idx++));
      }
    }
    return builder.toString();
  }
}

