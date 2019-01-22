package com.mirak.leetcode.individual.easy;

public class RepeatedStringMatch {

  private final long BASE = 31L;
  private final int MOD = 1000000007;
  private long[] baseMod;

  public int repeatedStringMatch(String A, String B) {

    baseMod = new long[10001];
    initBaseMod();

    if (A.length() > B.length()) {
      int bOccurrence = rabinKarp(A + A, B);
      if (bOccurrence == -1) {
        return -1;
      }
      return bOccurrence + B.length() <= A.length() ? 1 : 2;
    }

    int numOccurrence = B.length() / A.length();
    if (A.length() * numOccurrence < B.length()) {
      numOccurrence++;
    }

    String minimalRepeat = repeatedString(A, numOccurrence);

    if (rabinKarp(minimalRepeat, B) != -1) {
      return numOccurrence;
    }

    if (rabinKarp(minimalRepeat + A, B) != -1) {
      return numOccurrence + 1;
    }

    return -1;
  }

  private int rabinKarp(String t, String s) {
    long hash = 0;
    int n = s.length();
    long runningHash = 0;
    for (int i = 0; i < s.length(); i++) {
      hash = (hash + (s.charAt(i) - 'a') * baseMod[n - i - 1]) % MOD;
      runningHash = (runningHash + (t.charAt(i) - 'a') * baseMod[n - i - 1]) % MOD;
    }

    if (runningHash == hash) {
      return 0;
    }

    for (int i = s.length(); i < t.length(); i++) {
      runningHash =
          (((runningHash - (t.charAt(i - n) - 'a') * baseMod[n - 1] + MOD) % MOD * BASE) % MOD +
              t.charAt(i) - 'a') % MOD;
      if (runningHash == hash) {
        return i - n + 1;
      }
    }
    return -1;
  }

  private String repeatedString(String A, int n) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < n; i++) {
      builder.append(A);
    }
    return builder.toString();
  }

  private void initBaseMod() {
    baseMod[0] = 1;
    for (int i = 1; i < baseMod.length; i++) {
      baseMod[i] = (baseMod[i - 1] * BASE) % MOD;
    }
  }

}
