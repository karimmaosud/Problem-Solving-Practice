package com.mirak.codeforces.rounds.regular.round517;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CuriosityHasNoLimits {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    int[] a = new int[n - 1];
    int[] b = new int[n - 1];
    String[] aStrs = reader.readLine().split(" ");
    String[] bStrs = reader.readLine().split(" ");
    for (int i = 0; i < n - 1; i++) {
      a[i] = Integer.parseInt(aStrs[i]);
      b[i] = Integer.parseInt(bStrs[i]);
    }

    int[][] dp = new int[n + 10][10];
    int[][] path = new int[n + 10][10];

    for (int i = 0; i < dp.length; i++) {
      Arrays.fill(dp[i], -1);
    }

    String printStr = "NO";
    int ti;
    for (ti = 0; ti < 4; ti++) {
      for (int tj = 0; tj < 4; tj++) {

        if ((ti | tj) == a[0] && (ti & tj) == b[0]) {
          if(solve(1, tj, a, b, dp, path) == 1) {
            path[0][ti] = tj;
            printStr = "YES";
            break;
          }
        }
      }
      if (printStr.equals("YES")) {
        break;
      }
    }
    System.out.println(printStr);
    if (printStr.equals("NO")) {
      return;
    }
    System.out.print(ti + " " + path[0][ti]);
    // print path.
    printPath(1, path[0][ti], path, a.length);
  }

  private static int solve (int index, int prev, int[] a, int[] b, int[][] dp, int[][] path) {
    if (index == a.length) {
      return 1;
    }

    if (dp[index][prev] != -1) {
      return dp[index][prev];
    }

    int ans = 0;

    for (int i = 0; i <= 3; i++) {
      if((prev | i) == a[index] && (prev & i) == b[index]) {
        int canGo = solve(index + 1, i, a, b, dp, path);
        if (canGo == 1) {
          path[index][prev] = i;
        }
        ans |= canGo;
      }
    }
    return dp[index][prev] = ans;
  }


  private static void printPath(int index, int prev, int[][] path, int n) {
    if (index == n) {
      System.out.println();
      return;
    }
    System.out.print(" " + path[index][prev]);
    printPath(index + 1, path[index][prev], path, n);
  }


}
