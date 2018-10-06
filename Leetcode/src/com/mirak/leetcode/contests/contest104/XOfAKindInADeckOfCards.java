package com.mirak.leetcode.contests.contest104;

public class XOfAKindInADeckOfCards {
  public boolean hasGroupsSizeX(int[] deck) {
    if(deck.length <= 1) {
      return false;
    }
    int[] count = new int[10010];
    for(int i = 0; i < deck.length; i++) {
      count[deck[i]]++;
    }

    int g = 0;
    for(int i = 0; i < deck.length; i++) {
      if(count[i] > 0) {
        g = count[i];
        break;
      }
    }


    for(int i = 0; i < deck.length; i++) {
      if(count[i] != 0) {
        g = gcd(g, count[i]);
      }
    }

    return g > 1;
  }

  private int gcd(int a, int b) {
    if(b == 0) {
      return a;
    }
    return gcd(b, a % b);
  }
}
