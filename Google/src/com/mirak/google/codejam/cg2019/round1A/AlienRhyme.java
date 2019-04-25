package com.mirak.google.codejam.cg2019.round1A;

import java.io.*;

public class AlienRhyme {

  private static class Node {

    Node[] c;
    Node parent;
    int count;

    Node() {
      c = new Node[26];
      count = 0;
      parent = null;
    }
  }

  private static class Trie {

    Node root;

    Trie() {
      root = new Node();
    }

    void insertWord(String word) {
      Node current = root;
      for (int i = word.length() - 1; i >= 0; --i) {
        char a = word.charAt(i);
        if (current.c[a - 'A'] == null) {
          current.c[a - 'A'] = new Node();
        }
        current.c[a - 'A'].parent = current;
        current = current.c[a - 'A'];
        current.count++;
      }
    }
  }

  private static int solve(Node node, Node root) {
    int ans = 0;
    for (int i = 0; i < node.c.length; ++i) {
      if (node.c[i] != null) {
        ans += solve(node.c[i], root);
      }
    }

    if (node.count >= 2) {
      Node p = node;
      ans += 2;
      while (p != root) {
        p.count -= 2;
        p = p.parent;
      }
    }
    return ans;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(reader.readLine());
    for (int t = 1; t <= T; ++t) {
      int n = Integer.parseInt(reader.readLine());
      String[] words = new String[n];
      for (int i = 0; i < n; ++i) {
        words[i] = reader.readLine();
      }
      Trie trie = getTrieFromWords(words);
      System.out.println("Case #" + t + ": " + solve(trie.root, trie.root));
    }
  }

  private static Trie getTrieFromWords(String[] words) {
    Trie trie = new Trie();
    for (String word : words) {
      trie.insertWord(word);
    }
    return trie;
  }
}
