package com.mirak.codeforces.rounds.regular.round476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProblemC {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] strs = reader.readLine().split(" ");
    long n = Long.parseLong(strs[0]);
    long k = Long.parseLong(strs[1]);
    long m = Long.parseLong(strs[2]);
    long d = Long.parseLong(strs[3]);
    System.out.println(getMax(n, k, d, m));
  }

  private static long getMax(long n, long k, long d, long m) {
    long low = findXLowerBound(n, k, d, m);
    long high = m;
    while(low <= high) {

      ArrayList<Long> vals = new ArrayList<>();

      long mid = (low + high) / 2;

      if(mid - 1 >= low){
        vals.add((mid - 1) * getCount(mid - 1, n, k));
      }

      vals.add(mid * getCount(mid, n, k));

      if(mid + 1 <= high) {
        vals.add((mid + 1) * getCount(mid + 1, n, k));
      }

      if(vals.size() == 1){
        return mid * getCount(mid, n, k);
      }

      boolean increasing = allIncreasing(vals);
      boolean decreasing = allDecreasing(vals);

      if(increasing) {
        low = mid;
      }else if(decreasing){
        high = mid;
      }else{
        return vals.get(1);
      }
    }
    return Math.max(low * getCount(low, n, k), high * getCount(high, n, k));
  }

  private static long findXLowerBound(long n, long k, long d, long m){
    long low = 1;
    long high = m;
    while(low <= high) {
      long mid = (low + high) / 2;
      long count = getCount(mid, n, k);
      if(count <= d){
        high = mid - 1;
      }else{
        low = mid + 1;
      }
    }
    return low;
  }

  private static long getCount(long x, long n, long k) {
    return n / (x * k) + (n % (x * k) != 0? 1: 0);
  }

  private static boolean allIncreasing(ArrayList<Long> vals){
    for(int i = 1; i < vals.size(); i++) {
      if(vals.get(i) < vals.get(i - 1)){
        return false;
      }
    }
    return true;
  }

  private static boolean allDecreasing(ArrayList<Long> vals){
    for(int i = 1; i < vals.size(); i++) {
      if(vals.get(i) > vals.get(i - 1)){
        return false;
      }
    }
    return true;
  }
}
