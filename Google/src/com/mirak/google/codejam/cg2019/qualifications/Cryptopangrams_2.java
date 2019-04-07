package com.mirak.google.codejam.cg2019.qualifications;

import java.io.*;
import java.math.*;
import java.util.*;

public class Cryptopangrams_2 {

  public static void main(String[] args) throws IOException {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(reader.readLine());

    for (int t = 1; t <= T; ++t) {

      String[] strs = reader.readLine().split(" ");

      int l = Integer.parseInt(strs[1]);

      BigInteger[] values = new BigInteger[l];

      strs = reader.readLine().split(" ");

      for (int i = 0; i < l; ++i) {
        values[i] = new BigInteger(strs[i]);
      }

      BigInteger g = null;
      int idx = 0;
      for (; idx < l - 1; idx++) {
        BigInteger gcd = values[idx].gcd(values[idx + 1]);
        if (gcd.equals(values[idx])) {
          continue;
        }
        g = gcd;
        break;
      }

      if (g == null) {
        System.out.println("Case #" + t + ": " + getStringFromList(null, l + 1));
        continue;
      }

      Map<BigInteger, Character> primeToChar = new HashMap<>();
      initPrimes(values, idx, g, primeToChar);

      LinkedList<Character> list = new LinkedList<>();
      BigInteger prev = g;
      list.addFirst(primeToChar.get(prev));

      for (int i = idx; i >= 0; --i) {
        BigInteger next = values[i].divide(prev);
        list.addFirst(primeToChar.get(next));
        prev = next;
      }
      prev = g;
      for (int i = idx + 1; i < values.length; ++i) {
        BigInteger next = values[i].divide(prev);
        list.addLast(primeToChar.get(next));
        prev = next;
      }
      System.out.println("Case #" + t + ": " + getStringFromList(list, l + 1));
    }
  }

  private static void initPrimes(BigInteger[] values, int idx, BigInteger g,
      Map<BigInteger, Character> primeToChar) {

    BigInteger prev = g;
    Set<BigInteger> set = new TreeSet<>();
    set.add(g);

    for (int i = idx; i >= 0; --i) {
      BigInteger current = values[i].divide(prev);
      set.add(current);
      prev = current;
    }
    prev = g;
    for (int i = idx + 1; i < values.length; ++i) {
      BigInteger current = values[i].divide(prev);
      set.add(current);
      prev = current;
    }
    char a = 'A';
    for (BigInteger num : set) {
      primeToChar.put(num, a++);
    }
  }

  private static String getStringFromList(LinkedList<Character> list, int n) {
    StringBuilder builder = new StringBuilder();
    if (list == null) {
      for (int i = 0; i < n; i++) {
        builder.append('A');
      }
      return builder.toString();
    }
    while (!list.isEmpty()) {
      builder.append(list.removeFirst());
    }
    return builder.toString();
  }
}