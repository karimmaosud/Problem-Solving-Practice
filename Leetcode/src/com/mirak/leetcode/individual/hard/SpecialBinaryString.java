package com.mirak.leetcode.individual.hard;

import java.util.*;

public class SpecialBinaryString {

  private class Node {

    Node[] c;
    int count;

    Node() {
      c = new Node[2];
      count = 0;
    }
  }

  private class Trie {

    Node root;

    Trie() {
      root = new Node();
    }
  }

  public String makeLargestSpecial(String S) {
    int[] len = getSpecialSubstringsLengths(S);
    return solve(S, 0, S.length() - 1, len);
  }

  private int[] getSpecialSubstringsLengths(String s) {
    int[] len = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      len[i] = 1;
      if (s.charAt(i) == '0') {
        continue;
      }
      int onesCount = 0;
      int j = i;
      for (j = i; j < s.length(); j++) {
        onesCount += s.charAt(j) == '1' ? 1 : -1;
        if (onesCount == 0) {
          break;
        }
      }
      len[i] = j - i + 1;
    }
    return len;
  }

  private String solve(String s, int left, int right, int[] len) {
    if (left == right) {
      return "" + s.charAt(left);
    }
    if (left > right) {
      return "";
    }
    if (len[left] == right - left + 1) {
      return "1" + solve(s, left + 1, right - 1, len) + "0";
    }
    int idx = left;
    ArrayList<String> splits = new ArrayList<>();
    while (idx <= right) {
      splits.add(solve(s, idx, idx + len[idx] - 1, len));
      idx += len[idx];
    }
    Trie trie = makeTrieFromSplits(splits);
    StringBuilder builder = new StringBuilder();
    while (hasRemainingStrings(trie)) {
      appendMaximalString(trie.root, builder);
    }
    return builder.toString();
  }

  private Trie makeTrieFromSplits(ArrayList<String> splits) {
    Trie trie = new Trie();
    for (String split : splits) {
      Node current = trie.root;
      for (char c : split.toCharArray()) {
        if (current.c[c - '0'] == null) {
          current.c[c - '0'] = new Node();
        }
        current = current.c[c - '0'];
        current.count++;
      }
    }
    return trie;
  }

  private void appendMaximalString(Node node, StringBuilder builder) {
    if (node.c[1] != null && node.c[1].count > 0) {
      builder.append('1');
      node.c[1].count--;
      appendMaximalString(node.c[1], builder);
    } else if (node.c[0] != null && node.c[0].count > 0) {
      builder.append('0');
      node.c[0].count--;
      appendMaximalString(node.c[0], builder);
    }
  }

  private boolean hasRemainingStrings(Trie trie) {
    return (trie.root.c[0] == null ? 0 : trie.root.c[0].count) + (trie.root.c[1] == null ? 0
        : trie.root.c[1].count) > 0;
  }
}
