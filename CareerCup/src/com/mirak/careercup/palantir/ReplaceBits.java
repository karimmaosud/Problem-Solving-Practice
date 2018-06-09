package com.mirak.careercup.palantir;

public class ReplaceBits {
  public int replaceRangeBits(int n, int a, int b, int k) {
    if(b < a) {
      return -1;
    }
    // 1. take first (b-a) bits from k
    int d1 = ((1 << (b - a + 1)) -1);
    k &= d1;

    // 2. reset bits from position a to b in n.
    n &= ~(d1 << a);

    return n | (k << a);
  }
}
