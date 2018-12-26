package com.mirak.leetcode.individual.medium;

import java.util.*;

public class OutputContestMatches {

  private class Pair {

    Pair p1 = null, p2 = null;
    int i, j;

    Pair(Pair p1, Pair p2) {
      this.p1 = p1;
      this.p2 = p2;
    }

    Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }

    @Override
    public String toString() {
      if (p1 != null) {
        return new StringBuilder("(").append(p1.toString()).append(",").append(p2.toString())
            .append(")").toString();
      }
      return "(" + i + "," + j + ")";
    }
  }

  public String findContestMatch(int n) {
    LinkedList<Pair> list = new LinkedList<>();

    for (int i = 1; i <= n / 2; i++) {
      list.addLast(new Pair(i, n - i + 1));
    }

    while (list.size() != 1) {
      LinkedList<Pair> next = new LinkedList<>();
      while (!list.isEmpty()) {
        next.add(new Pair(list.removeFirst(), list.removeLast()));
      }
      list = next;
    }
    return list.removeFirst().toString();
  }
}
