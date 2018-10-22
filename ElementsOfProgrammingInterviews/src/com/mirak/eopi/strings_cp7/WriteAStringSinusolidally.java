package com.mirak.eopi.strings_cp7;

public class WriteAStringSinusolidally {

  public String writeStringSinusolidally(String s) {

    StringBuilder[] builders = new StringBuilder[3];
    builders[0] = new StringBuilder();
    builders[1] = new StringBuilder();
    builders[2] = new StringBuilder();

    int index = 1;
    boolean up = true;
    for (int i = 0; i < s.length(); i++) {
      builders[index].append(s.charAt(i));
      if (index == 0 || index == 2) {
        index = 1;
      } else {
        if (up) {
          index--;
        } else {
          index++;
        }
        up = !up;
      }
    }
    return builders[0].append(builders[1]).append(builders[0]).toString();
  }
}
