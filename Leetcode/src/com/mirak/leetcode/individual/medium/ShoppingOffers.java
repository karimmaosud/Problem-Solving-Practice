package com.mirak.leetcode.individual.medium;

import java.util.*;

public class ShoppingOffers {

  private final int INF = 1000000000;

  public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    return solve(special, needs, price, new HashMap<>());
  }

  private int solve(List<List<Integer>> offers, List<Integer> needs, List<Integer> price,
      Map<List<Integer>, Integer> mem) {

    if (mem.containsKey(needs)) {
      return mem.get(needs);
    }

    int ans = INF;
    for (List<Integer> offer : offers) {
      List<Integer> count = new ArrayList<>();
      int i;
      for (i = 0; i < needs.size(); ++i) {
        if (needs.get(i) - offer.get(i) < 0) {
          break;
        }
        count.add(needs.get(i) - offer.get(i));
      }
      if (i == needs.size()) {
        ans = Math.min(ans, offer.get(offer.size() - 1) + solve(offers, count, price, mem));
      }
    }
    int individualBuy = 0;
    for (int i = 0; i < needs.size(); i++) {
      individualBuy += needs.get(i) * price.get(i);
    }
    ans = Math.min(ans, individualBuy);
    mem.put(needs, ans);
    return ans;
  }

}
