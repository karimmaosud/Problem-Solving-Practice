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

  private int score;
  private LinkedList<int[]> snake;
  private int foodCursor;
  private boolean[][] vis;
  private int n, m;
  private int[][] food;

  public SnakeGame(int width, int height, int[][] food) {
    score = 0;
    snake = new LinkedList<>();
    snake.addLast(new int[]{0, 0});
    foodCursor = 0;
    n = height;
    m = width;
    vis = new boolean[n][m];
    vis[0][0] = true;

    this.food = food;
  }

  /**
   * Moves the snake.
   *
   * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
   * @return The game's score after the move. Return -1 if game over.
   * Game over when snake crosses the screen boundary or bites its body.
   */
  public int move(String direction) {
    int[] nextCell = getNextCell(direction);

    if (isBorderCell(nextCell)) {
      return -1;
    }

    snake.addLast(nextCell);
    vis[nextCell[0]][nextCell[1]] = true;

    if (foodCell(nextCell)) {
      foodCursor++;
      score++;
    } else {
      int[] tailCell = snake.removeFirst();
      if (!(tailCell[0] == nextCell[0] && tailCell[1] == nextCell[1])) {
        vis[tailCell[0]][tailCell[1]] = false;
      }
    }
    return score;
  }

  private int[] getNextCell(String direction) {
    int[] snakeHead = snake.getLast();
    switch (direction) {
      case "U":
        return new int[]{snakeHead[0] - 1, snakeHead[1]};
      case "D":
        return new int[]{snakeHead[0] + 1, snakeHead[1]};
      case "R":
        return new int[]{snakeHead[0], snakeHead[1] + 1};
      default:
        return new int[]{snakeHead[0], snakeHead[1] - 1};
    }
  }

  private boolean foodCell(int[] nextCell) {
    return foodCursor < food.length && food[foodCursor][0] == nextCell[0]
        && food[foodCursor][1] == nextCell[1];
  }

  private boolean isBorderCell(int[] nextCell) {
    if (nextCell[0] < 0 || nextCell[1] < 0 || nextCell[0] == n || nextCell[1] == m) {
      return true;
    }
    if (vis[nextCell[0]][nextCell[1]]) {
      return !(nextCell[0] == snake.getFirst()[0] && nextCell[1] == snake.getFirst()[1]);
    }
    return false;
  }
}