package com.mirak.leetcode.contests.contest110;

import java.util.*;

public class ReorderLogFiles {

  private class LetterLogEntry {

    String id;
    String log;

    LetterLogEntry(String id, String log) {
      this.id = id;
      this.log = log;
    }
  }

  public String[] reorderLogFiles(String[] logs) {
    ArrayList<LetterLogEntry> letterLogs = new ArrayList<>();
    for (String log : logs) {
      int firstSpaceIndex = log.indexOf(' ');

      if (Character.isAlphabetic(log.charAt(firstSpaceIndex + 1))) {
        String id = log.substring(0, firstSpaceIndex);
        String logEntry = log.substring(firstSpaceIndex + 1);
        letterLogs.add(new LetterLogEntry(id, logEntry));
      }
    }

    Collections.sort(letterLogs, new Comparator<LetterLogEntry>(){
      @Override
      public int compare(LetterLogEntry entry1, LetterLogEntry entry2) {
        int diff = entry1.log.compareTo(entry2.log);
        return diff == 0 ? entry1.id.compareTo(entry2.id) : diff;
      }
    });

    String[] result = new String[logs.length];
    for (int i = 0; i < letterLogs.size(); i++) {
      result[i] = letterLogs.get(i).id + " " + letterLogs.get(i).log;
    }

    int idx = letterLogs.size();
    for (int i = 0; i < logs.length; i++) {
      int fisrtSpaceIndex = logs[i].indexOf(' ');
      if (Character.isDigit(logs[i].charAt(fisrtSpaceIndex + 1))) {
        result[idx++] = logs[i];
      }
    }
    return result;
  }
}
