package com.mirak.leetcode.individual.hard;

import java.util.*;

public class ParseLispExpression {

  private enum ExpressionType {
    ADD, MUL, LET, VARIABLE, INTEGER
  }

  public int evaluate(String expression) {
    if (expression.length() == 0) {
      return 0;
    }
    // close holds the close parenthesis of each open parenthesis (if any).
    int[] close = new int[expression.length()];
    init(expression, close);
    Map<String, Stack<Integer>> variableValues = new HashMap<>();
    return parseExpression(expression, 0, expression.length() - 1, close, variableValues);
  }

  private ExpressionType getExpressionType(String expression, int l) {
    if (isAddExpression(expression, l)) {
      return ExpressionType.ADD;
    }
    if (isMulExpression(expression, l)) {
      return ExpressionType.MUL;
    }
    if (isLetExpression(expression, l)) {
      return ExpressionType.LET;
    }
    if (isVariableExpression(expression, l)) {
      return ExpressionType.VARIABLE;
    }
    return ExpressionType.INTEGER;
  }

  private int parseExpression(String expression, int l, int r, int[] close,
      Map<String, Stack<Integer>> variableValues) {

    ExpressionType expressionType = getExpressionType(expression, l);

    switch (expressionType) {
      case ADD:
        return parseAddExpression(expression, close, l + 5, r - 1, variableValues);
      case MUL:
        return parseMulExpression(expression, close, l + 6, r - 1, variableValues);
      case LET:
        return parseLetExpression(expression, close, l + 5, r - 1, variableValues);
      case VARIABLE:
        return parseVariableExpression(expression, l, r, variableValues);
      default:
        return parseIntegerExpression(expression, l, r);
    }
  }

  private int parseAddExpression(String expression, int[] close, int l, int r,
      Map<String, Stack<Integer>> variableValues) {

    int r_ = getExpressionEnd(expression, l, close);
    int left = parseExpression(expression, l, r_, close, variableValues);
    int right = parseExpression(expression, r_ + 2, r, close, variableValues);
    return left + right;

  }

  private int parseMulExpression(String expression, int[] close, int l, int r,
      Map<String, Stack<Integer>> variableValues) {
    int r_ = getExpressionEnd(expression, l, close);
    int left = parseExpression(expression, l, r_, close, variableValues);
    int right = parseExpression(expression, r_ + 2, r, close, variableValues);
    return left * right;
  }

  private int parseLetExpression(String expression, int[] close, int l, int r,
      Map<String, Stack<Integer>> variableValues) {

    Set<String> vars = new HashSet<>();
    // TODO: update l.
    int ret = 0;
    boolean valueSet = false;
    while (isVariableExpression(expression, l)) {

      String variable = expression.substring(l, getVariableEndIndex(expression, l) + 1);

      int valueLeftIndex = l + variable.length() + 1;
      if (valueLeftIndex > r) {
        ret = variableValues.get(variable).peek();
        valueSet = true;
        break;
      }
      int valueRightIndex = getExpressionEnd(expression, valueLeftIndex, close);

      int value = parseExpression(expression, valueLeftIndex, valueRightIndex, close,
          variableValues);

      variableValues.putIfAbsent(variable, new Stack<>());
      if (vars.contains(variable)) {
        variableValues.get(variable).pop();
      }
      variableValues.get(variable).push(value);
      vars.add(variable);
      l = valueRightIndex + 2;
    }
    if (!valueSet) {
      ret = parseExpression(expression, l, r, close, variableValues);
    }
    for (String variable : vars) {
      variableValues.get(variable).pop();
    }
    return ret;
  }


  private int parseVariableExpression(String expression, int l, int r,
      Map<String, Stack<Integer>> variableValues) {
    return variableValues.get(expression.substring(l, r + 1)).peek();
  }

  private int parseIntegerExpression(String expression, int l, int r) {
    return Integer.parseInt(expression.substring(l, r + 1));
  }

  private boolean isAddExpression(String expression, int l) {
    return expression.charAt(l) == '(' && matchWordFromIndex(expression, l + 1, "add");
  }

  private boolean isMulExpression(String expression, int l) {
    return expression.charAt(l) == '(' && matchWordFromIndex(expression, l + 1, "mult");
  }

  private boolean isLetExpression(String expression, int l) {
    return expression.charAt(l) == '(' && matchWordFromIndex(expression, l + 1, "let");
  }

  private boolean isVariableExpression(String expression, int l) {
    return Character.isAlphabetic(expression.charAt(l));
  }

  private boolean matchWordFromIndex(String expression, int index, String word) {
    for (int i = 0; i < word.length(); i++) {
      if (index + i == expression.length() || expression.charAt(index + i) != word.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  private void init(String expression, int[] close) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < expression.length(); i++) {
      if (expression.charAt(i) == '(') {
        stack.add(i);
      } else if (expression.charAt(i) == ')') {
        close[stack.pop()] = i;
      }
    }
  }

  private int getVariableEndIndex(String expression, int l) {
    while (expression.charAt(l) != ')' && expression.charAt(l) != ' ') {
      l++;
    }
    return l - 1;
  }

  private int getExpressionEnd(String expression, int l, int[] close) {
    return expression.charAt(l) == '(' ? close[l] : expression.indexOf(' ', l) - 1;
  }
}
