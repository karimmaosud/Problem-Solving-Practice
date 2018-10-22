package com.mirak.codeforces.rounds.regular.round517;

import java.io.*;
import java.util.*;

public class CramTime {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    String[] strs = reader.readLine().split(" ");
    Long a = Math.min(Long.parseLong(strs[0]), Long.parseLong(strs[1]));
    Long b = Math.max(Long.parseLong(strs[0]), Long.parseLong(strs[1]));

    long n = a + b;

    long x = (long) ((Math.sqrt(1.0 + 8.0 * n) - 1.0) / 2.0);

    if (x <= 0) {
      System.out.println(0 + "\n" + 0);
      return;
    }

    Set<Long> set = new HashSet<>();
    if (a <= x) {
      if (a != 0) {
        set.add(a);
      }
      print(x, set, Long.parseLong(strs[0]), Long.parseLong(strs[1]));
      return;
    }

    long sum = 0;
    for (long j = x; j >= 1; j--) {
      if (sum + j > a) {
        break;
      }
      sum += j;
      set.add(j);
    }

    if (a - sum > 0) {
      set.add(a - sum);
    }

    print(x, set, Long.parseLong(strs[0]), Long.parseLong(strs[1]));

  }

  private static void print(long x, Set<Long> set, long first, long second) {
    if (first < second) {
      printSet(x, set, false);
      printSet(x, set, true);
    } else {
      printSet(x, set, true);
      printSet(x, set, false);
    }
  }

  private static void printSet(long x, Set<Long> nums, boolean complement) {
    if (complement) {
      System.out.println(x - nums.size());
      if (x - nums.size() == 0) {
        return;
      }
    } else {
      System.out.println(nums.size());
      if (nums.size() == 0) {
        return;
      }
    }

    for (long i = 1; i <= x; i++) {
      if (complement && !nums.contains(i)) {
        System.out.print(i + " ");
      } else if (!complement && nums.contains(i)) {
        System.out.print(i + " ");
      }
    }
    System.out.println();
  }
}
