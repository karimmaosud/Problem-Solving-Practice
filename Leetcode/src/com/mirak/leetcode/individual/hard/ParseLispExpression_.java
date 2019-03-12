package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ParseLispExpression_ {

  private enum ExpressionType {
    ADD, MULT, LET, INTEGER, VARIABLE
  }

  public static int evaluate(String expression) {
    int[] closeParentheses = initCloseParentheses(expression);
    return parseExpression(expression, 0, new HashMap<>(), closeParentheses);
  }

  private static int parseExpression(String expression, int left,
      Map<String, Stack<Integer>> values,
      int[] closeParentheses) {
    ExpressionType type = getExpressionType(expression, left);
    switch (type) {
      case ADD:
        return parseAddExpression(expression, left + 5, values, closeParentheses);
      case MULT:
        return parseMultExpression(expression, left + 6, values, closeParentheses);
      case LET:
        return parseLetExpression(expression, left + 5, values, closeParentheses);
      case VARIABLE:
        return parseVariableExpression(expression, left, values);
      default:
        // INTEGER
        return parseIntegerExpression(expression, left);
    }
  }

  private static ExpressionType getExpressionType(String expression, int left) {
    if (isAddExpression(expression, left)) {
      return ExpressionType.ADD;
    }
    if (isMultExpression(expression, left)) {
      return ExpressionType.MULT;
    }
    if (isLetExpression(expression, left)) {
      return ExpressionType.LET;
    }
    if (isVariableExpression(expression, left)) {
      return ExpressionType.VARIABLE;
    }
    return ExpressionType.INTEGER;
  }

  private static boolean isAddExpression(String expression, int left) {
    return expression.indexOf("(add", left) == left;
  }

  private static boolean isMultExpression(String expression, int left) {
    return expression.indexOf("(mult", left) == left;
  }

  private static boolean isLetExpression(String expression, int left) {
    return expression.indexOf("(let", left) == left;
  }

  private static boolean isVariableExpression(String expression, int left) {
    return Character.isAlphabetic(expression.charAt(left));
  }

  private static int parseAddExpression(String expression, int left,
      Map<String, Stack<Integer>> values,
      int[] closeParentheses) {
    int separatorIndex =
        expression.charAt(left) == '(' ? closeParentheses[left] + 1 : expression.indexOf(' ', left);
    return parseExpression(expression, left, values, closeParentheses) + parseExpression(expression,
        separatorIndex + 1,
        values, closeParentheses);
  }

  private static int parseMultExpression(String expression, int left,
      Map<String, Stack<Integer>> values,
      int[] closeParentheses) {

    int separatorIndex =
        expression.charAt(left) == '(' ? closeParentheses[left] + 1 : expression.indexOf(' ', left);

    return parseExpression(expression, left, values, closeParentheses) * parseExpression(expression,
        separatorIndex + 1,
        values, closeParentheses);
  }

  private static int parseLetExpression(String expression, int left,
      Map<String, Stack<Integer>> values,
      int[] closeParentheses) {
    int runnerLeft = left;

    Map<String, Integer> pushCount = new HashMap<>();

    int letValue = Integer.MIN_VALUE;

    while (getExpressionType(expression, runnerLeft) == ExpressionType.VARIABLE) {

      int variableLastIndex = getVariableLastIndex(expression, runnerLeft);

      String variable = expression.substring(runnerLeft, variableLastIndex);

      if (expression.charAt(variableLastIndex) != ' ') {
        letValue = values.get(variable).peek();
        break;
      }

      pushCount.put(variable, pushCount.getOrDefault(variable, 0) + 1);

      int value = parseExpression(expression, variableLastIndex + 1, values, closeParentheses);
      values.putIfAbsent(variable, new Stack<>());
      values.get(variable).push(value);

      if (expression.charAt(variableLastIndex + 1) == '(') {
        runnerLeft = closeParentheses[variableLastIndex + 1] + 2;
      } else if (Character.isAlphabetic(expression.charAt(variableLastIndex + 1))) {
        runnerLeft = getVariableLastIndex(expression, variableLastIndex + 1) + 1;
      } else {
        runnerLeft = variableLastIndex + ("" + value).length() + 2;
      }
    }

    if (letValue == Integer.MIN_VALUE) {
      letValue = parseExpression(expression, runnerLeft, values, closeParentheses);
    }

    for (String key : pushCount.keySet()) {
      for (int i = 0; i < pushCount.get(key); i++) {
        values.get(key).pop();
      }
    }
    return letValue;
  }

  private static int parseVariableExpression(String expression, int left,
      Map<String, Stack<Integer>> values) {
    return values.get(expression.substring(left, getVariableLastIndex(expression, left))).peek();
  }

  private static int getVariableLastIndex(String expression, int left) {
    while (left < expression.length() && (Character.isAlphabetic(expression.charAt(left))
        || Character
        .isDigit(expression.charAt(left)))) {
      left++;
    }
    return left;
  }

  private static int parseIntegerExpression(String expression, int left) {
    int j = expression.charAt(left) == '-' ? left + 1 : left;
    while (j < expression.length() && Character.isDigit(expression.charAt(j))) {
      j++;
    }
    return Integer.parseInt(expression.substring(left, j));
  }

  private static int[] initCloseParentheses(String expression) {
    int[] closeParentheses = new int[expression.length()];
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < expression.length(); i++) {
      if (expression.charAt(i) == '(') {
        stack.push(i);
      } else if (expression.charAt(i) == ')') {
        closeParentheses[stack.pop()] = i;
      }
    }
    return closeParentheses;
  }

  public static void main(String[] args) {
    System.out.println(evaluate("(let x -2 y x y)"));
  }

}
