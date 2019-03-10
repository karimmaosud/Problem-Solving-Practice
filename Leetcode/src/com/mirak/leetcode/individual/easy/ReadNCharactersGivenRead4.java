package com.mirak.leetcode.individual.easy;

public class ReadNCharactersGivenRead4 {

  public int read(char[] buf, int n) {
    int actualRead = 0;
    char[] tempBuffer = new char[4];
    while (actualRead < n) {
      int readChars = read4(tempBuffer);
      if (readChars == 0) {
        return actualRead;
      }
      int i;
      for (i = 0; i < readChars && i + actualRead < n; i++) {
        buf[actualRead + i] = tempBuffer[i];
      }
      actualRead += i;
    }
    return actualRead;
  }

  private int read4(char[] buf) {
    return 0;
  }

}


