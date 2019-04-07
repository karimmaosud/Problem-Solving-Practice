package com.mirak.leetcode.individual.medium;

import java.util.*;

public class HouseRobberIII {

  private class TreeNode {

    TreeNode left, right;
    int val;
  }

  private class Pair {

    TreeNode node;
    boolean parentTaken;

    Pair(TreeNode node, boolean parentTaken) {
      this.node = node;
      this.parentTaken = parentTaken;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Pair)) {
        return false;
      }
      Pair that = (Pair) o;
      return this.node == that.node && this.parentTaken == that.parentTaken;
    }

    @Override
    public int hashCode() {
      return (this.node.hashCode() + "#" + parentTaken).hashCode();
    }
  }

  public int rob(TreeNode root) {
    return solve(new Pair(root, false), new HashMap<>());
  }

  private int solve(Pair pair, Map<Pair, Integer> mem) {
    if (pair.node == null) {
      return 0;
    }
    if (mem.containsKey(pair)) {
      return mem.get(pair);
    }
    int ans = solve(new Pair(pair.node.left, false), mem) + solve(new Pair(pair.node.right, false),
        mem);
    if (!pair.parentTaken) {
      ans = Math.max(ans, pair.node.val + solve(new Pair(pair.node.left, true), mem) + solve(
          new Pair(pair.node.right, true), mem));
    }
    mem.put(pair, ans);
    return ans;
  }
}
