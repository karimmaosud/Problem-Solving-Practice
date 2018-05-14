package com.mirak.leetcode.contests.contest77;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorseCodeWords {
  private String[] charMapping = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

  public int uniqueMorseRepresentations(String[] words) {
    Set<String> uniqueWords = new HashSet<>();
    for(String word: words) {
      String morseRepresentation =  getMorseRepresentation(word);
      uniqueWords.add(morseRepresentation);
    }
    return uniqueWords.size();
  }

  private String getMorseRepresentation(String word) {
    StringBuilder builder = new StringBuilder();
    for(char c: word.toCharArray()) {
      builder.append(charMapping[c - 'a']);
    }
    return builder.toString();
  }
}
