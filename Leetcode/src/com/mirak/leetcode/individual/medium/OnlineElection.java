package com.mirak.leetcode.individual.medium;

import java.util.*;

public class OnlineElection {

  class TopVotedCandidate {

    private int[] res;
    private int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
      res = new int[times.length];
      this.times = times;
      int[] votes = new int[persons.length + 2];
      int topCandidate = -1;
      for (int i = 0; i < persons.length; i++) {
        votes[persons[i]]++;
        if (topCandidate == -1 || votes[persons[i]] >= votes[topCandidate]) {
          topCandidate = persons[i];
        }
        res[i] = topCandidate;
      }
    }

    public int q(int t) {
      int low = 0;
      int high = times.length - 1;
      while (low <= high) {
        int mid = low + (high - low) / 2;
        if (times[mid] <= t) {
          low = mid + 1;
        } else {
          high = mid - 1;
        }
      }
      return res[high];
    }
  }

}
