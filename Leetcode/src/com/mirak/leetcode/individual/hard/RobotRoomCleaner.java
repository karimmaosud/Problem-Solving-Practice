package com.mirak.leetcode.individual.hard;

import java.util.*;

public class RobotRoomCleaner {

  // This is the robot's control interface.
  // You should not implement it, or speculate about its implementation
  interface Robot {

    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
  }

  private enum Direction {
    UP(0), RIGHT(1), DOWN(2), LEFT(3);

    int value;

    Direction(int value) {
      this.value = value;
    }

    public Direction next() {
      return values()[(ordinal() + 1) % values().length];
    }
  }

  public void cleanRoom(Robot robot) {
    Set<String> vis = new HashSet<>();
    vis.add("0#0");
    cleanAndMove(robot, Direction.UP, 0, 0, vis);
  }

  private void cleanAndMove(Robot robot, Direction robotDirection, int row, int col,
      Set<String> vis) {

    robot.clean();

    Direction nextDirection = robotDirection;
    for (int i = 0; i < 4; i++) {
      int row_ = getNextRow(row, nextDirection);
      int col_ = getNextCol(col, nextDirection);
      String mapped = row_ + "#" + col_;
      if (!vis.contains(mapped) && robot.move()) {
        vis.add(mapped);
        cleanAndMove(robot, nextDirection, row_, col_, vis);
        // reverse direction.
        robot.turnRight();
        robot.turnRight();
        // go back to parent.
        robot.move();
        // reverse direction again (get the in-order one).
        robot.turnRight();
        robot.turnRight();
      }
      robot.turnRight();
      nextDirection = nextDirection.next();
    }
  }

  private int getNextCol(int col, Direction direction) {
    switch (direction) {
      case RIGHT:
        return col + 1;
      case LEFT:
        return col - 1;
      default:
        return col;
    }
  }

  private int getNextRow(int row, Direction direction) {
    switch (direction) {
      case UP:
        return row + 1;
      case DOWN:
        return row - 1;
      default:
        return row;
    }
  }

}

