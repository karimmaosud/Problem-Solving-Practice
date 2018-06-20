package com.mirak.leetcode.individual.medium;

import java.util.Stack;

public class BasicCalculatorII {
  public static int calculate(String s) {
    Stack<Long> numsStack = new Stack<>();
    Stack<Character> opStack = new Stack<>();
    int idx = 0;
    while (idx < s.length()) {
      if (s.charAt(idx) == ' ') {
        idx++;
        continue;
      }
      if (Character.isDigit(s.charAt(idx))) {
        int j = idx + 1;
        while (j < s.length() && Character.isDigit(s.charAt(j))) {
          j++;
        }
        numsStack.push(Long.parseLong(s.substring(idx, j)));
        idx = j;
      } else if (s.charAt(idx) == '(') {
        opStack.push(s.charAt(idx));
        idx++;
      } else if (s.charAt(idx) == ')') {
        while (opStack.peek() != '(') {
          doStackOperation(numsStack, opStack);
        }
        opStack.pop();
        idx++;
      } else {
        while (!opStack.isEmpty() && !isHigherPrecedence(s.charAt(idx), opStack.peek())) {
          doStackOperation(numsStack, opStack);
        }
        opStack.push(s.charAt(idx));
        idx++;
      }
    }
    while (!opStack.isEmpty()) {
      doStackOperation(numsStack, opStack);
    }
    long ret = numsStack.pop();
    return (int) ret;
  }

  private static long doOperation(long a, long b, char op) {
    if (op == '+') {
      return a + b;
    }
    if (op == '-') {
      return a - b;
    }
    if(op == '*') {
      return a * b;
    }
    return a / b;
  }

  private static boolean isHigherPrecedence(char a, char b) {
    if(b == '(') {
      return true;
    }

    if(a == '+' || a == '-') {
      return false;
    }

    return b == '+' || b == '-';

  }

  private static void doStackOperation(Stack<Long> numsStack, Stack<Character> opStack) {
    long b = numsStack.pop();
    long a = numsStack.pop();
    numsStack.push(doOperation(a, b, opStack.pop()));
  }

  public static void main(String[] args) {
    System.out.println(calculate("0-2147483648"));
  }
}
