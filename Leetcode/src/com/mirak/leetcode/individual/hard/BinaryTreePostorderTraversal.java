package com.mirak.leetcode.individual.hard;

import java.util.*;

public class BinaryTreePostorderTraversal {

  public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }
  }

  public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> postOrder = new LinkedList<>();
    if (root == null) {
      return postOrder;
    }
    Stack<TreeNode> stack = new Stack<>();
    TreeNode current = root;
    while (current != null) {
      stack.add(current);
      current = current.left;
    }
    TreeNode lastAdded = null;
    while (!stack.isEmpty()) {
      current = stack.peek();
      if (current.right != null && current.right != lastAdded) {
        stack.push(current.right);
        TreeNode node = current.right.left;
        while (node != null) {
          stack.add(node);
          node = node.left;
        }
      } else {
        lastAdded = stack.pop();
        postOrder.add(current.val);
      }
    }
    return postOrder;
  }
}
