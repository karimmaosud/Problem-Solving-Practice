package com.mirak.leetcode.individual.medium;

import java.util.*;

public class GeneralizedAbbreviation {

  private ArrayList<String> emptyList;

  public List<String> generateAbbreviations(String word) {
    emptyList = new ArrayList<>();
    emptyList.add("");
    ArrayList<String>[][] dp = new ArrayList[word.length()][2];
    return solve(word, 0, 1, dp);
  }

  private ArrayList<String> solve(String word, int index, int canSkip, ArrayList<String>[][] dp) {
    if (index >= word.length()) {
      return emptyList;
    }
    if (dp[index][canSkip] != null) {
      return dp[index][canSkip];
    }
    ArrayList<String> result = new ArrayList<>();
    // Always you can take this character.
    // Because you have taken it, so you always can skip next character.
    ArrayList<String> suffixList = solve(word, index + 1, 1, dp);

    for (String str : suffixList) {
      result.add("" + word.charAt(index) + str);
    }

    if (canSkip == 0) {
      return result;
    }

    for (int skipped = 1; skipped <= word.length() - index; skipped++) {
      // Try skip all possible ways.
      // Because you have skipped at least 1 character, you can not skip next character.
      suffixList = solve(word, index + skipped, 0, dp);
      for (String str : suffixList) {
        result.add("" + skipped + str);
      }
    }
    return dp[index][canSkip] = result;
  }

  private class BitManipulationSolution {
    public List<String> generateAbbreviations(String word) {
      int n = word.length();
      List<String> result = new LinkedList<>();
      for(int mask = 0; mask < (1 << n); mask++) {
        int ones = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
          if ((mask & (1 << i)) == 0) {
            // Not abbreviated character.
            if (ones > 0) {
              builder.append(ones);
            }
            builder.append(word.charAt(i));
            ones = 0;
          } else {
            ones++;
          }
        }
        if (ones != 0) {
          builder.append(ones);
        }
        result.add(builder.toString());
      }
      return result;
    }
  }
}
