package com.mirak.leetcode.individual.easy;

/*
  Only for class compilation.
 */
class Reader4{
  int read4(char[] buf){
    return 0;
  }
}

public class ReadNCharactersGivenRead4 extends Reader4 {
  /**
   * @param buf Destination buffer
   * @param n   Maximum number of characters to read
   * @return    The number of characters read
   */
  public int read(char[] buf, int n) {
    int totalRead = 0;
    int idx = 0;
    char[] dummy = new char[4];
    while(totalRead < n) {
      int read = read4(dummy);
      int copy = totalRead + read <= n? read: n - totalRead;
      for(int i = 0; i < copy; i++) {
        buf[i + idx] = dummy[i];
      }
      totalRead += copy;
      if(read < 4) {
        break;
      }
    }
    return totalRead;
  }
}


