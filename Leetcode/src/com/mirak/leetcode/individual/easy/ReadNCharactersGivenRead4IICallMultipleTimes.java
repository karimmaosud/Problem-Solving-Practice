package com.mirak.leetcode.individual.easy;

public class ReadNCharactersGivenRead4IICallMultipleTimes extends Reader4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
  char[] buffer = new char[4];
  int bufferLength = 0;
  public int read(char[] buf, int n) {
    char[] dummy = new char[4];
    for(int i = 0; i < 4; i++) {
      dummy[i] = buffer[i];
    }
    int read = initBuf(buf, n);
    int copied = Math.min(n, read);
    int idx = copied;
    int totalRead = copied;
    while(totalRead < n) {
      read = read4(dummy);
      copied = totalRead + read <= n? read: n - totalRead;
      for(int i = 0; i < copied; i++) {
        buf[idx++] = dummy[i];
      }
      totalRead += copied;
      if(read < 4) {
        break;
      }
    }
    buffer = new char[4];
    bufferLength = read - copied;
    for(int i = 0; i < bufferLength; i++) {
      buffer[i] = dummy[i + copied];
    }
    return totalRead;
  }

  private int initBuf(char[] buf, int n) {
    int read = Math.min(n, bufferLength);
    for(int i = 0; i < read; i++) {
      buf[i] = buffer[i];
    }
    return bufferLength;
  }
}