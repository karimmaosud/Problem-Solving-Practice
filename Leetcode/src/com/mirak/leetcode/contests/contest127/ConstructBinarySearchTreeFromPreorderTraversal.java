package com.mirak.leetcode.contests.contest127;

import java.util.*;

public class ConstructBinarySearchTreeFromPreorderTraversal {

  private class TreeNode {

    TreeNode left, right;
    int val;

    TreeNode(int val) {
      this.val = val;
      left = right = null;
    }
  }

  public TreeNode bstFromPreorder(int[] preorder) {
    if (preorder.length == 0) {
      return null;
    }
    TreeNode root = new TreeNode(preorder[0]);
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    for (int i = 1; i < preorder.length; i++) {
      if (preorder[i] < stack.peek().val) {
        stack.peek().left = new TreeNode(preorder[i]);
        stack.push(stack.peek().left);
      } else {
        TreeNode parent = stack.peek();
        while (!stack.isEmpty() && stack.peek().val < preorder[i]) {
          parent = stack.pop();
        }
        parent.right = new TreeNode(preorder[i]);
        stack.push(parent.right);
      }
    }
    return root;
  }

}
