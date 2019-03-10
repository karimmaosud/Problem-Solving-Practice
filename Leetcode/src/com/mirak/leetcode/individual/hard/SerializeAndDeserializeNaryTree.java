package com.mirak.leetcode.individual.hard;

import java.util.*;

public class SerializeAndDeserializeNaryTree {

  private class Node {

    int val;
    List<Node> children;

    public Node() {
    }

    public Node(int _val, List<Node> _children) {
      val = _val;
      children = _children;
    }
  }

  class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
      if (root == null) {
        return "";
      }

      StringBuilder builder = new StringBuilder();

      Queue<Node> q = new LinkedList<>();
      q.add(root);

      builder.append(root.val);

      while (!q.isEmpty()) {
        Node top = q.poll();

        builder.append('(');

        if (!top.children.isEmpty()) {
          q.add(top.children.get(0));
          builder.append(top.children.get(0).val);
        }

        for (int i = 1; i < top.children.size(); i++) {
          builder.append(',').append(top.children.get(i).val);
          q.add(top.children.get(i));
        }

        builder.append(')');
      }
      return builder.toString();
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
      return parse(data);
    }

    private Node parse(String data) {
      if (data.length() == 0) {
        return null;
      }

      Node root = new Node(Integer.parseInt(data.substring(0, data.indexOf('('))),
          new ArrayList<>());

      Queue<Node> q = new LinkedList<>();
      int idx = data.indexOf('(');
      q.add(root);
      while (!q.isEmpty()) {

        Node top = q.poll();

        int next = data.indexOf(')', idx + 1);

        String childrenStr = data.substring(idx + 1, next);

        idx = next + 1;

        if (childrenStr.isEmpty()) {
          continue;
        }

        String[] vals = childrenStr.split(",");
        for (int i = 0; i < vals.length; i++) {
          int nodeValue = Integer.parseInt(vals[i]);
          Node child = new Node(nodeValue, new ArrayList<>());
          top.children.add(child);
          q.add(child);
        }
      }
      return root;
    }
  }

}
