package com.mirak.leetcode.individual.hard;

public class ReadNCharactersGivenRead4IICallMultipleTimes {

  private int prevRead = 0;
  private int offset = 0;
  private char[] tempBuffer = new char[4];

  public int read(char[] buf, int n) {

    int actualRead = fillBufferFromPrevState(buf, n);
    prevRead -= actualRead;
    offset += actualRead;

    if (actualRead == n) {
      return actualRead;
    }

    while (actualRead < n) {
      int readCount = read4(tempBuffer);
      if (readCount == 0) {
        prevRead = 0;
        offset = 0;
        return actualRead;
      }
      int i;
      for (i = 0; i < readCount && i + actualRead < n; i++) {
        buf[actualRead + i] = tempBuffer[i];
      }
      actualRead += i;
      prevRead = readCount - i;
      offset = i % tempBuffer.length;
    }
    return actualRead;
  }

  private int fillBufferFromPrevState(char[] buf, int n) {
    for (int i = 0; i < Math.min(prevRead, n); i++) {
      buf[i] = tempBuffer[offset + i];
    }
    return Math.min(prevRead, n);
  }

  private int read4(char[] buf) {
    return 0;
  }
}

