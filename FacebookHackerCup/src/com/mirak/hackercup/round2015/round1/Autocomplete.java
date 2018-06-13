package com.mirak.hackercup.round2015.round1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Autocomplete {
  private static class TrieNode {
    TrieNode[] c;
    int count;
    TrieNode() {
      c = new TrieNode[26];
      count = 0;
    }
  }
  private static class Trie {
    TrieNode root;
    Trie() {
      root = new TrieNode();
    }

    int insertWord(String word) {
      TrieNode current = root;
      int ret = -1;
      int typedChars = 0;
      for(int i = 0; i < word.length(); i++) {
        if(current.c[word.charAt(i) - 'a'] == null) {
          current.c[word.charAt(i) - 'a'] = new TrieNode();
        }
        typedChars++;
        current = current.c[word.charAt(i) - 'a'];
        current.count++;
        if(current.count == 1 && ret == -1) {
          ret = typedChars;
        }
      }
      return ret == -1? typedChars: ret;
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("/Users/masoud/Desktop/autocomplete.txt"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    int T = Integer.parseInt(reader.readLine());
    for(int t = 1; t <= T; t++) {
      Trie trie = new Trie();
      int n = Integer.parseInt(reader.readLine());
      int res = 0;
      for(int i = 0; i < n; i++) {
        String word = reader.readLine();
        res += trie.insertWord(word);
      }
      writer.write("Case #" + t + ": " + res + "\n");
    }
    reader.close();
    writer.close();
  }
}
