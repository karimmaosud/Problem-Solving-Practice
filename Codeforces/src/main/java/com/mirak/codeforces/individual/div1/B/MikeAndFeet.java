package com.mirak.codeforces.individual.div1.B;

import java.io.*;
import java.util.*;

public class MikeAndFeet {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");

    int[] nums = new int[n];
    Set<Integer> numsSet = new TreeSet<>();

    for (int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(strs[i]);
      numsSet.add(nums[i]);
    }

    ArrayList<Integer> sortedNums = new ArrayList<>(numsSet);
    Map<Integer, Integer> map = new HashMap<>();

    int[] leftLower = new int[n];
    int[] rightLower = new int[n];
    processNextLower(rightLower, nums, true);
    processNextLower(leftLower, nums, false);

    for (int i = 0; i < n; i++) {
      map.put(nums[i], Math.max(map.getOrDefault(nums[i], 0), rightLower[i] - leftLower[i] - 1));
    }

    int[] res = new int[n + 1];
    int resIndex = 1;
    int runnerIndex = sortedNums.size() - 1;

    while (resIndex <= n) {
      int upperBound = map.get(sortedNums.get(runnerIndex));
      if (upperBound >= resIndex) {
        while (upperBound >= resIndex) {
          res[resIndex] = sortedNums.get(runnerIndex);
          resIndex++;
        }
      } else {
        runnerIndex--;
      }
    }

    StringBuilder builder = new StringBuilder();
    for (int i = 1; i <= n; i++) {
      builder.append(res[i]).append(" ");
    }
    System.out.println(builder);
  }

  private static void processNextLower(int[] nextLower, int[] nums, boolean leftToRight) {
    int start = leftToRight ? 0 : nums.length - 1;
    int end = leftToRight ? nums.length : -1;
    int inc = leftToRight ? 1 : -1;
    Stack<Integer> stack = new Stack<>();
    while (start != end) {
      if (stack.isEmpty() || nums[start] > nums[stack.peek()]) {
        stack.add(start);
      } else {
        while (!stack.isEmpty() && nums[start] < nums[stack.peek()]) {
          nextLower[stack.pop()] = start;
        }
        stack.add(start);
      }
      start += inc;
    }
    while (!stack.isEmpty()) {
      nextLower[stack.pop()] = leftToRight ? nums.length : -1;
    }
  }
}
