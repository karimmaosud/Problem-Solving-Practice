package com.mirak.practice.codeforces.rounds.regular.round468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ProblemC {
  public static void main(String[] args)throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    int[] nums = new int[n];
    Map<Integer, Integer> countMap = new HashMap<>();
    for(int i = 0; i < n; i++){
      nums[i] = Integer.parseInt(strs[i]);
      countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
    }
    if(countMap.size() == 1){
      printNums(nums, n);
      System.exit(0);
    }

    Arrays.sort(nums);

    int left = 0, right = n - 1;
    while(left < countMap.get(nums[0])){
      left++;
    }
    while(n - right - 1 < countMap.get(nums[n - 1])){
      right--;
    }

    if(countMap.size() == 2){
      if(nums[n - 1] - nums[0] == 1){
        printNums(nums, n);
        System.exit(0);
      }
    }


    int strikeOuter = n - Math.min(left, n - right - 1) * 2;
    int strikeInner = n - ((right - left + 1) / 2) * 2;
    boolean walkInner = strikeInner < strikeOuter;
    if(right < left && !walkInner){
      nums[left--]--;
      nums[right++]++;
    }
    while(right >= left && right < n && left >= 0){
      if(walkInner){
        nums[left]++;
        nums[right]--;
      }else if(left > 0 && right < n - 1) {
        nums[left - 1]++;
        nums[right + 1]--;
      }
      left += walkInner? 1: -1;
      right -= walkInner? 1: -1;
    }
    printNums(nums, Math.min(strikeInner, strikeOuter));
  }

  private static void printNums(int[] nums, int res) {
    System.out.println(res);
    for(int i = 0; i < nums.length; i++){
      System.out.print(nums[i] + " ");
    }
    System.out.println();
  }
}
