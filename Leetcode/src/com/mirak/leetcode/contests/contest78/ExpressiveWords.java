package com.mirak.leetcode.contests.contest78;

import java.util.ArrayList;

public class ExpressiveWords {
  private class CharCount{
    char c;
    short count;
    CharCount(char c, short count) {
      this.c = c;
      this.count = count;
    }
  }
  public int expressiveWords(String S, String[] words) {
    int res = 0;
    ArrayList<CharCount> original = getListFromString(S);
    for(String word: words) {
      if(original == null){
        res += (word == null || word.isEmpty())? 1: 0;
        continue;
      }
      ArrayList<CharCount> toExtendList = getListFromString(word);
      if(toExtendList == null){
        continue;
      }
      res += canBeExtended(original, toExtendList);
    }
    return res;
  }

  private ArrayList<CharCount> getListFromString(String word){
    if(word == null || word.isEmpty()) {
      return null;
    }
    ArrayList<CharCount> list = new ArrayList<>();
    char[] wordChars = word.toCharArray();
    char prev = wordChars[0];
    short count = 1;
    for(int i = 1; i < wordChars.length; i++) {
      if(wordChars[i] == prev){
        count++;
      }else{
        list.add(new CharCount(prev, count));
        count = 1;
        prev = wordChars[i];
      }
    }
    list.add(new CharCount(prev, count));
    return list;
  }

  private int canBeExtended(ArrayList<CharCount> original, ArrayList<CharCount> toExtendList) {
    if(original.size() != toExtendList.size()){
      return 0;
    }
    for(int i = 0; i < original.size(); i++) {
      CharCount currentOriginal = original.get(i);
      CharCount toExtend = toExtendList.get(i);
      if(currentOriginal.c != toExtend.c
          || (currentOriginal.count < 3 && currentOriginal.count != toExtend.count)
          || currentOriginal.count < toExtend.count) {
        return 0;
      }
    }
    return 1;
  }
}
