package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ClosestBinarySearchTreeValueII {

  private class TreeNode {

    TreeNode left, right;
    int val;
  }

  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    LinkedList<Integer> successors = new LinkedList<>();
    LinkedList<Integer> predecessors = new LinkedList<>();

    inorderSuccessors(root, target, successors, k);
    inorderPredecessors(root, target, predecessors, k);

    System.out.println(successors);
    System.out.println(predecessors);

    List<Integer> res = new ArrayList<>();

    while (k > 0) {
      if (successors.isEmpty()) {
        res.add(predecessors.removeFirst());
      } else if (predecessors.isEmpty()) {
        res.add(successors.removeFirst());
      } else {
        if (Math.abs(target - successors.getFirst()) > Math.abs(target - predecessors.getFirst())) {
          res.add(predecessors.removeFirst());
        } else {
          res.add(successors.removeFirst());
        }
      }
      k--;
    }
    return res;
  }

  private void inorderSuccessors(TreeNode node, double target, LinkedList<Integer> successors,
      int k) {
    if (node == null) {
      return;
    }
    if (target > node.val) {
      inorderSuccessors(node.right, target, successors, k);
    } else {
      inorderSuccessors(node.left, target, successors, k);
      if (successors.size() < k) {
        successors.addLast(node.val);
        inorderSuccessors(node.right, target, successors, k);
      }
    }
  }

  private void inorderPredecessors(TreeNode node, double target, LinkedList<Integer> predecessors,
      int k) {
    if (node == null) {
      return;
    }

    if (target <= node.val) {
      inorderPredecessors(node.left, target, predecessors, k);
    } else {
      inorderPredecessors(node.right, target, predecessors, k);
      if (predecessors.size() < k) {
        predecessors.addLast(node.val);
        inorderPredecessors(node.left, target, predecessors, k);
      }
    }
  }
}
