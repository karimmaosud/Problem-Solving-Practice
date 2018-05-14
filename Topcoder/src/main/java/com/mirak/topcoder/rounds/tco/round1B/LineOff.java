package com.mirak.topcoder.rounds.tco.round1B;

import java.util.Stack;

public class LineOff {
  public int movesToDo(String points){
    Stack<Character> stack = new Stack<Character>();
    int ans = 0;
    for(char c: points.toCharArray()) {
      if(!stack.isEmpty() && stack.peek() == c) {
        ans++;
        stack.pop();
        continue;
      }
      stack.add(c);
    }
    return ans;
  }
}
