package com.mirak.leetcode.contests.contest86;

import java.util.HashSet;
import java.util.Set;

public class MagicSquaresInGrid {
  public int numMagicSquaresInside(int[][] grid) {
    int res = 0;
    for(int i = 0; i < grid.length; i++) {
      for(int j = 0; j < grid[i].length; j++) {
        if(i + 3 > grid.length || j + 3 > grid[i].length) {
          break;
        }

        int[] rowSum = new int[3];
        int[] columnSum = new int[3];
        int[] digSum = new int[2];
        boolean validSquare = true;
        for(int k = 0; k < 3; k++) {
          for(int l = 0; l < 3; l++) {
            if(grid[i + k][j + l] > 9 || grid[i + k][j + l] < 1) {
              validSquare = false;
              break;
            }
            rowSum[k] += grid[i + k][j + l];
            columnSum[l] += grid[i + k][j + l];
          }
          if(!validSquare) {
            break;
          }
        }

        if(!validSquare) {
          continue;
        }

        for(int k = 0; k < 3; k++) {
          digSum[0] += grid[i + k][j + k];
          digSum[1] += grid[i + 2 - k][j + k];
        }

        if(isMagicSquare(rowSum, columnSum, digSum)) {
          res++;
        }
      }
    }
    return res;
  }

  private boolean isMagicSquare(int[] rowSum, int[] columnSum, int[] digSum) {
    Set<Integer> set = new HashSet<>();
    for(int i = 0; i < rowSum.length; i++) {
      set.add(rowSum[i]);
      set.add(columnSum[i]);
      if(i < 2) {
        set.add(digSum[i]);
      }
      if(set.size() > 1) {
        return false;
      }
    }
    return set.size() == 1;
  }

}
