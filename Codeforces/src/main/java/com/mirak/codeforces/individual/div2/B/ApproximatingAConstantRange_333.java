package com.mirak.codeforces.individual.div2.B;

import java.io.*;
import java.util.*;

public class ApproximatingAConstantRange_333 {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] numStrs = reader.readLine().split(" ");
    int[] nums = new int[n];
    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(numStrs[i]);
    }
    int ans = 0;
    int left = 0, right = 0;
    int min = nums[left], max = nums[left];

    Map<Integer, Integer> countMap = new HashMap<>();
    countMap.put(nums[left], 1);

    while (right < n) {
      if (!validRange(min, max)) {
        countMap.put(nums[left], countMap.get(nums[left]) - 1);
        left++;
        if (countMap.get(min) == 0) {
          min = Integer.MAX_VALUE;
        }
        if (countMap.get(max) == 0) {
          max = Integer.MIN_VALUE;
        }

        min = Math.min(min, nums[left]);
        max = Math.max(max, nums[left]);

        if (countMap.getOrDefault(nums[left] - 1, 0) != 0) {
          min = Math.min(min, nums[left] - 1);
        }
        if (countMap.getOrDefault(nums[left] + 1, 0) != 0) {
          max = Math.max(max, nums[left] + 1);
        }
      } else {
        ans = Math.max(ans, right - left + 1);
        right++;
        if (right < n) {
          countMap.put(nums[right], countMap.getOrDefault(nums[right], 0) + 1);
          min = Math.min(min, nums[right]);
          max = Math.max(max, nums[right]);
        }
      }
    }
    System.out.println(ans);
  }

  private static boolean validRange(int min, int max) {
    return max - min <= 1;
  }

}
