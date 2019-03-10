package com.mirak.leetcode.individual.hard;

public class ValidNumber {

  public boolean isNumber(String s) {
    s = s.trim();
    if (s.indexOf('e') != -1) {
      return validateFloat(s.substring(0, s.indexOf('e'))) && validateInteger(
          s.substring(s.indexOf('e') + 1), true, false);
    }
    return validateFloat(s);
  }


  private boolean validateFloat(String numStr) {
    if (numStr.isEmpty()) {
      return false;
    }

    if (numStr.charAt(0) == '+' || numStr.charAt(0) == '-') {
      numStr = numStr.substring(1);
    }

    if (numStr.isEmpty()) {
      return false;
    }

    int dotIndex = numStr.indexOf('.');

    if (dotIndex != -1) {
      String left = numStr.substring(0, dotIndex);
      String right = numStr.substring(dotIndex + 1);
      if (left.isEmpty() && right.isEmpty()) {
        return false;
      }
      return validateInteger(left, false, true) &&
          validateInteger(right, false, true);
    }
    return validateInteger(numStr, false, false);
  }

  private boolean validateInteger(String numStr, boolean expectSign, boolean acceptEmpty) {
    if (numStr.isEmpty()) {
      return acceptEmpty;
    }

    if (numStr.charAt(0) == '+' || numStr.charAt(0) == '-') {
      if (!expectSign) {
        return false;
      }
      numStr = numStr.substring(1);
    }

    if (numStr.isEmpty()) {
      return false;
    }

    for (int i = 0; i < numStr.length(); i++) {
      if (!Character.isDigit(numStr.charAt(i))) {
        return false;
      }
    }
    return true;
  }

}
