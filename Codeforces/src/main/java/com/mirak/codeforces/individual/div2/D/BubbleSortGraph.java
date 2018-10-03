package com.mirak.codeforces.individual.div2.D;

import java.io.*;
import java.util.*;

public class BubbleSortGraph {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(strs[i]);
    }
    int[] d = new int[n + 2];
    Arrays.fill(d, Integer.MAX_VALUE);
    d[0] = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      int j = binarySearch(d, nums[i]);
      if (nums[i] > d[j - 1] && nums[i] < d[j]) {
        d[j] = nums[i];
      }
    }
    int i = 0;
    for (i = 1; i <= n + 1; i++) {
      if (d[i] == Integer.MAX_VALUE) {
        i--;
        break;
      }
    }
    System.out.println(i);
  }

  private static int binarySearch(int[] d, int key) {
    int low = 0;
    int high = d.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (key > d[mid]) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }
}
