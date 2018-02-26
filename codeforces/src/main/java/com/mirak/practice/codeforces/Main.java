package com.mirak.practice.codeforces;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args){
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = 1000000007;
    for(int i = 2; i*i <= n; i++){
      if(n %i == 0){
        System.out.println("not prime");
        return;
      }
    }
    System.out.println("prime");

  }
}
