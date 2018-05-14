package com.mirak.codeforces.rounds.regular.round476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemCPractice {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    long n = Long.parseLong(strs[0]);
    long k = Long.parseLong(strs[1]);
    long m = Long.parseLong(strs[2]);
    int D = Integer.parseInt(strs[3]);
    long max = -1;
    for(int d = 1; d <= Math.min(D, n); d++) {
      long maxX = getMaxX(d, n, m, k);
      max = Math.max(max, maxX * d);
    }
    System.out.println(max);
  }

  private static long getMaxX(int d, long n, long m, long k) {
    long low = 1;
    long high = m;
    long ret = -1;
    while(low <= high) {
      long mid = (low + high) / 2;
      double y = isValid(mid, d, n, k);
      if(y <= 0.0){
        high = mid - 1;
      }else if (y > (1.0 * k)){
        low = mid + 1;
      }else{
        ret = Math.max(ret, mid);
        low = mid + 1;
      }
    }
    return ret;
  }
  private static double isValid(long x, int d, long n, long k) {
    return (1.0 * n) / (1.0 * x) + k - d * k;

  }
}
