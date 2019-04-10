package com.mirak.leetcode.contests.contest131;

import java.util.*;

public class CamelcaseMatching {

  public List<Boolean> camelMatch(String[] queries, String pattern) {
    List<Boolean> res = new ArrayList<>();
    for (String query : queries) {
      if (match(query, pattern)) {
        res.add(true);
      } else {
        res.add(false);
      }
    }
    return res;
  }


  private boolean match(String query, String pattern) {
    int pq = 0, pp = 0;
    while (pq < query.length() && pp < pattern.length()) {
      char cq = query.charAt(pq);
      char cp = pattern.charAt(pp);

      if (cp == cq) {
        pp++;
        pq++;
        continue;
      }

      if (Character.isUpperCase(cq) && Character.isUpperCase(cp)) {
        return false;
      }

      if (Character.isUpperCase(cq)) {
        return false;
      }

      pq++;
    }
    if (pp != pattern.length()) {
      return false;
    }
    for (; pq < query.length(); ++pq) {
      if (Character.isUpperCase(query.charAt(pq))) {
        return false;
      }
    }
    return true;
  }

}
