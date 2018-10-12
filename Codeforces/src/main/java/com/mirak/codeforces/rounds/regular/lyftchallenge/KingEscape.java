package com.mirak.codeforces.rounds.regular.lyftchallenge;

import java.io.*;
import java.util.*;

public class KingEscape {

  private static int SHIFT = 10;

  private static int[] rowInc = {1, 1, 1, -1, -1, -1, 0, 0};
  private static int[] colInc = {0, 1, -1, 0, 1, -1, 1, -1};


  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    int ax = Integer.parseInt(strs[0]);
    int ay = Integer.parseInt(strs[1]);

    strs = reader.readLine().split(" ");

    int bx = Integer.parseInt(strs[0]);
    int by = Integer.parseInt(strs[1]);

    strs = reader.readLine().split(" ");

    int cx = Integer.parseInt(strs[0]);
    int cy = Integer.parseInt(strs[1]);

    boolean[][] vis = new boolean[n + 1][n + 1];

    Queue<Integer> queue = new LinkedList<>();
    queue.add(makeNumber(bx, by));

    while (!queue.isEmpty()) {
      int cell = queue.poll();
      int x = getX(cell);
      int y = getY(cell);

      if (x == cx && y == cy) {
        System.out.println("YES");
        return;
      }
      for (int i = 0; i < rowInc.length; i++) {
        int xP = x + rowInc[i];
        int yP = y + colInc[i];
        if (xP > n || xP == 0 || yP > n || yP == 0 || vis[xP][yP] || !validCell(xP, yP, ax, ay)) {
          continue;
        }
        vis[xP][yP] = true;
        queue.add(makeNumber(xP, yP));
      }
    }
    System.out.println("NO");
  }

  private static int makeNumber(int x, int y) {
    return x | (y << SHIFT);
  }

  private static int getX(int cell) {
    return cell & ((1 << SHIFT) - 1);
  }

  private static int getY(int cell) {
    return cell >> SHIFT;
  }

  private static boolean validCell(int kingX, int kingY, int queenX, int queenY) {
    if (kingX == queenX || kingY == queenY || kingX + kingY == queenX + queenY
        || kingX - kingY == queenX - queenY) {
      return false;
    }
    return true;
  }
}
