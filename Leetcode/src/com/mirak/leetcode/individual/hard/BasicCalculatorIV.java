package com.mirak.leetcode.individual.hard;

import java.util.*;

public class BasicCalculatorIV {

  private class Term implements Comparable<Term> {

    String variable;
    int coefficient;

    Term(String variable, int coefficient) {
      this.variable = variable;
      this.coefficient = coefficient;
    }

    @Override
    public int compareTo(Term o) {
      if (this.variable.isEmpty()) {
        return 1;
      }
      if (o.variable.isEmpty()) {
        return -1;
      }

      int o1StarCount = this.variable.length() - this.variable.replaceAll("\\*", "").length();
      int o2StarCount = o.variable.length() - o.variable.replaceAll("\\*", "").length();
      int diff = o1StarCount - o2StarCount;
      return diff == 0 ? this.variable.compareTo(o.variable) : -1 * diff;
    }

    @Override
    public boolean equals(Object o) {
      return this.variable.equals(((Term) o).variable);
    }
  }

  private class Expression {

    ArrayList<Term> terms;

    Expression() {
      terms = new ArrayList<>();
    }

    Expression(String str, int coefficient) {
      terms = new ArrayList<>();
      terms.add(new Term(str, coefficient));
    }

    void addExpression(Expression addedExpression, int mul) {
      for (Term addedTerm : addedExpression.terms) {
        addTerm(addedTerm, mul);
      }
    }

    void addTerm(Term addedTerm, int mul) {
      addedTerm.coefficient *= mul;
      boolean overriden = false;
      for (Term term : terms) {
        if (term.equals(addedTerm)) {
          overriden = true;
          term.coefficient += addedTerm.coefficient;
          if (term.coefficient == 0) {
            terms.remove(term);
          }
          break;
        }
      }
      if (!overriden && addedTerm.coefficient != 0) {
        terms.add(addedTerm);
      }
    }

    void multiplyExpression(Expression multipliedExpression) {
      Expression result = new Expression();
      for (Term term : terms) {
        for (Term multipliedTerm : multipliedExpression.terms) {
          result.addTerm(new Term(getJoinedString(term.variable, multipliedTerm.variable),
              term.coefficient * multipliedTerm.coefficient), 1);
        }
      }

      this.terms = result.terms;
    }


    String getJoinedString(String left, String right) {
      if (left.length() == 0) {
        return right;
      }
      if (right.length() == 0) {
        return left;
      }
      String[] combined = (left + "*" + right).split("\\*");
      if (combined.length == 1) {
        return combined[0];
      }
      Arrays.sort(combined);
      return String.join("*", combined);
    }
  }

  public List<String> basicCalculatorIV(String expression, String[] evalvars,
      int[] evalints) {

    expression = substituteGivenVariables(evalvars, evalints, expression);

    Stack<Expression> expressionsStack = new Stack<>();
    Stack<Character> symbolsStack = new Stack<>();
    int idx = 0;
    while (idx < expression.length()) {
      char currentChar = expression.charAt(idx);
      if (isLetter(currentChar)) {
        int j = getVariableLastIndex(expression, idx);
        String variable = expression.substring(idx, j);
        expressionsStack.add(new Expression(variable, 1));
        idx = j;
        continue;
      }

      if (isDigit(currentChar) || (currentChar == '-' && isDigit(expression.charAt(idx + 1)))) {
        int mul = 1;
        if (currentChar == '-') {
          mul = -1;
          idx++;
        }
        int j = getNumberLastIndex(expression, idx);
        int number = mul * Integer.parseInt(expression.substring(idx, j));
        expressionsStack.add(new Expression("", number));
        idx = j;
        continue;
      }

      if (currentChar == '(' || currentChar == '*') {
        symbolsStack.push(currentChar);
      } else if (currentChar != ' ') {
        // currentChar is + or - or )
        evaluateExpressions(expressionsStack, symbolsStack, currentChar);
        if (currentChar != ')') {
          symbolsStack.add(currentChar);
        }
      }
      idx++;
    }
    evaluateExpressions(expressionsStack, symbolsStack, '(');
    // now stack should have only one expression.
    Expression finalExpression = expressionsStack.pop();
    ArrayList<Term> terms = finalExpression.terms;

    Collections.sort(terms);

    List<String> result = new LinkedList<>();
    for (Term term : terms) {
      if (term.coefficient == 0) {
        continue;
      }
      if (term.variable.length() == 0) {
        result.add("" + term.coefficient);
      } else {
        result.add(term.coefficient + "*" + term.variable);
      }
    }
    return result;
  }

  private String substituteGivenVariables(String[] evalVars, int[] evalInts,
      String expression) {
    Map<String, Integer> evalMap = new HashMap<>();
    for (int i = 0; i < evalVars.length; i++) {
      evalMap.put(evalVars[i], evalInts[i]);
    }
    StringBuilder builder = new StringBuilder();
    int idx = 0;
    while (idx < expression.length()) {
      if (isLetter(expression.charAt(idx))) {
        int j = idx;
        while (j < expression.length() && isLetter(expression.charAt(j))) {
          j++;
        }
        String variable = expression.substring(idx, j);
        if (evalMap.containsKey(variable)) {
          builder.append(evalMap.get(variable));
        } else {
          builder.append(variable);
        }
        idx = j;
      } else {
        builder.append(expression.charAt(idx++));
      }
    }
    return builder.toString();
  }

  private boolean isLetter(char a) {
    return Character.isLowerCase(a);
  }

  private boolean isDigit(char a) {
    return Character.isDigit(a);
  }

  private int getVariableLastIndex(String expression, int idx) {
    while (idx < expression.length() && isLetter(expression.charAt(idx))) {
      idx++;
    }
    return idx;
  }

  private int getNumberLastIndex(String expression, int idx) {
    while (idx < expression.length() && isDigit(expression.charAt(idx))) {
      idx++;
    }
    return idx;
  }

  private void evaluateExpressions(Stack<Expression> expressionsStack,
      Stack<Character> symbolsStack, char popper) {

    if (symbolsStack.isEmpty() || symbolsStack.peek() == '(') {
      return;
    }

    while (!symbolsStack.isEmpty() && symbolsStack.peek() != '(') {
      Expression right = expressionsStack.pop();
      Expression left = expressionsStack.pop();
      doOperation(left, right, symbolsStack.pop());
      expressionsStack.add(left);
    }

    if (!symbolsStack.isEmpty() && popper == ')') {
      // Peek must be ‘(’.
      symbolsStack.pop();
    }
  }

  private void doOperation(Expression left, Expression right, char op) {
    if (op == '+') {
      left.addExpression(right, 1);
    } else if (op == '-') {
      left.addExpression(right, -1);
    } else {
      left.multiplyExpression(right);
    }
  }
}
