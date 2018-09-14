package com.mirak.leetcode.contests.contest96;

public class DecodedStringAtIndex {

  private class MyObject {

    String s;
    int r;
    MyObject object;

    long length;

    long lengthWithoutRepitition = 0;

    MyObject(String s, int r) {
      this.s = s;
      this.r = r;
      this.length = s.length() * r;
      this.lengthWithoutRepitition = s.length();
    }

    MyObject(MyObject object, int r) {
      this.object = object;
      this.r = r;
      this.length = object.getLength() * r;
      this.lengthWithoutRepitition = object.getLength();
    }

    MyObject(MyObject object, String s, int r) {
      this.object = object;
      this.s = s;
      this.r = r;
      this.length = (object.getLength() + s.length()) * r;
      this.lengthWithoutRepitition = object.getLength() + s.length();
    }

    void multiply(int r) {
      this.r *= r;
      this.length *= r;
    }

    long getLength() {
      return this.length;
    }

    long getLengthWithoutRepitition() {
      return this.lengthWithoutRepitition;
    }
  }

  public String decodeAtIndex(String S, int K) {
    int idx = 0;
    StringBuilder builder = new StringBuilder();
    MyObject runnerObject = null;
    while (idx < S.length()) {
      if (Character.isDigit(S.charAt(idx))) {
        if (builder.length() != 0) {
          if (runnerObject == null) {
            runnerObject = new MyObject(builder.toString(), S.charAt(idx) - '0');
          } else {
            runnerObject = new MyObject(runnerObject, builder.toString(), S.charAt(idx) - '0');
          }
          builder = new StringBuilder();
        } else {
          runnerObject.multiply(S.charAt(idx) - '0');
        }
      } else {
        builder.append(S.charAt(idx));
      }
      idx++;
    }

    if (builder.length() > 0) {
      if (runnerObject == null) {
        runnerObject = new MyObject(builder.toString(), 1);
      } else {
        runnerObject = new MyObject(runnerObject, builder.toString(), 1);
      }
    }
    return "" + solve(runnerObject, K);
  }

  private char solve(MyObject runnerObject, int k) {

    long rep = (long) k / runnerObject.getLengthWithoutRepitition();
    int k_ = (int) ((long) k - rep * runnerObject.getLengthWithoutRepitition());
    if (k_ == 0) {
      k_ = (int) ((long) k - (rep - 1) * runnerObject.getLengthWithoutRepitition());
    }

    if (runnerObject.object == null) {
      return runnerObject.s.charAt(k_ - 1);
    }

    if (runnerObject.s == null) {
      // a string repeated r times.
      return solve(runnerObject.object, k_);
    }

    if (k_ > runnerObject.object.getLength()) {
      return runnerObject.s.charAt((int) (k_ - runnerObject.object.getLength() - 1));
    }

    return solve(runnerObject.object, k_);
  }
}
