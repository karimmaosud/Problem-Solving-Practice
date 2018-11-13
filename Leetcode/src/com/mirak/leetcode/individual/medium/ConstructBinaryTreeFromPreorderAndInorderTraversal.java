package com.mirak.leetcode.individual.medium;

import java.util.*;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

  private class IntegerWrapper {

    int index;

    IntegerWrapper() {
      this.index = 0;
    }
  }

  private class TreeNode {

    TreeNode left, right;
    int val;

    TreeNode(int val) {
      this.val = val;
      this.left = null;
      this.right = null;
    }
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    Map<Integer, Integer> elementToIndex = new HashMap<>();
    initElementToIndexMap(elementToIndex, inorder);
    return constructTree(0, preorder.length - 1, new IntegerWrapper(), preorder, elementToIndex);
  }

  private TreeNode constructTree(int left, int right, IntegerWrapper preorderIndex, int[] preorder,
      Map<Integer, Integer> elementToIndex) {
    if (left > right) {
      preorderIndex.index--;
      return null;
    }

    TreeNode node = new TreeNode(preorder[preorderIndex.index]);

    int inorderIndex = elementToIndex.get(preorder[preorderIndex.index]);

    preorderIndex.index++;
    node.left = constructTree(left, inorderIndex - 1, preorderIndex, preorder,
        elementToIndex);

    preorderIndex.index++;
    node.right = constructTree(inorderIndex + 1, right, preorderIndex, preorder,
        elementToIndex);
    return node;
  }

  private void initElementToIndexMap(Map<Integer, Integer> elementToIndex, int[] inorder) {
    for (int i = 0; i < inorder.length; i++) {
      elementToIndex.put(inorder[i], i);
    }
  }
}
