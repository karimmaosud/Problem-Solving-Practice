package com.mirak.leetcode.individual.hard;

public class CountTheRepetitions {

  public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
    int[] s2Repetitions = new int[s1.length()];
    int[] s1Repetitions = new int[s1.length()];
    int[] s2LastIndex = new int[s1.length()];
    int[] next = new int[s1.length()];
    initNextIndex(next, s1, s2);
    init(s1, s2, s2Repetitions, s2LastIndex, s1Repetitions);

    int totalS2Repitions = 0;
    int cursor = next[0];
    for (int i = 0; i < n1; i++) {
      if (cursor == -1) {
        return 0;
      }
      if (s2Repetitions[cursor] > 0) {
        totalS2Repitions += s2Repetitions[cursor];
        // move cursor to the next pointer
        int last = s2LastIndex[cursor];
        if (last + 1 < s1.length()) {
          if (next[last + 1] > last) {
            i--;
          }
          cursor = next[last + 1];
        } else {
          cursor = next[0];
        }
      } else {
        int s1Rep = s1Repetitions[cursor];
        if (i + s1Rep > n1) {
          break;
        }
        totalS2Repitions++;
        i = i + s1Rep - 1;
        int last = s2LastIndex[cursor];
        if (last + 1 < s1.length()) {
          if (next[last + 1] > last) {
            i--;
          }
          cursor = next[last + 1];
        } else {
          cursor = next[0];
        }
      }
    }
    return totalS2Repitions / n2;
  }

  private void initNextIndex(int[] next, String s1, String s2) {
    for (int i = 0; i < next.length; i++) {
      next[i] = -1;
    }
    for (int i = 0; i < s1.length(); i++) {
      if (s1.charAt(i) == s2.charAt(0)) {
        next[i] = i;
        for (int j = i - 1; j >= 0 && next[j] == -1; j--) {
          next[j] = i;
        }
      }
    }
    for (int i = s1.length() - 1; i >= 0 && next[i] == -1; i--) {
      next[i] = next[0];
    }
  }

  private void init(String s1, String s2, int[] s2Repetitions, int[] s2LastIndex,
      int[] s1Repetitions) {

    for (int i = 0; i < s1.length(); i++) {

      if (s1.charAt(i) != s2.charAt(0)) {
        // not valid i, first character don't match.
        continue;
      }
      // how many s2 can you get inside s1 starting from i.
      int s1Idx = i;
      int s2Idx = 0;
      while (s1Idx < s1.length()) {
        if (s1.charAt(s1Idx) == s2.charAt(s2Idx)) {
          s1Idx++;
          s2Idx++;
        } else {
          s1Idx++;
          continue;
        }
        if (s2Idx == s2.length()) {
          s2Repetitions[i]++;
          s2LastIndex[i] = s1Idx - 1;
          s2Idx = 0;
        }
      }
    }

    for (int i = 0; i < s1.length(); i++) {
      // if s2Repetitions[i] > 0, this means that you can get at least one s2 if you start from here.
      if (s1.charAt(i) != s2.charAt(0) || s2Repetitions[i] > 0) {
        continue;
      }
      // how many s1 repetitions needed if you start matching s2 from i
      // s1Repetitions[i] is at least 1.
      s1Repetitions[i] = 1;
      int s2Idx = 0;
      int s1Idx = i;
      while (s2Idx < s2.length()) {
        if (s1.charAt(s1Idx) == s2.charAt(s2Idx)) {
          s1Idx++;
          s2Idx++;
        } else {
          s1Idx++;
        }
        if (s1Idx == s1.length()) {
          // s1Idx hit the edge.
          if (s2Idx < s2.length()) {
            // you still need to match some characters in s2.
            s1Idx = 0;
            s1Repetitions[i]++;
          }
        }
      }
      s2LastIndex[i] = (s1Idx - 1 + s1.length()) % s1.length();
    }
  }
}


