package com.mirak.leetcode.contests.contest91;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AllNodesDistanceKInBinaryTree {

  class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

    if (root == null || target == null) {
      return new LinkedList<>();
    }

    List<Integer> res = new LinkedList<>();

    if (K == 0) {
      res.add(target.val);
      return res;
    }

    Map<TreeNode, TreeNode> parent = new HashMap<>();

    // init parent map
    dfs(root, target, parent);

    bfs(target, K, res);
    K--;
    TreeNode start = parent.getOrDefault(target, null);
    TreeNode prev = target;
    while (start != null) {
      if (K == 0) {
        res.add(start.val);
        break;
      }
      if (prev == start.left) {
        bfs(start.right, K - 1, res);
      } else {
        bfs(start.left, K - 1, res);
      }
      prev = start;
      start = parent.getOrDefault(start, null);
      K--;
    }

    return res;
  }

  private void dfs(TreeNode node, TreeNode target, Map<TreeNode, TreeNode> parent) {
    if (node == target) {
      return;
    }

    if (node.left != null) {
      parent.put(node.left, node);
      dfs(node.left, target, parent);
    }

    if (node.right != null) {
      parent.put(node.right, node);
      dfs(node.right, target, parent);
    }
  }

  private void bfs(TreeNode start, int K, List<Integer> res) {

    if (start == null) {
      return;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    Set<TreeNode> vis = new HashSet<>();

    vis.add(start);
    queue.add(start);

    while (K > 0 && !queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        if (node.left != null && !vis.contains(node.left)) {
          queue.add(node.left);
          vis.add(node.left);
        }
        if (node.right != null && !vis.contains(node.right)) {
          queue.add(node.right);
          vis.add(node.right);
        }
      }
      K--;
    }

    if (K != 0) {
      return;
    }

    while (!queue.isEmpty()) {
      res.add(queue.poll().val);
    }
  }

}
