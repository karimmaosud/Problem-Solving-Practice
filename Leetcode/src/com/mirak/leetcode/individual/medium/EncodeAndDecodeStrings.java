package com.mirak.leetcode.individual.medium;

import java.util.LinkedList;
import java.util.List;

public class EncodeAndDecodeStrings {

}

class Codec {

  // Encodes a list of strings to a single string.
  public String encode(List<String> strs) {
    StringBuilder builder = new StringBuilder();
    for(String str: strs) {
      builder.append(str.length()).append('#').append(str);
    }
    return builder.toString();
  }

  // Decodes a single string to a list of strings.
  public List<String> decode(String s) {
    List<String> decoded = new LinkedList<>();
    int i = 0;
    while(i < s.length()) {
      int len = 0;
      while(s.charAt(i) != '#') {
        len = len * 10 + s.charAt(i) - '0';
        i++;
      }
      // s.charAt(i) = '#'
      i++;
      decoded.add(s.substring(i, i + len));
      i += len;
    }
    return decoded;
  }
}