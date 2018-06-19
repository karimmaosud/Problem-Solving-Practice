package com.mirak.careercup.palantir;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DecodeGrid {

  private class Pair {
    char c;
    int count;

    Pair(char c, int count) {
      this.c = c;
      this.count = count;
    }
  }

  public List<String> decodeGrid(String grid) {
    ArrayList<Pair> list = new ArrayList<>();
    Set<Character> validChars = new HashSet<>();
    validChars.add('_');
    validChars.add('r');
    validChars.add('b');

    int rows = decodeGridToChars(grid, list, validChars);

    int n = findLeastDivisor(rows);
    List<String> decodedGrid = new ArrayList<>();
    StringBuilder builder = new StringBuilder();
    int takenColumns = 0;
    for (int i = 0; i < list.size(); i++) {
      Pair pair = list.get(i);
      for (int j = 0; j < pair.count; j++) {
        builder.append("|").append(pair.c);
        takenColumns++;
        if (takenColumns == n) {
          pair.count -= (j + 1);
          takenColumns = 0;
          decodedGrid.add(builder.append("|").toString());
          builder = new StringBuilder();
          i--;
          break;
        }
      }
    }
    return decodedGrid;
  }

  private int findLeastDivisor(int len) {
    for (int i = 2; i * i <= len; i++) {
      if (len % i == 0) {
        return i;
      }
    }
    return 1;
  }

  private int decodeGridToChars(String grid, ArrayList<Pair> list, Set<Character> validChars) {
    int len = 0;
    int idx = 0;
    while (idx < grid.length()) {

      if (Character.isDigit(grid.charAt(idx))) {
        int j = idx + 1;
        while (j < grid.length() && Character.isDigit(grid.charAt(j))) {
          j++;
        }

        if (j == grid.length() || !validChars.contains(grid.charAt(j))) {
          return -1;
        }
        len += Integer.parseInt(grid.substring(idx, j));
        list.add(new Pair(grid.charAt(j), Integer.parseInt(grid.substring(idx, j))));
        idx = j + 1;
      } else {
        if (!validChars.contains(grid.charAt(idx))) {
          return -1;
        }
        list.add(new Pair(grid.charAt(idx), 1));
        idx++;
        len++;
      }
    }
    return len;
  }
}
