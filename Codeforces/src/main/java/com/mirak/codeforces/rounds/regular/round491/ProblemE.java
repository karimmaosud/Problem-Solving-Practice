package com.mirak.codeforces.rounds.regular.round491;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ProblemE {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String str = reader.readLine();
    int[] digitCount = new int[10];
    Map<String, Long> dp = new HashMap<>();
    int digitsMask = initDigitsCount(digitCount, str);
    System.out.println(solve(digitCount, true, str.length(), "", 0, digitsMask, dp));
  }

  private static int initDigitsCount(int[] digitCount, String str) {
    int mask = 0;
    for(char a: str.toCharArray()) {
      mask |= (1 << (a - '0'));
      digitCount[a - '0']++;
    }
    return mask;
  }

  private static long solve(int[] digitCount, boolean start, int rem, String current, int mask, int digitsMask, Map<String, Long> dp) {
    if(rem == 0) {
      return 1L;
    }

    String hash = getHash(digitCount);

    if(dp.containsKey(hash)){
      return dp.get(hash);
    }

    long ans = mask == digitsMask? 1: 0;
    int end = start? 1: 0;
    for(int i = 9; i >= end; i--) {
      if(digitCount[i] != 0) {
        digitCount[i]--;
        ans += solve(digitCount, false, rem - 1, current + i, mask | (1 << i), digitsMask, dp);
        digitCount[i]++;
      }
    }
    dp.put(hash, ans);
    return ans;
  }

  private static String getHash(int[] digitsCount) {
    StringBuilder builder = new StringBuilder();
    for(int i = 0; i < 10; i++) {
      builder.append(digitsCount[i]).append('|');
    }
    return builder.toString();
  }
}
