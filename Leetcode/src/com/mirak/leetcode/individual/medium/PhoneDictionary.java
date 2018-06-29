package com.mirak.leetcode.individual.medium;

import java.util.HashSet;
import java.util.Set;

class PhoneDirectory {

  /** Initialize your data structure here
   @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
  Set<Integer> set;
  public PhoneDirectory(int maxNumbers) {
    set = new HashSet<>();
    for(int i = 0; i <maxNumbers; i++) {
      set.add(i);
    }
  }

  /** Provide a number which is not assigned to anyone.
   @return - Return an available number. Return -1 if none is available. */
  public int get() {
    if (set.size() == 0) {
      return -1;
    }
    int ret = set.iterator().next();
    set.remove(ret);
    return ret;
  }

  /** Check if a number is available or not. */
  public boolean check(int number) {
    return set.contains(number);
  }

  /** Recycle or release a number. */
  public void release(int number) {
    set.add(number);
  }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */

