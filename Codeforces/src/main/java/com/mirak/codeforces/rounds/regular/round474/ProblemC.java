package com.mirak.codeforces.rounds.regular.round474;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProblemC {
  private static final int MAXN = 10000;

  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    int X = Integer.parseInt(strs[0]);
    int d = Integer.parseInt(strs[1]);
    ArrayList<Long> list = new ArrayList<>();

    long start = 1;

    while(list.size() < MAXN && X > 0) {
      int maxPower = getMaxPower(X);
      if(!(list.size() + maxPower <= MAXN)) {
        list = null;
        break;
      }
      for(int i = 0; i < maxPower; i++){
        list.add(start);
      }
      start += d;
      X -= ((1 << maxPower) - 1);
    }
    if(list == null){
      System.out.println(-1);
      return;
    }
    System.out.println(list.size());
    System.out.print(list.get(0));
    for(int i = 1; i < list.size(); i++){
      System.out.print(" " + list.get(i));
    }
    System.out.println();
  }
  private static int getMaxPower(int X) {
    int low = 1;
    int high = 29;
    while(low <= high) {
      int mid = (low + high) / 2;
      int val = (1 << mid) - 1;
      if(X - val >= 0){
        low = mid + 1;
      }else{
        high = mid - 1;
      }
    }
    return high;
  }
}
