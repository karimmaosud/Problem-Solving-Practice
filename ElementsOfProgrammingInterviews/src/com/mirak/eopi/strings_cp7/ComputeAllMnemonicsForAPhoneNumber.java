package com.mirak.eopi.strings_cp7;

import java.util.*;

public class ComputeAllMnemonicsForAPhoneNumber {

  public List<String> phoneMnemonic(String phoneNumber) {
    Map<Character, List<Character>> digitToChars = new HashMap<>();
    initDigitToChars(digitToChars);

    int[] listPointers = new int[phoneNumber.length()];

    List<String> result = new LinkedList<>();

    while (listPointers[0] < digitToChars.get(phoneNumber.charAt(0)).size()) {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < phoneNumber.length(); i++) {
        builder.append(digitToChars.get(phoneNumber.charAt(i)).get(listPointers[i]));
      }
      result.add(builder.toString());
      int j = listPointers.length - 1;
      while (j >= 0) {
        listPointers[j]++;
        if (j > 0 && listPointers[j] == digitToChars.get(phoneNumber.charAt(j)).size()) {
          listPointers[j] = 0;
          j--;
        } else {
          break;
        }
      }
    }
    return result;
  }

  private void initDigitToChars(Map<Character, List<Character>> digitToChars) {
    digitToChars.put('0', Arrays.asList('0'));
    digitToChars.put('1', Arrays.asList('1'));
    digitToChars.put('2', Arrays.asList('A', 'B', 'C'));
    digitToChars.put('3', Arrays.asList('D', 'E', 'F'));
    digitToChars.put('4', Arrays.asList('G', 'H', 'I'));
    digitToChars.put('5', Arrays.asList('J', 'K', 'L'));
    digitToChars.put('6', Arrays.asList('M', 'N', 'O'));
    digitToChars.put('7', Arrays.asList('P', 'Q', 'R', 'S'));
    digitToChars.put('8', Arrays.asList('T', 'U', 'V'));
    digitToChars.put('9', Arrays.asList('W', 'X', 'Y', 'Z'));
  }
}
