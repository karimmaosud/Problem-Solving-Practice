package com.mirak.practice.codeforces.rounds.round465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemD {
  private static final long mod = 1000000007;

  private static class MathObject{
    long a;
    long b;
    MathObject(long a, long b){
      this.a = a%mod;
      this.b = b%mod;
    }

    private MathObject add(MathObject that){
      return new MathObject(((this.a * that.b)%mod + (this.b * that.a)%mod)%mod,
          (this.b * that.b)%mod);
    }

    private MathObject multiply(MathObject that){
      return new MathObject((this.a * that.a)%mod, (this.b * that.b)%mod);
    }
  }

  private static final MathObject one = new MathObject(1, 1);
  private static final MathObject zero = new MathObject(0, 1);

  private static MathObject solve(int i, int[] a, int [] b, int m){
    if(i == a.length){
      return zero;
    }
    if(a[i] == 0 && b[i] == 0){
      return new MathObject(1, m).multiply(solve(i + 1, a, b, m)).add(new MathObject(m - 1, 2 * m));
    }
    if(a[i] == 0 && b[i] != 0){
      return new MathObject(1, m).multiply(solve(i + 1, a, b, m)).add(new MathObject(m - b[i], m));
    }
    if(a[i] != 0 && b[i] == 0){
      return new MathObject(1, m).multiply(solve(i + 1, a, b, m)).add(new MathObject(a[i] - 1, m));
    }
    return a[i] > b[i]? one: b[i] > a[i]? zero: solve(i + 1, a, b, m);
  }

  public static void main(String[] args)throws IOException{
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int[] a = parseToInt(reader.readLine().split(" "));
    int n = a[0];
    int m = a[1];
    a = parseToInt(reader.readLine().split(" "));
    int[] b = parseToInt(reader.readLine().split(" "));

    MathObject res = solve(0, a, b, m);
    long multiplicativeInverse = modularExponentiation(res.b, (int)mod - 2);
    System.out.println((res.a * multiplicativeInverse)%mod);
  }


  private static int[] parseToInt(String[] strs){
    int[] nums = new int[strs.length];
    for(int i = 0; i < strs.length; i++){
      nums[i] = Integer.parseInt(strs[i]);
    }
    return nums;
  }

  private static long modularExponentiation(long n, int p){
    long res = 1;
    long base = n % mod;
    while(p > 0){
      if((p&1) != 0){
        res = (res%mod * base%mod)%mod;
      }
      p >>= 1;
      base = (base%mod * base%mod)%mod;
    }
    return res;
  }

}
