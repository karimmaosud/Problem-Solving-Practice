package com.mirak.codeforces.rounds.regular.round470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemC {
  public static void main(String[] args)throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    int[] v = new int[n];
    for(int i = 0; i < n; i++){
      v[i] = Integer.parseInt(strs[i]);
    }
    strs = reader.readLine().split(" ");
    long[] t = new long[n];
    long[] sum = new long[n];
    for(int i = 0; i < n; i++){
      t[i] = Long.parseLong(strs[i]);
      sum[i] = t[i];
      if(i > 0){
        sum[i] += sum[i - 1];
      }
    }
    int[] runnerUpdate = new int[n + 1];
    long[] remainderUpdate = new long[n + 1];
    for(int i = 0; i < n; i++){
      if(t[i] > v[i]){
        remainderUpdate[i] += v[i];
        continue;
      }
      int idx = binSearch(v[i], i, sum);
      runnerUpdate[i] += 1;
      runnerUpdate[idx] -= 1;
      long rem = sum[idx - 1];
      if(i > 0){
        rem -= sum[i - 1];
      }
      remainderUpdate[idx] += v[i] - (int) rem;
    }
    for(int i = 0; i < n; i++){
      if(i > 0){
        runnerUpdate[i] += runnerUpdate[i - 1];
      }
      long res = 1L * t[i] * runnerUpdate[i] + remainderUpdate[i];
      System.out.print(res + " ");
    }
    System.out.println();
  }

  private static int binSearch(int value, int from, long[] sum){
    long subtract = 0;
    if(from > 0){
      subtract = sum[from - 1];
    }
    int low = from;
    int high = sum.length - 1;
    while(low <= high){
      int mid = (low + high) / 2;
      long consecutiveSum = sum[mid] - subtract;
      if(consecutiveSum <= value){
        low = mid + 1;
      }else{
        high = mid - 1;
      }
    }
    return low;
  }
}
