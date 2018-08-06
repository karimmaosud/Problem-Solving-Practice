package com.mirak.leetcode.individual.hard;

import java.util.LinkedList;

public class RecoverBinarySearchTree {

  private class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public void recoverTree(TreeNode root) {
    LinkedList<TreeNode> runnerList = new LinkedList<>();
    LinkedList<TreeNode> swappedNodes = new LinkedList<>();
    inOrdereTraversal(root, runnerList, swappedNodes);
    if (swappedNodes.size() == 0) {
      return;
    }
    TreeNode first = swappedNodes.removeFirst();
    TreeNode second = swappedNodes.removeFirst();
    int temp = first.val;
    first.val = second.val;
    second.val = temp;
  }


  private void inOrdereTraversal(TreeNode node, LinkedList<TreeNode> runnerList,
      LinkedList<TreeNode> swappedNodes) {

    if (node == null) {
      return;
    }

    inOrdereTraversal(node.left, runnerList, swappedNodes);

    if (runnerList.size() > 0) {
      TreeNode prev = runnerList.removeLast();
      if (node.val < prev.val) {
        // misplaced node.
        if (swappedNodes.size() > 0) {
          swappedNodes.removeLast();
        } else {
          swappedNodes.add(prev);
        }
        swappedNodes.add(node);
      }
    }

    runnerList.addLast(node);

    inOrdereTraversal(node.right, runnerList, swappedNodes);
  }
}
