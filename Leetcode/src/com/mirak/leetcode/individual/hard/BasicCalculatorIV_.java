package com.mirak.leetcode.individual.hard;

import java.util.*;
import java.util.stream.Collectors;

public class BasicCalculatorIV_ {

  private class Term implements Comparable<Term> {

    List<String> vars;
    int coefficient;

    Term() {
      vars = new ArrayList<>();
      coefficient = 0;
    }

    Term(String termStr, int coefficient) {
      this();
      this.vars.add(termStr);
      this.coefficient = coefficient;
    }

    Term(List<String> vars, int coefficient) {
      this.vars = vars;
      this.coefficient = coefficient;
    }


    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Term)) {
        return false;
      }
      Term that = (Term) o;
      return that.vars.equals(this.vars);
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      if (vars.size() > 0) {
        builder.append(vars.get(0));
      }
      for (int i = 1; i < vars.size(); i++) {
        builder.append("*").append(vars.get(i));
      }
      return builder.toString();
    }


    @Override
    public int compareTo(Term o) {
      String thisStr = this.toString();
      String that = o.toString();
      if (thisStr.isEmpty()) {
        return 1;
      } else if (that.isEmpty()) {
        return -1;
      }
      return thisStr.compareTo(that);
    }
  }

  private class Expression {

    ArrayList<Term> terms;

    Expression() {
      terms = new ArrayList<>();
    }

    Expression(String termStr, int coefficient) {
      this();
      terms.add(new Term(termStr, coefficient));
    }

    Expression(String termStr) {
      this(termStr, 1);
    }

    Expression(int coefficient) {
      this("", coefficient);
    }

    private void addTerm(Term term) {
      this.terms.add(term);
    }

    Expression add(Expression right) {

      for (Term rightTerm : right.terms) {
        Term term = searchTerm(rightTerm);
        if (term != null) {
          term.coefficient += rightTerm.coefficient;
        } else {
          this.terms.add(rightTerm);
        }
      }
      return this;
    }

    Expression subtract(Expression right) {
      for (Term term : right.terms) {
        term.coefficient *= -1;
      }
      return add(right);
    }

    Expression multiply(Expression right) {
      Expression result = new Expression();
      for (Term leftTerm : this.terms) {
        for (Term rightTerm : right.terms) {
          Term term = getMultipliedTerm(leftTerm, rightTerm);
          Term searchTerm = result.searchTerm(term);
          if (searchTerm == null) {
            result.addTerm(term);
          } else {
            searchTerm.coefficient += term.coefficient;
          }
        }
      }
      return result;
    }

    private Term searchTerm(Term rightTerm) {
      for (Term leftTerm : this.terms) {
        if (leftTerm.equals(rightTerm)) {
          return leftTerm;
        }
      }
      return null;
    }

    private Term getMultipliedTerm(Term left, Term right) {
      int coefficient = left.coefficient * right.coefficient;

      ArrayList<String> c = new ArrayList<>(left.vars);
      c.addAll(right.vars);

      List<String> vars = c.stream().filter(var -> !var.isEmpty()).collect(Collectors.toList());

      if (vars.size() == 0) {
        vars.add("");
      }

      Collections.sort(vars);
      return new Term(vars, coefficient);
    }
  }

  public List<String> basicCalculatorIV(String expression, String[] evalvars,
      int[] evalints) {
    return parseExpression(evaluateGivens(getSpaceAdjustedString(expression), evalvars, evalints));
  }

  private String evaluateGivens(String expression, String[] evalvars, int[] evalints) {
    String[] vals = expression.split(" ");
    Map<String, Integer> map = new HashMap<>();

    for (int i = 0; i < evalints.length; i++) {
      map.put(evalvars[i], evalints[i]);
    }

    StringBuilder builder = new StringBuilder();
    for (String val : vals) {
      if (map.containsKey(val)) {
        val = "" + map.get(val);
      }
      if (builder.length() > 0) {
        builder.append(" ");
      }
      builder.append(val);
    }
    return builder.toString();
  }

  private String getSpaceAdjustedString(String expression) {
    return expression.replaceAll("\\(", "( ").replaceAll("\\)", " )");
  }

  private List<String> parseExpression(String expression) {

    Stack<Expression> expressionStack = new Stack<>();
    Stack<Character> opStack = new Stack<>();

    String[] strs = expression.split(" ");
    for (String str : strs) {
      if (isVariable(str)) {
        // push a new expression with one term and 1 coefficient.
        expressionStack.push(new Expression(str));
      } else if (isNumber(str)) {
        // push a new expression with one term (empty string), and coefficient is the integer value of str.
        expressionStack.push(new Expression(Integer.parseInt(str)));
      } else if (isOp(str)) {
        opHandle(str.charAt(0), expressionStack, opStack);
      } else if (str.equals("(")) {
        opStack.push('(');
      } else {
        closeBracketHandle(expressionStack, opStack);
      }
    }
    while (!opStack.isEmpty()) {
      popOperation(expressionStack, opStack);
    }
    return expressionToList(expressionStack.pop());
  }

  private boolean isVariable(String str) {
    return Character.isAlphabetic(str.charAt(0));
  }

  private boolean isNumber(String str) {
    return Character.isDigit(str.charAt(0)) || str.length() > 1 && str.charAt(0) == '-' && Character
        .isDigit(str.charAt(1));
  }

  private boolean isOp(String str) {
    return str.equals("+") || str.equals("-") || str.equals("*");
  }

  private void opHandle(char op, Stack<Expression> expressionStack,
      Stack<Character> opStack) {
    while (!opStack.isEmpty() && !higherPrecedence(op, opStack.peek())) {
      popOperation(expressionStack, opStack);
    }
    opStack.push(op);
  }

  private void closeBracketHandle(Stack<Expression> expressionStack,
      Stack<Character> opStack) {
    while (opStack.peek() != '(') {
      popOperation(expressionStack, opStack);
    }
    opStack.pop();
  }

  private void popOperation(Stack<Expression> expressionStack, Stack<Character> opStack) {
    Expression right = expressionStack.pop();
    Expression left = expressionStack.pop();
    char poppedOp = opStack.pop();
    expressionStack.push(doOperation(left, right, poppedOp));
  }

  private boolean higherPrecedence(char leftOp, char rightOp) {
    if (rightOp == '(') {
      return true;
    }
    if (leftOp == '*') {
      return rightOp != '*';
    }
    return false;
  }

  private Expression doOperation(Expression left, Expression right, char op) {
    if (op == '+') {
      return left.add(right);
    } else if (op == '-') {
      return left.subtract(right);
    } else {
      return left.multiply(right);
    }
  }

  private List<String> expressionToList(Expression expression) {
    List<String> res = new ArrayList<>();

    Collections.sort(expression.terms, (t1, t2) ->  {
      int orderDiff = t1.vars.size() - t2.vars.size();
      if (orderDiff != 0) {
        return -1 * orderDiff;
      }
      return t1.compareTo(t2);
    });

    for (Term term : expression.terms) {
      if (term.coefficient == 0) {
        continue;
      }

      String termStr = term.toString();
      res.add(termStr.length() > 0 ? (term.coefficient + "*" + termStr) : ("" + term.coefficient));
    }
    return res;
  }
}
