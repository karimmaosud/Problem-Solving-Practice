package com.mirak.leetcode.individual.medium;

import java.util.LinkedList;

public class SnakeGame {

  /**
   * Initialize your data structure here.
   *
   * @param width - screen width
   * @param height - screen height
   * @param food - A list of food positions
   * E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0].
   */

  private int shift = 15;

  private boolean[][] vis;
  private LinkedList<Integer> snake;
  private int foodPointer;
  private int[][] food;

  private int score;

  public SnakeGame(int width, int height, int[][] food) {
    this.vis = new boolean[height][width];
    this.vis[0][0] = true;
    this.snake = new LinkedList<>();
    this.snake.addLast(0);
    this.foodPointer = 0;
    this.food = food;
    this.score = 0;
  }

  /**
   * Moves the snake.
   *
   * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
   * @return The game's score after the move. Return -1 if game over.
   * Game over when snake crosses the screen boundary or bites its body.
   */

  public int move(String direction) {
    int lastLocation = snake.getLast();

    int currentRow = getRow(lastLocation);
    int currentColumn = getColumn(lastLocation);

    int nextRow = getNextRow(currentRow, direction);
    int nextColumn = getNextColumn(currentColumn, direction);

    if (isGameOver(nextRow, nextColumn)) {
      return -1;
    }

    int nextLocation = makeCell(nextRow, nextColumn);
    if (isFoodCell(nextRow, nextColumn)) {
      foodPointer++;
      vis[nextRow][nextColumn] = true;
      snake.addLast(nextLocation);
      score++;
    } else {
      moveSnake(nextLocation);
    }
    return score;
  }

  private int getNextRow(int currentRow, String direction) {
    if (direction.equals("U")) {
      return currentRow - 1;
    } else if (direction.equals("D")) {
      return currentRow + 1;
    }
    return currentRow;
  }

  private int getNextColumn(int currentColumn, String direction) {
    if (direction.equals("L")) {
      return currentColumn - 1;
    } else if (direction.equals("R")) {
      return currentColumn + 1;
    }
    return currentColumn;
  }

  private boolean isGameOver(int row, int column) {
    return row < 0 || column < 0 || row == vis.length || column == vis[0].length
        || (!isHeadCell(row, column) && vis[row][column]);
  }

  private boolean isHeadCell(int row, int column) {
    int location = makeCell(row, column);
    return snake.getFirst() == location;
  }

  private boolean isFoodCell(int row, int column) {
    return foodPointer != food.length && food[foodPointer][0] == row
        && food[foodPointer][1] == column;
  }

  private int makeCell(int row, int column) {
    return row | (column << shift);
  }

  private int getRow(int location) {
    return location & ((1 << shift) - 1);
  }

  private int getColumn(int location) {
    return location >> shift;
  }

  private void moveSnake(int location) {
    vis[getRow(location)][getColumn(location)] = true;
    snake.addLast(location);
    int deletedLocation = snake.removeFirst();
    if (deletedLocation != location) {
      vis[getRow(deletedLocation)][getColumn(deletedLocation)] = false;
    }
  }
}
