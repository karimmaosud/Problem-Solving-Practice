package com.mirak.google.codejam.cg2019.qualifications;

import java.io.*;
import java.util.*;

public class Cryptopangrams {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    ArrayList<Integer> primes = generatePrimes();

    int T = Integer.parseInt(reader.readLine());

    for (int t = 1; t <= T; ++t) {

      String[] strs = reader.readLine().split(" ");

      String nStr = strs[0];
      int l = Integer.parseInt(strs[1]);
      if (nStr.length() >= 6) {
        System.out.println("Case #" + t + ": Nan");
        continue;
      }

      int[] values = new int[l];

      strs = reader.readLine().split(" ");

      for (int i = 0; i < l; ++i) {
        values[i] = Integer.parseInt(strs[i]);
      }

      Map<Integer, Character> primeToChar = new HashMap<>();
      Map<Integer, Integer> valueToLeastPrime = new HashMap<>();

      processValues(values, primes, primeToChar, valueToLeastPrime);

      String text = getStringWithStartP(valueToLeastPrime.get(values[0]), values, primeToChar);
      if (text == null) {
        text = getStringWithStartP(values[0] / valueToLeastPrime.get(values[0]), values,
            primeToChar);
      }

      System.out.println("Case #" + t + ": " + text);
    }
  }

  private static ArrayList<Integer> generatePrimes() {
    int N = 10010;
    boolean[] flag = new boolean[N];
    Arrays.fill(flag, true);
    flag[0] = flag[1] = false;
    for (int i = 2; i * i <= N; ++i) {
      if (flag[i]) {
        for (int j = i * i; j < N; j += i) {
          flag[j] = false;
        }
      }
    }
    ArrayList<Integer> primes = new ArrayList<>();
    for (int i = 0; i < N; ++i) {
      if (flag[i]) {
        primes.add(i);
      }
    }
    return primes;
  }

  private static void processValues(int[] values, ArrayList<Integer> primes,
      Map<Integer, Character> primeToChar, Map<Integer, Integer> valueLeastPrime) {

    Set<Integer> ps = new TreeSet<>();

    for (int value : values) {
      int pi = getLeastPrime(value, primes);
      valueLeastPrime.putIfAbsent(value, pi);
      ps.add(pi);
      ps.add(value / pi);
    }

    char c = 'A';
    for (int p : ps) {
      primeToChar.put(p, c++);
    }

  }

  private static int getLeastPrime(int value, ArrayList<Integer> primes) {
    for (int prime : primes) {
      if (value % prime == 0) {
        return prime;
      }
    }
    // should never happen.
    return -1;
  }

  private static String getStringWithStartP(int prev, int[] values,
      Map<Integer, Character> primeToChar) {
    StringBuilder builder = new StringBuilder();
    builder.append(primeToChar.get(prev));
    for (int value : values) {
      int pi = value / prev;
      if (!primeToChar.containsKey(pi)) {
        return null;
      }
      builder.append(primeToChar.get(pi));
      prev = pi;
    }
    return builder.toString();
  }
}
