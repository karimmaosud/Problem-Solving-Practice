package com.mirak.leetcode.individual.hard;

public class StudentAttendanceRecordII {

  private final int mod = 1000000007;

  public int checkRecord(int n) {
    int[] f = new int[2];
    int[] pl = new int[2];
    int[] l = new int[3];
    int[] pl_l = new int[2];
    int[] pl_ll = new int[2];
    int[] pl_p = new int[2];

    init(f, pl, l, pl_l, pl_ll, pl_p);

    if (n <= 2) {
      return f[n - 1];
    }

    for (int i = 2; i < n; i++) {
      f[i % 2] =
          (((2 * f[(i + 1) % 2]) % mod + pl[(i + 1) % 2]) % mod - l[(i + 1) % 3] + mod) % mod;

      pl_l[i % 2] = pl_p[(i + 1) % 2];
      pl_ll[i % 2] = pl_l[(i + 1) % 2];
      pl_p[i % 2] = ((pl_l[(i + 1) % 2] + pl_ll[(i + 1) % 2]) % mod + pl_p[(i + 1) % 2]) % mod;
      pl[i % 2] = ((pl_l[i % 2] + pl_ll[i % 2]) % mod + pl_p[i % 2]) % mod;

      l[i % 3] = (f[(i + 1) % 2] - (l[(i + 2) % 3] + l[(i + 1) % 3]) % mod + mod) % mod;
    }
    return f[(n - 1) % 2];
  }

  private void init(int[] f, int[] pl, int[] l, int[] pl_l, int[] pl_ll, int[] pl_p) {
    f[0] = 3;
    f[1] = 8;

    pl[0] = 2;
    pl[1] = 4;

    l[0] = 1;
    l[1] = 2;

    pl_l[1] = 1;
    pl_ll[1] = 1;
    pl_p[1] = 2;
  }
}

