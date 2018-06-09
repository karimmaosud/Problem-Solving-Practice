package com.mirak.hackercup.round2015.round2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class LazySort {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("/Users/masoud/Desktop/lazy_sort.txt"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    int T = Integer.parseInt(reader.readLine());
    String[] strs;
    for(int t = 1; t <= T; t++) {
      int n = Integer.parseInt(reader.readLine());
      strs = reader.readLine().split(" ");
      int[] nums = new int[n];
      for(int i = 0 ; i < n; i++) {
        nums[i] = Integer.parseInt(strs[i]);
      }

      writer.write("Case #" + t + ": " + canSort(nums) + "\n");
    }
    reader.close();
    writer.close();
  }

  private static void insertElement(LinkedList<Integer> list, int element) {
    if(list.isEmpty() || element < list.getFirst()) {
      list.addFirst(element);
    } else {
      list.addLast(element);
    }
  }

  private static boolean comesBefore(int element, LinkedList<Integer> list) {
    return list.getFirst() - element == 1;
  }

  private static boolean comesAfter(int element, LinkedList<Integer> list) {
    return element - list.getLast() == 1;
  }

  private static String canSort(int[] nums) {
    LinkedList<Integer> list = new LinkedList<>();
    list.addLast(nums[0]);
    String res = walk(list, nums,1, nums.length - 1);
    if(res.equals("no")) {
      list = new LinkedList<>();
      list.addLast(nums[nums.length - 1]);
      res = walk(list, nums, 0, nums.length - 2);
    }
    return res;
  }

  private static String walk(LinkedList<Integer> list, int[] nums, int i, int j) {
    while(i <= j) {
      if(comesBefore(nums[i], list)) {
        list.addFirst(nums[i++]);
      }else if(comesAfter(nums[i], list)) {
        list.addLast(nums[i++]);
      }else if(comesBefore(nums[j], list)){
        list.addFirst(nums[j--]);
      }else if(comesAfter(nums[j], list)) {
        list.addLast(nums[j--]);
      }else{
        return "no";
      }
    }
    return "yes";
  }
}
