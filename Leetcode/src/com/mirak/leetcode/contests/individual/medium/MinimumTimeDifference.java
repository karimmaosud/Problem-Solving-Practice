package com.mirak.leetcode.contests.individual.medium;

import java.util.ArrayList;
import java.util.List;

public class MinimumTimeDifference {
  public static int findMinDifference(List<String> timePoints) {

    boolean[] times = new boolean[24 * 60 + 1];
    int diff = Integer.MAX_VALUE;
    for(String time: timePoints) {
      String[] strs = time.split(":");
      int minutes = Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
      if(times[minutes]) {
        diff = 0;
      }else{
        times[minutes] = true;
      }
    }

    if(diff == 0) {
      return diff;
    }

    ArrayList<Integer> timesList = new ArrayList<>();
    for(int i = 0; i < times.length; i++) {
      if(times[i]) {
        if(timesList.size() != 0) {
          int currentDiff = i - timesList.get(timesList.size() - 1);
          diff = Math.min(diff, Math.min(currentDiff, 24 * 60 - currentDiff));
        }
        timesList.add(i);
      }
    }

    return Math.min(diff, Math.min(timesList.get(timesList.size() - 1) - timesList.get(0), 24 * 60 - timesList.get(timesList.size() - 1) + timesList.get(0)));
  }
}
