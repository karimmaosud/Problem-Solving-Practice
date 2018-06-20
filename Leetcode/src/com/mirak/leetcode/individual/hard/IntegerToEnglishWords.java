package com.mirak.leetcode.individual.hard;

public class IntegerToEnglishWords {

  public String numberToWords(int num) {
    int[] divisors = {1000000000, 1000000, 1000, 100};
    String[] majors = {"Billion", "Million", "Thousand", "Hundred"};
    String[] minors = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    String[] tiny = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    return getStringNumber(num, divisors, majors, minors, tiny);
  }

  private String getStringNumber(int num, int[] divisors, String[] majors, String[] minors, String[] tiny) {
    if(num < 20) {
      return tiny[num];
    }
    if(num < 100) {
      String ret = minors[num / 10 - 2];
      if(num % 10 > 0) {
        ret += " " + tiny[num % 10];
      }
      return ret;
    }

// num is greater than one hundred

    int divisor;
    for(divisor = 0; divisor < divisors.length; divisor++) {
      if(num / divisors[divisor] != 0) {
        break;
      }
    }

    String ret = getStringNumber(num / divisors[divisor], divisors, majors,minors, tiny) + " " + majors[divisor];
    if(num % divisors[divisor] != 0) {
      ret += " " + getStringNumber(num % divisors[divisor], divisors, majors, minors, tiny);
    }
    return ret;
  }

}

