package com.mirak.google.codejam.cg2018.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class TroubleSort {
  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder builder = new StringBuilder();
    int T = Integer.parseInt(reader.readLine());
    for(int t = 1; t <= T; t++) {
      int n = Integer.parseInt(reader.readLine());
      String[] strs = reader.readLine().split(" ");
      int[] nums = new int[n];
      for(int i = 0; i < n; i++) {
        nums[i] = Integer.parseInt(strs[i]);
      }
      ArrayList<Integer> oddElements = new ArrayList<>();
      ArrayList<Integer> evenElements = new ArrayList<>();
      for(int i = 0; i < n; i++) {
        if((i & 1) == 0){
          evenElements.add(nums[i]);
        }else{
          oddElements.add(nums[i]);
        }
      }
      Collections.sort(oddElements);
      Collections.sort(evenElements);
      int[] res = new int[n];
      int oddPointer = 0, evenPointer = 0;
      for(int i = 0; i < n; i++){
        if((i & 1) == 0){
          res[i] = evenElements.get(evenPointer++);
        }else{
          res[i] = oddElements.get(oddPointer++);
        }
      }
      int idx = -1;
      for(int i = 0; i < n - 1; i++) {
        if(res[i] > res[i + 1]){
          idx = i;
          break;
        }
      }
      builder.append("Case #").append(t).append(": ").append(getResString(idx)).append('\n');
    }
    System.out.print(builder);
  }
  private static String getResString(int idx) {
    return idx == -1? "OK": "" + idx;
  }
}
