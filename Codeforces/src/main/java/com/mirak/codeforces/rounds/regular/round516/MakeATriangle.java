package com.mirak.codeforces.rounds.regular.round516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MakeATriangle {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int[] nums = new int[3];
    nums[0] = Integer.parseInt(strs[0]);
    nums[1] = Integer.parseInt(strs[1]);
    nums[2] = Integer.parseInt(strs[2]);

    Arrays.sort(nums);
    if(nums[0] + nums[1] > nums[2]) {
      System.out.println(0);
      return;
    }
    System.out.println(nums[2] - (nums[0] + nums[1]) + 1);
  }
}
