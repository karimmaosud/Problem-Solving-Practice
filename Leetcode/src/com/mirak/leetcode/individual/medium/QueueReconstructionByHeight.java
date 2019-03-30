package com.mirak.leetcode.individual.medium;

import java.util.*;

public class QueueReconstructionByHeight {

  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(people, (int[] a, int[] b) -> b[0] - a[0] == 0 ? a[1] - b[1] : b[0] - a[0]);
    for (int i = 0; i < people.length; ++i) {
      int[] person = people[i];
      int skipped = i - person[1];
      for (int j = 0; j < skipped; ++j) {
        swap(people, i - j, i - j - 1);
      }
    }
    return people;
  }

  private void swap(int[][] people, int i, int j) {
    int[] temp = people[i];
    people[i] = people[j];
    people[j] = temp;
  }
}
