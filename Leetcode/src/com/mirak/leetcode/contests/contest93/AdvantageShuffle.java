package com.mirak.leetcode.contests.contest93;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AdvantageShuffle {

  private class Element {

    int value;
    int index;

    Element(int value, int index) {
      this.value = value;
      this.index = index;
    }
  }

  public int[] advantageCount(int[] A, int[] B) {
    ArrayList<Element> aList = new ArrayList<>();
    ArrayList<Element> bList = new ArrayList<>();
    addElements(A, aList);
    addElements(B, bList);
    int aIndex = 0;
    int bLeft = 0;
    int bRight = A.length - 1;
    int[] ret = new int[A.length];
    while (aIndex < A.length) {
      if (aList.get(aIndex).value > bList.get(bLeft).value) {
        // current lowest in A > current lowest in B
        // put that element in front of the B's element's index
        ret[bList.get(bLeft).index] = aList.get(aIndex).value;
        bLeft++;
      } else {
        ret[bList.get(bRight).index] = aList.get(aIndex).value;
        bRight--;
      }
      aIndex++;
    }
    return ret;
  }

  private void addElements(int[] numsArray, ArrayList<Element> list) {
    for (int i = 0; i < numsArray.length; i++) {
      list.add(new Element(numsArray[i], i));
    }

    Collections.sort(list, new Comparator<Element>() {
      @Override
      public int compare(Element e1, Element e2) {
        return e1.value - e2.value;
      }
    });
  }
}

