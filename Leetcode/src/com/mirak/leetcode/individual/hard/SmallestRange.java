package com.mirak.leetcode.individual.hard;

import java.util.*;

public class SmallestRange {

  private class ElementWrapper implements Comparable<ElementWrapper> {

    int num;
    int listIndex;
    int localIndex;

    ElementWrapper(int num, int listIndex, int localIndex) {
      this.num = num;
      this.listIndex = listIndex;
      this.localIndex = localIndex;
    }

    @Override
    public int hashCode() {
      return (num + "#" + listIndex + "#" + localIndex).hashCode();
    }

    @Override
    public boolean equals(Object o) {
      ElementWrapper that = (ElementWrapper) o;
      return num == that.num && listIndex == that.listIndex && localIndex == that.localIndex;
    }

    @Override
    public int compareTo(ElementWrapper that) {
      int diff = this.num - that.num;
      if (diff != 0) {
        return diff;
      }
      return this.listIndex - that.listIndex;
    }

    @Override
    public String toString() {
      return "list: " + listIndex + " - index: " + listIndex + " - value: " + num;
    }
  }

  public int[] smallestRange(List<List<Integer>> nums) {
    TreeSet<ElementWrapper> set = new TreeSet<>();
    for (int i = 0; i < nums.size(); i++) {
      set.add(new ElementWrapper(nums.get(i).get(0), i, 0));
    }
    int[] ret = {0, 1000000000};
    while (set.size() == nums.size()) {
      ElementWrapper lowest = set.first();
      ElementWrapper highest = set.last();
      if (highest.num - lowest.num < ret[1] - ret[0]) {
        ret[0] = lowest.num;
        ret[1] = highest.num;
      }
      set.remove(lowest);
      int listIndex = lowest.listIndex;
      int nextIndex = lowest.localIndex + 1;
      if (nextIndex < nums.get(listIndex).size()) {
        set.add(new ElementWrapper(nums.get(listIndex).get(nextIndex), listIndex, nextIndex));
      }
    }
    return ret;
  }
}
