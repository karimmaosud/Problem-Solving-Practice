package com.mirak.leetcode.contests.contest85;

public class New21Game {

  public static double new21Game(int N, int K, int W) {
    if (N >= K + W - 1) {
      return 1.0;
    }
    double[] p = new double[K + W + 1];
    p[0] = 1.0;
    double[] sum = new double[K + W + 1];
    sum[0] = 1.0;
    for (int i = 1; i < K + W; i++) {
      // p[i] = sum from Math.max(p[i - W]
      double windowSum = 0.0;
      if (i <= K) {
        windowSum = sum[i - 1];
      } else {
        if (K > 0) {
          windowSum = sum[K - 1];
        }
      }
      if (i - W - 1 >= 0) {
        windowSum -= sum[i - W - 1];
      }
      p[i] = (1.0 / W) * windowSum;
      sum[i] = sum[i - 1] + p[i];
    }
    double res = 0.0;
    for (int i = K; i <= N; i++) {
      res += p[i];
    }
    return res;
  }


  public static void main(String[] args) {
    System.out.println(new21Game(21, 17, 10));
  }
}
