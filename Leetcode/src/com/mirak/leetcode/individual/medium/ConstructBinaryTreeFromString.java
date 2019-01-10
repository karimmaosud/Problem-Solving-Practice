package com.mirak.leetcode.individual.medium;

import java.util.*;


public class ConstructBinaryTreeFromString {

  private class TreeNode {

    int val;
    TreeNode left, right;

    TreeNode(int val) {
      this.val = val;
    }
  }

  public TreeNode str2tree(String s) {
    int[] closeIndex = new int[s.length()];
    Arrays.fill(closeIndex, -1);
    setCloseIndex(s, closeIndex);
    return makeTree(s, 0, closeIndex);
  }

  private TreeNode makeTree(String s, int left, int[] closeIndex) {
    if (left < 0 || left >= s.length()) {
      return null;
    }
    // 1. Get the value of the current root.
    int mul = 1;
    if (s.charAt(left) == '-') {
      mul = -1;
      left++;
    }
    int j = left;
    while (j < s.length() && Character.isDigit(s.charAt(j))) {
      j++;
    }

    if (j == left) {
      return null;
    }

    TreeNode root = new TreeNode(mul * Integer.parseInt(s.substring(left, j)));
    if (j < s.length() && s.charAt(j) == '(') {
      // 2. Set left and right children.
      root.left = makeTree(s, j + 1, closeIndex);
      root.right = makeTree(s, closeIndex[j] + 2, closeIndex);
    }
    return root;
  }

  private void setCloseIndex(String s, int[] closeIndex) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        stack.add(i);
      } else if (s.charAt(i) == ')') {
        closeIndex[stack.pop()] = i;
      }
    }
  }
}
