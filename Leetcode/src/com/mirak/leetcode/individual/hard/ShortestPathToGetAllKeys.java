package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestPathToGetAllKeys {

  public int shortestPathAllKeys(String[] grid) {
    int[][] charIndexes = new int[6][2];
    initCharIndexes(grid, charIndexes);
    List<ArrayList<Integer>> permutations = new ArrayList<>();
    int[] permutationArray = new int[6];
    generatePermutations(0, 0, 6, permutationArray, permutations);

    int startRow = -1, startColumn = -1;
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length(); j++) {
        if (grid[i].charAt(j) == '@') {
          startRow = i;
          startColumn = j;
          break;
        }
      }
    }

    int min = Integer.MAX_VALUE;
    for (ArrayList<Integer> permutation : permutations) {

      int sourceRow = startRow;
      int sourceColumn = startColumn;

      int totalSteps = 0;
      // If you have reached to some character, mark it as unlocked (set its corresponding bit to 1) to be able to walk into its uppercase letter.
      int unlocked = 0;

      for (int i = 0; i < permutation.size(); i++) {
        // key is a number from 0 to 5 --> a to f.
        int key = permutation.get(i);
        if (charIndexes[key][0] == -1) {
          // Initially charIndexes is set to -1, so if a key wasn't present in the input grid, we can't walk to it.
          continue;
        }

        int dist = bfs(sourceRow, sourceColumn, charIndexes[key][0], charIndexes[key][1], grid,
            unlocked);
        if (dist == -1) {
          // key is present in the input grid, but couldn't reach to it from (sourceRow,sourceColumn).
          totalSteps = Integer.MAX_VALUE;
          break;
        }
        // Set (sourceRow,sourceColumn) to the key indexes.
        sourceRow = charIndexes[key][0];
        sourceColumn = charIndexes[key][1];

        totalSteps += dist;
        // mark the key as unlocked.
        unlocked |= (1 << key);
      }
      min = Math.min(min, totalSteps);
    }
    return min == Integer.MAX_VALUE ? -1 : min;
  }

  private int bfs(int startRow, int startColumn, int endRow, int endColumn, String[] grid,
      int unlocked) {

    int n = grid.length;
    int m = grid[0].length();

    boolean[][] vis = new boolean[n][m];
    Queue<Integer> queue = new LinkedList<>();

    vis[startRow][startColumn] = true;
    // First 10 bits are reserved for row, remaining bits are for column.
    int start = startRow | (startColumn << 10);
    queue.add(start);

    int level = 0;
    int[] rowInc = {1, -1, 0, 0};
    int[] colInc = {0, 0, 1, -1};

    while (!queue.isEmpty()) {
      // Level by level traversal to avoid creating an object that holds cell and distance.
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int num = queue.poll();

        int currentRow = num & ((1 << 10) - 1);
        int currentColumn = num >> 10;

        if (currentRow == endRow && currentColumn == endColumn) {
          // Reached to the destination
          return level;
        }

        for (int k = 0; k < 4; k++) {
          int nextRow = currentRow + rowInc[k];
          int nextColumn = currentColumn + colInc[k];
          if (nextRow < 0 || nextRow == n
              || nextColumn < 0 || nextColumn == m || vis[nextRow][nextColumn]
              || grid[nextRow].charAt(nextColumn) == '#') {
            continue;
          }
          char nextChar = grid[nextRow].charAt(nextColumn);
          if (Character.isAlphabetic(nextChar) && Character.isUpperCase(nextChar)) {
            if ((unlocked & (1 << (nextChar - 'A'))) == 0) {
              continue;
            }
          }
          vis[nextRow][nextColumn] = true;
          queue.add(nextRow | (nextColumn << 10));
        }
      }
      level++;
    }
    return -1;
  }

  private void initCharIndexes(String[] grid, int[][] charIndexes) {
    for (int i = 0; i < charIndexes.length; i++) {
      for (int j = 0; j < charIndexes[i].length; j++) {
        charIndexes[i][j] = -1;
      }
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[i].length(); j++) {
        char c = grid[i].charAt(j);
        if (Character.isAlphabetic(c) && Character.isLowerCase(c)) {
          charIndexes[c - 'a'][0] = i;
          charIndexes[c - 'a'][1] = j;
        }
      }
    }
  }

  private void generatePermutations(int index, int mask, int n, int[] permutationArray,
      List<ArrayList<Integer>> permutations) {
    if (mask == ((1 << n) - 1)) {
      ArrayList<Integer> permutationList = new ArrayList<>();
      for (int i = 0; i < permutationArray.length; i++) {
        permutationList.add(permutationArray[i]);
      }
      permutations.add(permutationList);
      return;
    }
    for (int i = 0; i < n; i++) {
      if ((mask & (1 << i)) == 0) {
        permutationArray[index] = i;
        generatePermutations(index + 1, mask | (1 << i), n, permutationArray, permutations);
      }
    }
  }
}
