package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ZumaGame {

  private final int INF = 1000000000;
  private final char[] charSet = {'R', 'Y', 'B', 'G', 'W'};

  public int findMinStep(String board, String hand) {
    if (board.length() == 0) {
      return -1;
    }
    char[] sortedHand = hand.toCharArray();
    Arrays.sort(sortedHand);
    int res = solve(board, new String(sortedHand), new HashMap<>());
    return res == INF ? -1 : res;
  }

  private int solve(String board, String hand, Map<String, Integer> dp) {
    if (board.isEmpty()) {
      return 0;
    }
    if (hand.isEmpty()) {
      return INF;
    }
    String key = board + "#" + hand;
    if (dp.containsKey(key)) {
      return dp.get(key);
    }
    int ans = INF;
    for (char a : charSet) {
      if (hand.indexOf(a) == -1) {
        continue;
      }
      int idx = 0;
      while (idx < board.length()) {
        if (board.charAt(idx) == a) {
          String nextBoard = getNextBoard(board, idx, a);
          String nextHand = getNextHand(hand, a);
          ans = Math.min(ans, 1 + solve(nextBoard, nextHand, dp));
          idx = getNextIdx(board, idx);
        } else {
          idx++;
        }
      }
    }
    dp.put(key, ans);
    return ans;
  }

  private String getNextBoard(String board, int idx, char a) {
    LinkedList<Character> list = new LinkedList<>();
    for (int i = 0; i < idx; i++) {
      list.addLast(board.charAt(i));
    }
    list.addLast(a);
    for (int i = idx; i < board.length(); i++) {
      list.addLast(board.charAt(i));
    }

    Stack<Character> stack = new Stack<>();

    while (!list.isEmpty()) {
      char top = list.removeFirst();
      if (stack.size() < 2) {
        stack.push(top);
        continue;
      }
      char second = stack.pop();
      char first = stack.pop();
      if (first == second && first == top) {
        while (!list.isEmpty() && list.getFirst() == top) {
          list.removeFirst();
        }
      } else {
        stack.push(first);
        stack.push(second);
        stack.push(top);
      }
    }

    StringBuilder builder = new StringBuilder();
    while (!stack.isEmpty()) {
      builder.append(stack.pop());
    }
    return builder.reverse().toString();
  }


  private String getNextHand(String hand, char chosen) {
    char[] nextHand = new char[hand.length() - 1];
    boolean found = false;
    int idx = 0;
    for (char a : hand.toCharArray()) {
      if (a == chosen && !found) {
        found = true;
        continue;
      }
      nextHand[idx++] = a;
    }
    return new String(nextHand);
  }

  private int getNextIdx(String board, int idx) {
    char skipped = board.charAt(idx);
    while (idx < board.length() && board.charAt(idx) == skipped) {
      idx++;
    }
    return idx;
  }
}
