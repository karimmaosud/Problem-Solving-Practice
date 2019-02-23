package com.mirak.leetcode.individual.medium;

import java.util.*;

public class MyCalendar {

  private class Event implements Comparable<Event> {

    int start;
    int end;

    Event(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Event that) {
      int startDiff = this.start - that.start;
      if (startDiff != 0) {
        return startDiff;
      }
      return this.end - that.end;
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Event)) {
        return false;
      }
      Event that = (Event) o;
      return this.start == that.start && this.end == that.end;
    }

    @Override
    public int hashCode() {
      return (start + "#" + end).hashCode();
    }

    @Override
    public String toString() {
      return "start: " + start + ",end: " + end;
    }
  }

  private TreeSet<Event> set;

  public MyCalendar() {
    set = new TreeSet<>();
  }

  public boolean book(int start, int end) {
    Event newEvent = new Event(start, end);
    Event floor = set.floor(newEvent);
    Event ceil = set.ceiling(newEvent);
    if (floor == null && ceil == null) {
      set.add(newEvent);
      return true;
    }
    if (floor != null && newEvent.start < floor.end) {
      return false;
    }
    if (ceil != null && newEvent.end > ceil.start) {
      return false;
    }
    set.add(newEvent);
    return true;
  }

}
