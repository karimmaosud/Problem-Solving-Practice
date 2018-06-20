package com.mirak.careercup.palantir;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BalancedChemicalReactions {
  public static boolean isBalancedFormula(String formula) {
    String[] equations = formula.split("->");
    System.out.println(equations[0]);
    System.out.println(equations[1]);
    Map<String, Integer> leftAtomsCount = formulaToAtomsCount(equations[0]);
    Map<String, Integer> rightAtomsCount = formulaToAtomsCount(equations[1]);
    return verify(leftAtomsCount, rightAtomsCount);
  }

  private static boolean verify(
      Map<String, Integer> leftAtomsCount, Map<String, Integer> rightAtomsCount) {
    if (leftAtomsCount.size() != rightAtomsCount.size()) {
      return false;
    }
    for (Entry<String, Integer> entry : leftAtomsCount.entrySet()) {
      Integer rightCount = rightAtomsCount.get(entry.getKey());
      if (rightCount == null || rightCount.compareTo(entry.getValue()) != 0) {
        return false;
      }
    }
    return true;
  }

  private static Map<String, Integer> formulaToAtomsCount(String formula) {

    Map<String, Integer> atomsCount = new HashMap<>();

    String[] items = formula.split("\\+");

    for (String item : items) {

      int idx = 0;
      while (item.charAt(idx) == ' ') {
        idx++;
      }

      int mul = 1;

      if (Character.isDigit(item.charAt(idx))) {
        int j = idx;
        while (j < item.length() && Character.isDigit(item.charAt(j))) {
          j++;
        }
        mul = Integer.parseInt(item.substring(idx, j));
        while (item.charAt(j) == ' ') {
          j++;
        }
        idx = j;
      }

      StringBuilder builder = new StringBuilder();
      int atoms = 0;
      for (int j = idx; j < item.length(); j++) {
        if(item.charAt(j) == ' '){
          continue;
        }
        if (Character.isDigit(item.charAt(j))) {
          atoms = atoms * 10 + (item.charAt(j) - '0');
        } else {
          if (Character.isUpperCase(item.charAt(j)) && builder.length() > 0) {
            atomsCount.put(
                builder.toString(),
                atomsCount.getOrDefault(builder.toString(), 0) + mul * (atoms == 0 ? 1 : atoms));
            builder = new StringBuilder();
            atoms = 0;
          }
          builder.append(item.charAt(j));
        }
      }
      atomsCount.put(
          builder.toString(),
          atomsCount.getOrDefault(builder.toString(), 0) + mul * (atoms == 0 ? 1 : atoms));
    }

    return atomsCount;
  }
}
