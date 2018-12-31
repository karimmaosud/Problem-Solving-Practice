package com.mirak.leetcode.individual.medium;

public class MirrorReflection {

  private enum Mirror {
    LEFT, RIGHT
  }

  public int mirrorReflection(int p, int q) {
    Mirror[] mirros = {Mirror.LEFT, Mirror.RIGHT};
    int index = 0;
    int start = 0;
    int reverseMirror = 1;
    while (start != p) {
      start += q;
      index = (index + 1) % 2;
      if (start > p) {
        reverseMirror ^= 1;
        start %= p;
      }
    }
    if (index == 0) {
      // LEFT mirror.
      return 2;
    }
    // Right mirror. either 0 (in case reverseMirror = 0), or 1 (in case reverseMirror = 1).
    return reverseMirror;
  }


  private class Solution2 {

    public int mirrorReflection(int p, int q) {
      int g = gcd(p, q);
      int meetingPoint = (p * q) / g;
      int steps = meetingPoint / q;
      if (steps % 2 == 0) {
        return 2;
      }
      int reflections = (meetingPoint / p) - 1;
      return reflections % 2 == 0 ? 1 : 0;
    }

    private int gcd(int a, int b) {
      return b == 0 ? a : gcd(b, a % b);
    }
  }
}
