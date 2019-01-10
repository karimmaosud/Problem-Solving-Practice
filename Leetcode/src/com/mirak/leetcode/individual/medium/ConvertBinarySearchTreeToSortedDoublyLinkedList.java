package com.mirak.leetcode.individual.medium;

import java.util.Stack;

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

  private class Node {

    Node left, right;
  }

  public Node treeToDoublyList(Node root) {
    Node prev = null, head = null;
    Stack<Node> stack = new Stack<>();
    addLeftNodes(root, stack);
    while (!stack.isEmpty()) {
      Node current = stack.pop();
      if (head == null) {
        head = current;
      } else {
        prev.right = current;
        current.left = prev;
      }
      prev = current;
      addLeftNodes(current.right, stack);
    }

    if (head != null) {
      prev.right = head;
      head.left = prev;
    }
    return head;
  }

  private void addLeftNodes(Node node, Stack<Node> stack) {
    while (node != null) {
      stack.add(node);
      node = node.left;
    }
  }
}
