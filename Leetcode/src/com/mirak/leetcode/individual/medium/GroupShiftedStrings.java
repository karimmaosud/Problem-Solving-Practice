package com.mirak.leetcode.individual.medium;

import java.util.*;
import java.util.Map.Entry;

public class GroupShiftedStrings {

  public List<List<String>> groupStrings(String[] strings) {
    Map<String, LinkedList<String>> map = new HashMap<>();

    for (String str : strings) {
      String strMapping = getStringMapping(str);
      map.putIfAbsent(strMapping, new LinkedList<>());
      map.get(strMapping).add(str);
    }

    List<List<String>> groups = new LinkedList<>();

    for (Entry<String, LinkedList<String>> entry : map.entrySet()) {
      groups.add(entry.getValue());
    }
    return groups;
  }

  private String getStringMapping(String str) {
    StringBuilder builder = new StringBuilder();
    for (int i = 1; i < str.length(); i++) {
      builder.append('a' + (26 + str.charAt(i) - str.charAt(i - 1)) % 26);
    }
    return builder.toString();
  }
}
