package com.mirak.leetcode.contests.contest131;

import java.util.*;

public class VideoStitching {

  private final int INF = 1000000000;

  public int videoStitching(int[][] clips, int T) {
    return vidoeStitchingGreedy(clips, T);
  }

  private int videoStitchingDp(int[][] clips, int T) {
    ArrayList<int[]> list = new ArrayList<>();
    for (int[] clip : clips) {
      list.add(clip);
    }
    Collections
        .sort(list, (int[] a, int[] b) -> a[0] < b[0] ? -1 : a[0] > b[0] ? 1 : a[1] - b[1]);
    int[][] dp = new int[list.size()][101];
    for (int[] row : dp) {
      Arrays.fill(row, -1);
    }
    int ans = solve(0, 0, list, dp, T);
    return ans == INF ? -1 : ans;
  }

  private int solve(int idx, int end, ArrayList<int[]> list, int[][] dp, int T) {
    if (end >= T) {
      return 0;
    }
    if (idx == list.size() || list.get(idx)[0] > end) {
      return INF;
    }
    if (dp[idx][end] != -1) {
      return dp[idx][end];
    }
    return dp[idx][end] = Math.min(1 + solve(idx + 1, Math.max(end, list.get(idx)[1]), list, dp, T),
        solve(idx + 1, end, list, dp, T));
  }

  private int vidoeStitchingGreedy(int[][] clips, int T) {
    ArrayList<int[]> list = new ArrayList<>();
    for (int[] clip : clips) {
      list.add(clip);
    }
    Collections.sort(list, (int[] a, int[] b) -> a[0] - b[0] == 0 ? a[1] - b[1] : a[0] - b[0]);

    int end = 0;
    int ans = 0;
    for (int i = 0; i < list.size(); ++i) {
      if (list.get(i)[0] > end) {
        return -1;
      }

      int maxEnd = end;

      while (i < list.size() && list.get(i)[0] <= end) {
        maxEnd = Math.max(maxEnd, list.get(i)[1]);
        i++;
      }
      ans++;
      end = maxEnd;
      i--;
      if (maxEnd >= T) {
        return ans;
      }
    }
    return -1;
  }
}
