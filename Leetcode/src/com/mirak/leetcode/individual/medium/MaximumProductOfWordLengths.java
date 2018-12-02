package com.mirak.leetcode.individual.medium;

public class MaximumProductOfWordLengths {

  public int maxProduct(String[] words) {

    int[] mask = new int[words.length];
    for (int i = 0; i < words.length; i++) {
      for (char c : words[i].toCharArray()) {
        mask[i] |= (1 << (c - 'a'));
      }
    }

    int maxLength = 0;

    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if ((mask[i] & mask[j]) == 0) {
          maxLength = Math.max(maxLength, words[i].length() * words[j].length());
        }
      }
    }
    return maxLength;
  }
}
