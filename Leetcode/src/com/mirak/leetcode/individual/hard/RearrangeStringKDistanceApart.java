package com.mirak.leetcode.individual.hard;

public class RearrangeStringKDistanceApart {

  public String rearrangeString(String s, int k) {

    if(s.length() == 0 || k == 0) {
      return s;
    }

    int[] charCount = new int[26];
    int[] lastIndex = new int[26];
    for(int i = 0; i < s.length(); i++) {
      charCount[s.charAt(i) - 'a']++;
      lastIndex[s.charAt(i) - 'a'] = -1000000000;
    }
    char[] resChar = new char[s.length()];
    for(int i = 0; i < s.length(); i++) {
      int chosen = -1;
      int maxOccurrence = 0;
      for(int j = 0; j < 26; j++) {
        if(charCount[j] == 0) {
          continue;
        }
        if(i - lastIndex[j] >= k && charCount[j] > maxOccurrence) {
          chosen = j;
          maxOccurrence = charCount[j];
        }
      }
      if(chosen == -1) {
        return "";
      }
      resChar[i] = (char) (chosen + 'a');
      charCount[chosen]--;
      lastIndex[chosen] = i;
    }
    return new String(resChar);
  }
}