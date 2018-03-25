package com.mirak.google.kickstart;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProblemA {
  public static void main(String[] args) throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader("/Users/masoud/Downloads/A-large-practice.in"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/masoud/Desktop/out.txt"));
    StringBuilder builder = new StringBuilder();
    int T = Integer.parseInt(reader.readLine());
    for(int t = 0; t < T; t++){
      String str = reader.readLine();
      long num = Long.parseLong(str);
      builder.append("Case #").append(t + 1).append(": ").append(solve(num)).append('\n');
    }
    writer.write(builder.toString());
    writer.close();
  }
  private static long solve(long num){
    return Math.min(increment(num), decrement(num));
  }

  private static long increment(long num){
    long mul = 1L;
    long res = 0;
    while(containsOddDigit(num)){
      // add one or add (10 - digit)
      int digit = (int) (num % 10);
      long left = num / 10;
      if(containsOddDigit(left)){
        res += (mul * (10 - digit));
        num += (10 - digit);
      }else{
        res += mul;
        num += 1;
      }
      num /= 10;
      mul *= 10;
    }
    return res;
  }

  private static boolean containsOddDigit(long num){
    while(num > 0){
      byte digit = (byte)(num%10);
      if((digit & 1) == 1){
        return true;
      }
      num /= 10;
    }
    return false;
  }

  private static long decrement(long num){
    if(num < 10){
      return (num & 1) == 1? 1: 0;
    }

    long right = 0;
    long mul = 1;
    long rem = 0;
    while(containsOddDigit(num)){
      int digit = (int) (num % 10);
      right = (mul * digit) + right;
      rem += (mul * 9);
      mul *= 10;
      num /= 10;
    }
    rem /= 10;
    mul /= 10;
    if(mul == 0){
      return 0;
    }
    right = right % mul;
    long res = right + 1;
    while(containsOddDigit(rem)){
      res += (mul / 10);
      rem -= (mul / 10);
      mul /= 10;
    }
    return res;
  }
}
