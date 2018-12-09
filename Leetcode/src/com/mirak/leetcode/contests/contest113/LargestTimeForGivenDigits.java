package com.mirak.leetcode.contests.contest113;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LargestTimeForGivenDigits {

  public String largestTimeFromDigits(int[] A) {

    int[] count = new int[10];

    for (int digit : A) {
      count[digit]++;
    }

    int maxTime = -1;

    for (int i = 0; i <= 9999; i++) {
      ArrayList<Integer> mappedArray = getMappedArray(i);
      int[] tempCount = new int[10];
      for (int digit : mappedArray) {
        tempCount[digit]++;
      }

      if (Arrays.equals(tempCount, count)) {
        int hour = mappedArray.get(0) * 10 + mappedArray.get(1);
        int minute = mappedArray.get(2) * 10 + mappedArray.get(3);
        if (hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59) {
          maxTime = Math.max(maxTime, hour * 60 + minute);
        }
      }
    }
    if (maxTime == -1) {
      return "";
    }
    return getValidString(maxTime / 60) + ":" + getValidString(maxTime % 60);
  }

  private String getValidString(int num) {
    if (num == 0) {
      return "00";
    }

    return num < 10 ? "0" + num : "" + num;
  }

  private ArrayList<Integer> getMappedArray(int num) {
    ArrayList<Integer> mappedArray = new ArrayList<>();
    while (num != 0) {
      mappedArray.add(num % 10);
      num /= 10;
    }
    while (mappedArray.size() < 4) {
      mappedArray.add(0);
    }
    Collections.reverse(mappedArray);
    return mappedArray;
  }
}
