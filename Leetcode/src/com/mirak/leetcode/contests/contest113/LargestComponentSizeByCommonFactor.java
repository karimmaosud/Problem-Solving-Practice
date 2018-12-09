package com.mirak.leetcode.contests.contest113;

import java.util.*;

public class LargestComponentSizeByCommonFactor {

  private int MAXN = 1000010;

  public int largestComponentSize(int[] A) {
    ArrayList<Integer> primes = generatePrimes();

    Map<Integer, Integer> primeIndex = indexPrimes(primes);

    int[] p = new int[primes.size()];

    int[] count = new int[p.length];

    for (int i = 0; i < p.length; i++) {
      p[i] = i;
    }

    for (int num : A) {
      ArrayList<Integer> primeFactors = getPrimeFactors(num, primes);

      if (primeFactors == null) {
        continue;
      }

      for (int i = 0; i < primeFactors.size(); i++) {
        unionSet(primeIndex.get(primeFactors.get(0)), primeIndex.get(primeFactors.get(i)), p,
            count);
      }

      count[findParent(primeIndex.get(primeFactors.get(0)), p)]++;
    }

    int max = 1;

    for (int i = 0; i < count.length; i++) {
      max = Math.max(max, count[i]);
    }

    return max;
  }

  private Map<Integer, Integer> indexPrimes(ArrayList<Integer> primes) {
    Map<Integer, Integer> primeIndex = new HashMap<>();
    for (int i = 0; i < primes.size(); i++) {
      primeIndex.put(primes.get(i), i);
    }
    return primeIndex;
  }

  private ArrayList<Integer> generatePrimes() {
    boolean[] flag = new boolean[MAXN];
    Arrays.fill(flag, true);
    flag[0] = flag[1] = false;
    for (int i = 2; i * i <= MAXN; i++) {
      if (flag[i]) {
        for (int j = i * i; j < MAXN; j += i) {
          flag[j] = false;
        }
      }
    }

    ArrayList<Integer> primes = new ArrayList<>();
    for (int i = 2; i < MAXN; i++) {
      if (flag[i]) {
        primes.add(i);
      }
    }

    return primes;
  }

  private ArrayList<Integer> getPrimeFactors(int num, ArrayList<Integer> primes) {

    if (num == 1) {
      return null;
    }

    ArrayList<Integer> primeFactors = new ArrayList<>();

    for (int p : primes) {

      if (p * p > num) {
        break;
      }

      if (num % p == 0) {
        primeFactors.add(p);
        while (num % p == 0) {
          num /= p;
        }
      }
    }

    if (num != 1) {
      primeFactors.add(num);
    }
    return primeFactors;
  }

  private int findParent(int i, int[] p) {
    if (p[i] == i) {
      return i;
    }
    p[i] = findParent(p[i], p);
    return p[i];
  }

  private void unionSet(int i, int j, int[] p, int[] count) {
    int pi = findParent(i, p);
    int pj = findParent(j, p);

    if (pi == pj) {
      return;
    }
    p[pi] = pj;
    count[pj] += count[pi];

  }
}
