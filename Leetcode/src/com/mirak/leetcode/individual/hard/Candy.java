package com.mirak.leetcode.individual.hard;

public class Candy {
  public int candy(int[] ratings) {
    if(ratings.length == 0) {
      return 0;
    }

    int res = 0;

    int[] candies = new int[ratings.length];
    int i = 0;

    while(i < ratings.length) {
      int j = i + 1;
      if(j < ratings.length && ratings[j] > ratings[i]) {
        while(j < ratings.length && ratings[j] > ratings[j - 1]) {
          j++;
        }
        j--;
        for(int k = i; k <= j; k++) {
          candies[k] = getAssignedCandies(ratings, k, candies);
        }
      }else {
        while( j < ratings.length && ratings[j] <= ratings[j - 1]) {
          j++;
        }
        j--;
        for(int k = j; k >= i; k--) {
          candies[k] = getAssignedCandies(ratings, k, candies);
        }
      }
      i = j + 1;
    }
    for(i = 0; i < ratings.length; i++) {
      System.out.print(candies[i] + " ");
      res += candies[i];
    }
    System.out.println();
    return res;
  }

  private int getAssignedCandies(int[] ratings, int i, int[] candies) {
    int res = 1;
    if(i - 1 >= 0 && ratings[i] > ratings[i - 1]) {
      res = candies[i - 1] + 1;
    }
    if(i + 1 < ratings.length && ratings[i] > ratings[i + 1]) {
      res = Math.max(res, candies[i + 1] + 1);
    }
    return res;
  }
}
