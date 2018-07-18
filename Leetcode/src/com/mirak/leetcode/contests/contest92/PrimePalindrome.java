package com.mirak.leetcode.contests.contest92;

import java.util.ArrayList;
import java.util.Collections;

public class PrimePalindrome {

  public static int primePalindrome(int N) {
    ArrayList<Long> palindromeNums = prePrecess();
    Collections.sort(palindromeNums);
    int lowerBoundIndex = getLowerBound(N, palindromeNums);
    for (int i = lowerBoundIndex; i < palindromeNums.size(); i++) {
      if (isPrime(palindromeNums.get(i))) {
        long ret = palindromeNums.get(i);
        return (int) ret;
      }
    }
    return -1;
  }

  private static boolean isPrime(long num) {
    if (num == 1) {
      return false;
    }
    for (int i = 2; i * i <= num; i++) {
      if (num % i == 0) {
        return false;
      }
    }
    return true;
  }

  private static ArrayList<Long> prePrecess() {
    ArrayList<Long> palindromeNums = new ArrayList<>();
    for (int i = 1; i <= 20000; i++) {
      palindromeNums.add(getOddPalindrome(i));
      palindromeNums.add(getEvenPalindrome(i));
    }
    return palindromeNums;
  }

  private static long getOddPalindrome(int num) {
    StringBuilder builder = new StringBuilder("" + num);
    StringBuilder prefix = new StringBuilder(builder.substring(0, builder.length() - 1));
    return Long.parseLong(builder.toString() + prefix.reverse().toString());
  }

  private static long getEvenPalindrome(int num) {
    StringBuilder builder = new StringBuilder("" + num);
    return Long.parseLong(builder.toString() + builder.reverse().toString());
  }


  private static int getLowerBound(int key, ArrayList<Long> palindromeNums) {
    int low = 0;
    int high = palindromeNums.size() - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (palindromeNums.get(mid) == key) {
        return mid;
      }
      if (palindromeNums.get(mid) > key) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }

  public static void main(String[] args) {
    primePalindrome(1000000);
  }
}
