package com.mirak.codeforces.rounds.regular.round492;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemB {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    int[] nums = new int[n];
    int entrance = 1;
    int minEnter = Integer.MAX_VALUE;

    for(int i = 0; i < n; i++) {
      nums[i] = Integer.parseInt(strs[i]);

      int enteringTime = i;
      int rem = nums[i] - i;
      if(rem > 0) {
        enteringTime += (rem / n) * n;
        if(rem % n != 0) {
          enteringTime += n;
        }
      }

      if(enteringTime < minEnter) {
        minEnter = enteringTime;
        entrance = i + 1;
      }
    }
    System.out.println(entrance);
  }
}
