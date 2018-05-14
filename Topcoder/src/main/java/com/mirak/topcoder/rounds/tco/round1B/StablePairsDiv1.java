package com.mirak.topcoder.rounds.tco.round1B;

import java.util.LinkedList;

public class StablePairsDiv1 {
  public int[] findMaxStablePairs(int n, int c, int k){
    LinkedList<Integer> resList = null;
    int[] empty = {};
    for(int y = n; y >= 1; y--) {
      for(int x = y - 1; x >= 1; x--) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.addFirst(y);
        list.addFirst(x);
        for(int i = 0; i < k - 1; i++) {
          if(!walk(list, c)){
            break;
          }
        }
        if(list.size() == 2 * k) {
          resList = list;
          break;
        }
      }
      if(resList != null){
        break;
      }
    }
    int[] res = new int[2 * k];
    if(resList == null){
      return empty;
    }
    int index = 0;
    while (!resList.isEmpty()) {
      res[index++] = resList.getFirst();
      resList.removeFirst();
    }
    return res;
  }

  private boolean walk(LinkedList<Integer> list, int c){
    if(list.getFirst() < 3) {
      return false;
    }
    int sum = list.getFirst() + list.get(1) - c;
    int upper = list.getFirst() - 1;
    if(sum <= 0 || sum > 2 * upper - 1){
      return false;
    }
    while(upper + 1 > sum){
      upper--;
    }
    if(upper < 2){
      return false;
    }
    int l = sum - upper;
    int r = upper;
    int inc = (r - l + 1) / 2;
    l += inc;
    r -= inc;
    if(!(l < r)){
      l--;
      r++;
    }
    list.addFirst(r);
    list.addFirst(l);
    return true;
  }
//  public static void main(String[] args) {
//    int [] res = findMaxStablePairs(64, 48, 3);
//    System.out.println(1);
//  }
}
