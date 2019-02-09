package com.mirak.leetcode.individual.medium;

public class CircularArrayLoop {

  public boolean circularArrayLoop(int[] nums) {
    int n = nums.length;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        continue;
      }
      int p = i, j = i;
      boolean forwardLoop = nums[i] > 0;
      boolean start = true;
      boolean hasLoop = true;
      int k = 0;
      while (start || p != j) {
        start = false;
        if (nums[p] % n == 0 || nums[j] % n == 0 || invalidMove(forwardLoop, nums[p])
            || invalidMove(forwardLoop, nums[j])) {
          hasLoop = false;
          break;
        }
        int p_ = (p + nums[p] % n + n) % n;
        int j_ = (j + nums[j] % n + n) % n;
        if (nums[j_] % n == 0 || invalidMove(forwardLoop, nums[j])) {
          hasLoop = false;
          break;
        }
        k++;
        j_ = (j_ + nums[j_] % n + n) % n;
        p = p_;
        j = j_;
      }
      if (hasLoop) {
        return true;
      }
      p = j = i;
      for (int l = 0; l < k; l++) {
        int p_ = (p + nums[p] % n + n) % n;
        int j_ = (j + nums[j] % n + n) % n;
        j_ = (j_ + nums[j_] % n + n) % n;
        nums[p] = nums[j] = 0;
        p = p_;
        j = j_;
      }
    }
    return false;
  }

  private boolean invalidMove(boolean forwardLoop, int num) {
    return (num > 0) ^ forwardLoop;
  }
}
