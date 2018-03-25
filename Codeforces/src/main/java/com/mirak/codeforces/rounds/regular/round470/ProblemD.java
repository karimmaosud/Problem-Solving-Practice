package com.mirak.codeforces.rounds.regular.round470;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemD {
  private static final int MAX_BIT_COUNT = 29;
  private static class Node{
    Node one;
    Node zero;
    int[] count;
    Node(){
      count = new int[2];
    }

    boolean isLeaf(){
      return count[0] == 0 && count[1] == 0;
    }
  }
  private static class Trie{
    Node root;
    Trie(){
      root = new Node();
    }

    void insertNum(int num) {
      Node current = root;
      for(int i = MAX_BIT_COUNT; i >= 0; i--){
        int idx = (num & (1 << i)) == 0? 0 : 1;
        current.count[idx]++;
        if(idx == 1){
          if(current.one == null){
            current.one = new Node();
          }
          current = current.one;
        }else{
          if(current.zero == null){
            current.zero = new Node();
          }
          current = current.zero;
        }
      }
    }

    int getMinimumNum(int num, int idx, Node current){
      if(current == null || current.isLeaf()){
        return num;
      }
      int bit = (num & (1 << idx)) == 0? 0 : 1;
      if(current.count[bit] > 0){
        return getMinimumNum(setZero(num, idx), idx - 1, bit == 1? current.one : current.zero);
      }else{
        return getMinimumNum(num | (1 << idx), idx - 1, bit == 1? current.zero: current.one);
      }
    }

    private int setZero(int num, int idx) {
      return num & (((1 << (MAX_BIT_COUNT + 1)) - 1) ^ (1 << idx));
    }

    void removeNumber(int num){
      Node current = root;
      for(int i = MAX_BIT_COUNT; i >= 0; i--){
        int idx = (num & (1 << i)) == 0? 0 : 1;
        current.count[idx]--;
        if(idx == 1){
          current = current.one;
        }else{
          current = current.zero;
        }
      }
    }
  }
  public static void main(String[] args)throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    String[] strs = reader.readLine().split(" ");
    int[] a = new int[n];
    for(int i = 0; i < n; i++){
      a[i] = Integer.parseInt(strs[i]);
    }
    int[] p = new int[n];
    strs = reader.readLine().split(" ");
    for(int i = 0; i < n; i++){
      p[i] = Integer.parseInt(strs[i]);
    }
    Trie trie = new Trie();
    for(int num: p){
      trie.insertNum(num);
    }
    int[] res = new int[n];
    for(int i = 0; i < n; i++){
      int minimumNum = trie.getMinimumNum(a[i], MAX_BIT_COUNT, trie.root);
      trie.removeNumber(a[i] ^ minimumNum);
      res[i] = minimumNum;
    }
    System.out.print(res[0]);
    for(int i = 1; i < n; i++){
      System.out.print(" " + res[i]);
    }
    System.out.println();
  }
}