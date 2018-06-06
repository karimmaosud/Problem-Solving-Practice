package com.mirak.leetcode.individual.medium;

public class PowXN {
  public double myPow(double x, int n) {
    boolean negPower = false;
    long p = (long) n;
    if(n < 0) {
      negPower = true;
      p *= -1;
    }

    double res = 1.0;
    while(p > 0) {
      if((p & 1) == 1) {
        res = res * x;
      }
      x *= x;
      p >>= 1;
    }
    if (negPower) {
      return 1.0 / res;
    }
    return res;
  }
}


