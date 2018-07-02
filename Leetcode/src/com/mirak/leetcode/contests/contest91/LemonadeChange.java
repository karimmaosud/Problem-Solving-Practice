package com.mirak.leetcode.contests.contest91;

public class LemonadeChange {
  public boolean lemonadeChange(int[] bills) {
    int[] change = new int[3];
    for(int i = 0; i < bills.length; i++) {
      if(bills[i] == 5) {
        change[0]++;
      }else if(bills[i] == 10) {
        if(change[0] > 0) {
          change[0]--;
        }else {
          return false;
        }
        change[1]++;
      }else {
        if(change[0] == 0) {
          return false;
        }
        change[0]--;
        if(change[1] != 0) {
          change[1]--;
        }else if(change[0] < 2) {
          return false;
        }else {
          change[0] -= 2;
        }
      }
    }
    return true;
  }
}
