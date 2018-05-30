package com.mirak.codeforces.rounds.regular.round485;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ProblemA {
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(reader.readLine());
    Set<String> set = new HashSet<>();
    set.add("Power");
    set.add("Time");
    set.add("Space");
    set.add("Soul");
    set.add("Reality");
    set.add("Mind");
    Map<String, String> map = new HashMap<>();
    map.put("purple", "Power");
    map.put("green", "Time");
    map.put("blue", "Space");
    map.put("orange", "Soul");
    map.put("red", "Reality");
    map.put("yellow", "Mind");
    for(int i = 0; i < n; i++) {
      String str = reader.readLine();
      set.remove(map.get(str));
    }
    System.out.println(set.size());
    for(String str: set) {
      System.out.println(str);
    }
  }
}
