package com.mirak.leetcode.individual.hard;

public class KEmptySlots_BIT {
  public int kEmptySlots(int[] flowers, int k) {

    int[] tree = new int[flowers.length + 3];

    for (int i = 0; i < flowers.length; i++) {
      update(flowers[i], 1, tree);
    }

    for (int i = 0; i < flowers.length; i++) {

      update(flowers[i], -1, tree);

      if (flowers[i] + k + 1 <= flowers.length && readSingle(flowers[i] + k + 1, tree) == 0) {
        int sum = read(flowers[i] + k + 1, tree) - read(flowers[i], tree);
        if (sum == k) {
          return i + 1;
        }
      }

      if (flowers[i] - k - 1 > 0 && readSingle(flowers[i] - k - 1, tree) == 0) {
        if (read(flowers[i], tree) - read(flowers[i] - k - 1, tree) == k) {
          return i + 1;
        }
      }
    }

    return -1;
  }

  private void update(int idx, int val, int[] tree) {
    while (idx < tree.length) {
      tree[idx] += val;
      idx += (idx & -idx);
    }
  }

  private int read(int idx, int[] tree) {
    int sum = 0;
    while (idx > 0) {
      sum += tree[idx];
      idx -= (idx & -idx);
    }
    return sum;
  }

  private int readSingle(int idx, int[] tree) {
    int sum = tree[idx];
    int z = idx - (idx & -idx);
    idx--;
    while (idx != z) {
      sum -= tree[idx];
      idx -= (idx & -idx);
    }
    return sum;
  }
}
