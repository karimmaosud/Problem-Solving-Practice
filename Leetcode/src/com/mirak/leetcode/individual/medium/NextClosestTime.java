package com.mirak.leetcode.individual.medium;

import java.util.HashSet;
import java.util.Set;

public class NextClosestTime {

  public String nextClosestTime(String time) {

    Set<Character> chars = new HashSet<>();
    for(char a: time.toCharArray()) {
      if(Character.isDigit(a)) {
        chars.add(a);
      }
    }
    int minAfter = Integer.MAX_VALUE;
    int minBefore = Integer.MAX_VALUE;
    int originalTime = integerTime(time);
    for(char h1: chars) {
      for(char h2: chars) {
        int hour = (h1 - '0') * 10 + (h2 - '0');
        if(hour >= 24){
          continue;
        }
        for(char m1: chars) {
          for(char m2: chars) {
            int minute = (m1 - '0') * 10 + (m2 - '0');
            if(minute >= 60) {
              continue;
            }
            int currentTime = hour * 60 + minute;
            if(currentTime > originalTime) {
              minAfter = Math.min(minAfter, currentTime);
            }else{
              minBefore = Math.min(minBefore, currentTime);
            }
          }
        }
      }
    }
    return minAfter == Integer.MAX_VALUE? stringTime(minBefore): stringTime(minAfter);
  }

  private int integerTime(String time) {
    String[] splitted = time.split(":");
    int hours = Integer.parseInt(splitted[0]);
    int minutes = Integer.parseInt(splitted[1]);
    if(hours >= 24 || minutes >= 60) {
      return Integer.MAX_VALUE;
    }
    return hours * 60 + minutes;
  }

  private String stringTime(int time) {
    int minutes = time % 60;
    int hours = (time - minutes) / 60;
    String hoursString = hours < 10? "0" + hours: "" + hours;
    String minutesString = minutes < 10? "0" + minutes: "" + minutes;
    return new StringBuilder(hoursString).append(":").append(minutesString).toString();
  }
}
