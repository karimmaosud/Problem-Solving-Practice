package com.mirak.leetcode.individual.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;


public class NumberOfAtoms {
  public String countOfAtoms (String formula) {
    Map<String, Integer> atomsCount = new HashMap<>();
    Stack<Integer> div = new Stack<>();
    int idx = formula.length() - 1;
    int mul = 1;
    while(idx >= 0) {

      if(Character.isDigit(formula.charAt(idx))) {
        int j = idx;
        while(j > 0 && Character.isDigit(formula.charAt(j))){
          j--;
        }

        int count = Integer.parseInt(formula.substring(j + 1, idx + 1));

        if(Character.isAlphabetic(formula.charAt(j))) {
          int i = j;
          while(i >= 0 && !Character.isUpperCase(formula.charAt(i))) {
            i--;
          }
          // formula.charAt(i) is an uppercase letter.
          String atom = formula.substring(i, j + 1);
          atomsCount.put(atom, atomsCount.getOrDefault(atom, 0) + count * mul);
          idx = i - 1;
        }else {
          // formula.charAt(idx) = ‘)’
          mul *= count;
          div.push(count);
          idx = j - 1;
        }
      }else if (formula.charAt(idx) == '(') {
        if(!div.isEmpty()) {
          mul /= div.pop();
        }
        idx--;
      }else if(formula.charAt(idx) == ')'){
        div.push(1);
        idx--;
      }else {
        // atom
        int i = idx;
        while(!Character.isUpperCase(formula.charAt(i))) {
          i--;
        }
        String atom = formula.substring(i, idx + 1);
        atomsCount.put(atom, atomsCount.getOrDefault(atom, 0) + mul);
        idx = i - 1;
      }
    }

    ArrayList<Entry<String, Integer>> list= new ArrayList<>(atomsCount.entrySet());

    Collections.sort(list, new Comparator<Entry<String, Integer>>() {
      @Override
      public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
        return e1.getKey().compareTo(e2.getKey());
      }
    });

    StringBuilder builder = new StringBuilder();
    for(Entry<String, Integer> entry: list) {
      builder.append(entry.getKey());
      if(entry.getValue() > 1) {
        builder.append(entry.getValue());
      }
    }
    return builder.toString();
  }
}
