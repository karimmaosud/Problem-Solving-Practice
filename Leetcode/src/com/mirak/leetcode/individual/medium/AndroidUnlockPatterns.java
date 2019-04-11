package com.mirak.leetcode.individual.medium;

public class AndroidUnlockPatterns {

  public int numberOfPatterns(int m, int n) {
    return countPatterns(0, -1, m, n, 0);
  }

  private int countPatterns(int mask, int last, int m, int n, int selected) {
    if (selected == n) {
      return 1;
    }
    int ans = selected >= m ? 1 : 0;
    for (int i = 0; i < 9; ++i) {
      if ((mask & (1 << i)) != 0) {
        continue;
      }
      if (last == -1) {
        ans += countPatterns(mask | (1 << i), i, m, n, selected + 1);
        continue;
      }
      int lastI = last / 3, lastJ = last % 3;
      int nextI = i / 3, nextJ = i % 3;
      if ((sameRowWithStep2(lastI, lastJ, nextI, nextJ) || sameColumnWithStep2(lastI, lastJ, nextI,
          nextJ) || sameDig1WithStep2(lastI, lastJ, nextI, nextJ) || sameDig2WithStep2(lastI, lastJ,
          nextI, nextJ)) && !middleSelected(mask, i, last)) {
        continue;
      }
      ans += countPatterns(mask | (1 << i), i, m, n, selected + 1);
    }
    return ans;
  }

  private boolean sameRowWithStep2(int i1, int j1, int i2, int j2) {
    return i1 == i2 && Math.abs(j1 - j2) == 2;
  }

  private boolean sameColumnWithStep2(int i1, int j1, int i2, int j2) {
    return j1 == j2 && Math.abs(i1 - i2) == 2;
  }

  private boolean sameDig1WithStep2(int i1, int j1, int i2, int j2) {
    return i1 == j1 && i2 == j2 && Math.abs(i1 - i2) == 2;
  }

  private boolean sameDig2WithStep2(int i1, int j1, int i2, int j2) {
    return i1 + j1 == i2 + j2 && i1 + j1 == 2 && Math.abs(i1 - i2) == 2;
  }

  private boolean middleSelected(int mask, int i, int last) {
    return (mask & (1 << (i + last) / 2)) != 0;
  }
}
