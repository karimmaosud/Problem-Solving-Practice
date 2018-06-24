package com.mirak.codeforces.rounds.regular.round491;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Use StringBuilder for large output.
 */
public class ProblemC {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    long n = Long.parseLong(reader.readLine());
    long low = 1;
    long high = n;
    long lastValid = n;
    long bound = (n & 1) == 0? n / 2: (n / 2) + 1;
    while(low <= high) {
      long mid = low + (high - low) / 2;
      if(validK(n, mid, bound)) {
        high = mid - 1;
        lastValid = Math.min(lastValid, mid);
      }else {
        low = mid + 1;
      }
    }
    System.out.println(lastValid);
  }

  private static boolean validK(long n, long k, long bound) {
    long eaten = 0;
    while(n > 0) {
      if(n <= k) {
        eaten += n;
        n = 0;
        continue;
      }
      eaten += k;
      n -= k;
      long taken = n / 10;
      n -= taken;
    }
    return eaten >= bound;
  }
}