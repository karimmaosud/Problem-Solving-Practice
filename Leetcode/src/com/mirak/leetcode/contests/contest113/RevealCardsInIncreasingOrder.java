package com.mirak.leetcode.contests.contest113;

import java.util.*;

public class RevealCardsInIncreasingOrder {

  public int[] deckRevealedIncreasing(int[] deck) {
    Arrays.sort(deck);
    Deque<Integer> deque = new LinkedList<>();
    for (int i = 0; i < deck.length; i++) {
      deque.addLast(i);
    }
    int[] ans = new int[deck.length];
    for (int card : deck) {
      ans[deque.pollFirst()] = card;
      if (!deque.isEmpty()) {
        deque.addLast(deque.pollFirst());
      }
    }
    return ans;
  }
}
