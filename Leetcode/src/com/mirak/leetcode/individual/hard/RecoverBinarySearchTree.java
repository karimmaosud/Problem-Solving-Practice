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
    if (root == null) {
      return;
    }
    LinkedList<TreeNode> list = new LinkedList<>();
    inorderTraversal(root, list);
    if (list.size() == 2 && list.getFirst().val > list.getLast().val) {
      swapNodeValues(list.getFirst(), list.getLast());
    }
  }

  private void inorderTraversal(TreeNode node, LinkedList<TreeNode> list) {
    if (node == null) {
      return;
    }

    inorderTraversal(node.left, list);

    if (list.size() == 2) {
      System.out.println(list.getFirst().val + " " + list.getLast().val + " .. " + node.val);
      if (list.getFirst().val > list.getLast().val && node.val > list.getFirst().val) {
        swapNodeValues(list.getFirst(), list.getLast());
      }

      if (list.getFirst().val > list.getLast().val) {
        list.removeLast();
      } else {
        list.removeFirst();
      }
    }

    list.addLast(node);
    inorderTraversal(node.right, list);
  }


  private void swapNodeValues(TreeNode n1, TreeNode n2) {
    int temp = n1.val;
    n1.val = n2.val;
    n2.val = temp;
  }
}
