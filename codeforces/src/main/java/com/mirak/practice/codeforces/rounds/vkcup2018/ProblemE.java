package com.mirak.practice.codeforces.rounds.vkcup2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;

public class ProblemE {
  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    int[] nums = new int[n];
    for(int i = 0; i < n; i++){
      nums[i] = Integer.parseInt(strs[i]);
    }
    Stack<Integer> stack = new Stack<>();
    for(int i = 0; i < n; i++){
      stack.push(nums[i]);
      while(stack.size() > 1){
        int p1 = stack.pop();
        int p2 = stack.pop();
        if (p1 == p2){
          stack.push(p1 + 1);
        }else{
          stack.push(p2);
          stack.push(p1);
          break;
        }
      }
    }
    LinkedList<Integer> list = new LinkedList<>();
    StringBuilder builder = new StringBuilder();
    while (!stack.isEmpty()){
      list.addFirst(stack.pop());
    }
    System.out.println(list.size());
    while(!list.isEmpty()){
      if (builder.length() > 0){
        builder.append(" ");
      }
      builder.append(list.removeFirst());
    }
    System.out.println(builder);
  }
}