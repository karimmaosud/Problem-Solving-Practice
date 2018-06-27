package com.mirak.codeforces.rounds.regular.round492;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemD {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    int[] nums = new int[2 * n];
    for(int i = 0; i < 2 * n; i++) {
      nums[i] = Integer.parseInt(strs[i]);
    }

    int left = 0, right = nums.length - 1;
    int answer = 0;

    while(left < right) {
      int rightSecondPosition = getSecondPosition(nums, nums[right], right, -1);
      int rightDist = right - rightSecondPosition - 1;
      if(rightDist == 0) {
        right -= 2;
        continue;
      }

      int leftSecondPosition = getSecondPosition(nums, nums[left], left, 1);
      int leftDist = leftSecondPosition - left - 1;
      if(leftDist == 0) {
        left += 2;
        continue;
      }
      if(rightDist < leftDist) {
        swapNums(nums, rightSecondPosition, right - 1, 1);
        right -= 2;
      }else {
        swapNums(nums, leftSecondPosition, left + 1, -1);
        left += 2;
      }
      answer += Math.min(rightDist, leftDist);
    }
    System.out.println(answer);
  }

  private static int getSecondPosition(int[] nums, int num, int idx, int inc) {
    for(int i = idx + inc; i < nums.length && i >= 0; i += inc) {
      if(nums[i] == num) {
        return i;
      }
    }
    return -1;
  }

  private static void swapNums(int[] nums, int startIndex, int endIndex, int inc) {
    while(startIndex != endIndex) {
      int temp = nums[startIndex];
      nums[startIndex] = nums[startIndex + inc];
      nums[startIndex + inc] = temp;
      startIndex += inc;
    }
  }
}
