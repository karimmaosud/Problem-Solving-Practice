package com.mirak.leetcode.individual.hard;

public class Candy2 {

  public int candy(int[] ratings) {
    int n = ratings.length;
    int[] candies = new int[n];
    int i = 0;
    while (i < n) {
      int j = i + 1;
      while (j < n && ratings[j] <= ratings[j - 1]) {
        j++;
      }
      j--;
      for (int k = j; k >= i; k--) {
        candies[k] = assignCandies(candies, ratings, k);
      }
      i = j + 1;
    }
    int res = 0;
    for (i = 0; i < n; i++) {
      System.out.print(candies[i] + " ");
      res += candies[i];
    }
    System.out.println();
    return res;
  }


  private int assignCandies(int[] candies, int[] ratings, int i) {
    return Math.max(1, Math.max(getMinFromNeighbour(i, i + 1, ratings, candies),
        getMinFromNeighbour(i, i - 1, ratings, candies)));
  }

  private int getMinFromNeighbour(int i, int j, int[] ratings, int[] candies) {
    if (j < 0 || j == ratings.length || ratings[j] >= ratings[i]) {
      return 1;
    }
    return candies[j] + 1;
  }

}
