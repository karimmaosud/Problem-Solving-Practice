package com.mirak.leetcode.contests.contest112;

import java.util.*;

public class BagOfTokens {

  public int bagOfTokensScore(int[] tokens, int P) {

    Arrays.sort(tokens);
    int l = 0, r = tokens.length - 1;
    int maxPoints = 0;
    int points = 0;
    while (l <= r) {
      if (tokens[l] <= P) {
        points++;
        P -= tokens[l++];
      } else if(points > 0){
        points--;
        P += tokens[r--];
      } else {
        break;
      }
      maxPoints = Math.max(maxPoints, points);
    }
    return maxPoints;
  }
}
