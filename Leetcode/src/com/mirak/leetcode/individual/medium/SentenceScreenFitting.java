package com.mirak.leetcode.individual.medium;

public class SentenceScreenFitting {

  public static int wordsTyping(String[] sentence, int rows, int cols) {

    int startWord = 0;
    int columnIndex = 0;
    int[] lengthSum = new int[sentence.length];
    initLengthSum(sentence, lengthSum);
    int ans = 0;
    for (int i = 0; i < rows; i++) {
      if (startWord == 0) {
        int sentenceInARow = getMaxSentenceInRow(columnIndex, cols, lengthSum);
        ans += sentenceInARow;
        if (sentenceInARow > 0) {
          columnIndex =
              columnIndex + sentenceInARow * (lengthSum[lengthSum.length - 1] + lengthSum.length);
          if (columnIndex >= cols) {
            columnIndex = 0;
            continue;
          }
        }
      }
      // endWord is the last word we can put into this row.
      int endWord = getEndWord(columnIndex, startWord, cols, lengthSum);
      if (endWord < startWord) {
        // you can't fit even the current word in that row, proceeding to next row.
        columnIndex = 0;
      } else {
        columnIndex = columnIndex + getSkippedLength(startWord, endWord, lengthSum) + 1;
        if (columnIndex >= cols) {
          columnIndex = 0;
        } else {
          // stay in the current row.
          i--;
        }
        startWord = endWord + 1;
        if (startWord == sentence.length) {
          startWord = 0;
          ans++;
        }
      }
    }
    return ans;
  }

  private static int getMaxSentenceInRow(int columnIndex, int cols, int[] lengthSum) {
    int low = 0;
    int high = 1000000;
    while (low <= high) {
      int mid = (low + high) / 2;
      int totalLength = mid * (lengthSum[lengthSum.length - 1] + lengthSum.length) - 1;
      if (totalLength <= cols - columnIndex) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return high;
  }

  private static int getSkippedLength(int startWord, int endWord, int[] lengthSum) {
    int sub = startWord == 0 ? 0 : lengthSum[startWord - 1];
    return lengthSum[endWord] - sub + (endWord - startWord);
  }

  private static void initLengthSum(String[] sentence, int[] lengthSum) {
    lengthSum[0] = sentence[0].length();
    for (int i = 1; i < sentence.length; i++) {
      lengthSum[i] = lengthSum[i - 1] + sentence[i].length();
    }
  }

  private static int getEndWord(int columnIndex, int startWord, int cols, int[] lengthSum) {
    int low = startWord;
    int high = lengthSum.length - 1;
    int sub = startWord == 0 ? 0 : lengthSum[startWord - 1];
    while (low <= high) {
      int mid = (low + high) / 2;
      int fittedLength = lengthSum[mid] - sub + (mid - startWord);
      if (fittedLength <= cols - columnIndex) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return high;
  }

  public static void main(String[] args) {
    String[] sentence = {"a", "bcd"};
    System.out.println(wordsTyping(sentence, 20000, 20000));
  }
}
