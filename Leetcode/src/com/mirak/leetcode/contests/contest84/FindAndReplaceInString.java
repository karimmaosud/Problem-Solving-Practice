package com.mirak.leetcode.contests.contest84;

public class FindAndReplaceInString {
  public static String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
    String[] replace = new String[S.length()];
    int[]skip = new int[S.length()];
    for(int i = 0; i < indexes.length; i++) {
      if(S.indexOf(sources[i], indexes[i]) == indexes[i]) {
        replace[indexes[i]] = targets[i];
        skip[indexes[i]] = sources[i].length();
      }
    }
    StringBuilder builder = new StringBuilder();
    for(int i = 0; i < S.length(); i++) {
      if(replace[i] != null && !replace[i].isEmpty()) {
        builder.append(replace[i]);
        i += skip[i] - 1;
        continue;
      }
      builder.append(S.charAt(i));
    }
    return builder.toString();
  }
}
