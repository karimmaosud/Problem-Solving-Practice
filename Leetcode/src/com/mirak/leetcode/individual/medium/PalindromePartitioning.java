package com.mirak.leetcode.individual.medium;

import java.util.*;

public class PalindromePartitioning {

  public List<List<String>> partition(String s) {
    List<List<String>> palindromePartitions = new LinkedList<>();
    if (s.length() == 0) {
      return palindromePartitions;
    }
    ArrayList<Integer>[] palindromeLengths = new ArrayList[s.length()];
    for (int i = 0; i < s.length(); i++) {
      palindromeLengths[i] = new ArrayList<>();
    }
    findPalindromes(s, palindromeLengths, true);
    findPalindromes(s, palindromeLengths, false);
    findAllPartitions(0, new LinkedList<>(), palindromeLengths, palindromePartitions, s);
    return palindromePartitions;
  }

  private void findPalindromes(String s, ArrayList<Integer>[] palindromeLengths,
      boolean oddPalindrome) {
    for (int i = 0; i < s.length(); i++) {
      int l = i;
      int r = oddPalindrome ? i : i + 1;
      while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        palindromeLengths[l].add(r - l + 1);
        l--;
        r++;
      }
    }
  }

  private void findAllPartitions(int index, LinkedList<Integer> partition,
      ArrayList<Integer>[] palindromeLengths, List<List<String>> palindromePartitions, String s) {

    if (index == s.length()) {
      int size = partition.size();
      List<String> addPartition = new LinkedList<>();
      int j = 0;
      for (int i = 0; i < size; i++) {
        int len = partition.removeFirst();
        addPartition.add(s.substring(j, j + len));
        partition.addLast(len);
        j += len;
      }
      palindromePartitions.add(addPartition);
      return;
    }

    for (int i = 0; i < palindromeLengths[index].size(); i++) {
      partition.addLast(palindromeLengths[index].get(i));
      findAllPartitions(index + palindromeLengths[index].get(i), partition, palindromeLengths,
          palindromePartitions, s);
      partition.removeLast();
    }
  }
}
