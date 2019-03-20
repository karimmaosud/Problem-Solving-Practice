package com.mirak.leetcode.individual.medium;

import java.util.*;

public class AccountsMerge {

  public List<List<String>> accountsMerge(List<List<String>> accounts) {

    Map<String, Integer> emailToId = new HashMap<>();
    Map<Integer, String> idToEmail = new HashMap<>();
    Map<Integer, String> componentName = new HashMap<>();

    init(accounts, emailToId, idToEmail);

    int[] p = new int[emailToId.size()];
    int[] rank = new int[p.length];

    for (int i = 0; i < p.length; i++) {
      p[i] = i;
    }

    for (List<String> account : accounts) {
      String name = account.get(0);
      if (account.size() == 1) {
        continue;
      }

      int parent = findParent(emailToId.get(account.get(1)), p);
      for (int i = 2; i < account.size(); i++) {
        parent = union(parent, emailToId.get(account.get(i)), p, rank);
      }
      componentName.put(parent, name);
    }

    Map<Integer, Set<String>> map = new HashMap<>();

    for (int i = 0; i < p.length; i++) {
      int parent = findParent(i, p);
      map.putIfAbsent(parent, new HashSet<>());
      map.get(parent).add(idToEmail.get(i));
    }
    List<List<String>> res = new LinkedList<>();
    for (int key : map.keySet()) {
      LinkedList<String> list = new LinkedList<>();
      list.add(componentName.get(key));

      ArrayList<String> temp = new ArrayList<>(map.get(key));

      Collections.sort(temp);
      list.addAll(temp);
      res.add(list);
    }
    return res;
  }


  private void init(List<List<String>> accounts, Map<String, Integer> emailToId,
      Map<Integer, String> idToEmail) {

    int id = 0;

    for (List<String> account : accounts) {
      for (int i = 1; i < account.size(); i++) {
        String email = account.get(i);
        if (!emailToId.containsKey(email)) {
          emailToId.put(email, id);
          idToEmail.put(id++, email);
        }
      }
    }
  }

  private int findParent(int i, int[] p) {
    return p[i] == i ? i : (p[i] = findParent(p[i], p));
  }

  private int union(int i, int j, int[] p, int[] rank) {
    int pi = findParent(i, p);
    int pj = findParent(j, p);
    if (rank[pi] > rank[pj]) {
      p[pj] = pi;
      return pi;
    }
    p[pi] = pj;
    if (rank[pi] == rank[pj]) {
      rank[pj]++;
    }
    return pj;
  }
}
