/**
 * Authors: Matthew Hall & Ibraheem Amir Elmasri
 * Assignment 1
 * COP3530
 *
 * This program will determine if the equation passed in from the user
 * is balanced. If it is then we will take the infix equation and convert
 * it to a postfix equation. From there we will evaluate it using a stack,
 * and return the last value on the stack.
 *
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class Arithmetic<Static> extends RPN {

    String temp = "";
    static String postFix;
    static ArrayList<Collection> beforeEVAL;
    private final String MULTIPLY = "*";
    private final String DIVIDE = "/";
    private final String SUBTRACT = "-";
    private final String ADD = "+";

    public Arithmetic(String s) {
        temp += "( " + s + " )";

    }
    /**
     * This method is made to see if the equation is balanced
     * meaning if the parentheses are where they should be. The
     * very first thing needed to do is surround the passed in equation
     * in a set of parentheses, that way if the equation is balanced or
     * not we won't receive any errors because there will always be a '('.
     *
     * @return
     * This returns a boolean value, of either true or false. True if
     * the equation is balance and flase if not.
     */
    public boolean isBalance() {

        String postFixExpression = "";
        Stack<Character> balancingStack = new Stack<Character>();

        for (int i = 0; i < temp.length(); i++) {
            if (Character.isDigit(temp.charAt(i))) {
                postFixExpression += temp.charAt(i);
            }
            if (temp.charAt(i) == '(') {
                balancingStack.push(temp.charAt(i));
            }
            if ((temp.charAt(i) == '-') || (temp.charAt(i) == '+')) {
                if (balancingStack.isEmpty()) {
                    balancingStack.push(temp.charAt(i));
                } else if (balancingStack.peek().equals('+') || balancingStack.peek().equals('-')) {
                    postFixExpression = postFixExpression + temp.charAt(i) + " ";
                } else {
                    balancingStack.push(temp.charAt(i));
                }
            }

            if ((temp.charAt(i) == '*') || (temp.charAt(i) == '/')) {
                if (balancingStack.isEmpty()) {
                    balancingStack.push(temp.charAt(i));
                } else if (balancingStack.peek().equals('*') || balancingStack.peek().equals('/')) {
                    postFixExpression += temp.charAt(i);
                } else if (balancingStack.peek().equals('+') || balancingStack.peek().equals('-')) {
                    postFixExpression += balancingStack.pop() + " ";
                    balancingStack.push(temp.charAt(i));
                } else {
                    balancingStack.push(temp.charAt(i));
                }
            }
            if (temp.charAt(i) == ')') {

                balancingStack.push(temp.charAt(i));

                while (balancingStack.isEmpty() || balancingStack.peek() != '(') {
                    if (balancingStack.isEmpty()) {
                        return false;
                    }
                    if (balancingStack.peek().equals('*') || balancingStack.peek().equals('/')) {
                        postFixExpression += balancingStack.pop();
                    } else if (balancingStack.peek().equals('-') || balancingStack.peek().equals('+')) {
                        postFixExpression += balancingStack.pop();
                    } else {
                        if (!balancingStack.isEmpty()) {
                            balancingStack.pop();
                        }
                    }
                }
                if (!balancingStack.isEmpty()) {
                    balancingStack.pop();
                }
            }
        }
        if (balancingStack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This postfixExpression method organizes the equation into readable
     * terms for which the computer or stack can understand. This method
     * uses the infix to postfix logic. In order to prioritize the operators
     * we need to follow that rule, push the first operator to the stack, then
     * if the next operator takes higher precedence push that one to the stack, or if
     * the operator takes lower precedence then pop the operator on the stack and
     * push the current one. If the operators are the same precedence then pop the
     * operator on the stack and push the current operator.
     *
     * Using this logic we are able to prepare a readable equation for which we can
     * preform in the next evaluation method.
     */
    public void postfixExpression() {

        postFix = "";
        String tempString = "";
        Stack<Character> postFixEX = new Stack<Character>();

        for (int i = 0; i < temp.length(); i++) {
            if (Character.isDigit(temp.charAt(i))) {
                tempString += temp.charAt(i);
            }
            if (temp.charAt(i) == ' ') {
                if (!tempString.isEmpty()) {
                    if (tempString.charAt(0) == ' ') {
                        tempString.replace(" ", "");
                    } else {
                        postFix += tempString + " ";
                        tempString = "";
                    }
                }
            }
            if (temp.charAt(i) == '+' || temp.charAt(i) == '-' || temp.charAt(i) == '*' || temp.charAt(i) == '/') {

                if (temp.charAt(i) == '+' || temp.charAt(i) == '-') {
                    if (postFixEX.peek() == '(' || postFixEX.isEmpty()) {
                        postFixEX.push(temp.charAt(i));
                    } else if (postFixEX.peek() == '+' || postFixEX.peek() == '-') {
                        tempString += postFixEX.pop();
                        postFixEX.push(temp.charAt(i));
                    } else if (postFixEX.peek() == '*' || postFixEX.peek() == '/') {
                        tempString += postFixEX.pop();
                        postFixEX.push(temp.charAt(i));
                    }
                }
                if (temp.charAt(i) == '*' || temp.charAt(i) == '/') {
                    if (postFixEX.peek() == '(' || postFixEX.isEmpty()) {
                        postFixEX.push(temp.charAt(i));
                    } else if (postFixEX.peek() == '*' || postFixEX.peek() == '/') {
                        tempString += postFixEX.pop();
                        postFixEX.push(temp.charAt(i));

                    } else if (postFixEX.peek() == '+' || postFixEX.peek() == '-') {
                        postFixEX.push(temp.charAt(i));

                    }
                }
            }
            if (temp.charAt(i) == '(') {
                postFixEX.push(temp.charAt(i));
            }
            if (temp.charAt(i) == ')') {
                while (postFixEX.isEmpty() || postFixEX.peek() != '(') {
                    postFix += postFixEX.pop();
                }
                postFixEX.pop();
            }
        }
    }

        public String getPostfix () {

            return postFix;
        }

    /**
     * Using the switch statement the method is able to
     * assess the problem and calculate the final result.
     *
     * It is depended on the postFix solution that is provided
     * using the static variable "postFix".
     *
     * In the beginning of the method is parses the string,
     * and adds it to the "stringToInt" variable, once a space
     * is detected is converts it to an integer and then pushes
     * it to the stack, after which both the temp string and temp
     * int are cleared or reset back to 0.
     *
     * @return Returns the final result of the evaluation
     */
    public int evaluateRPN () {

            Stack<Integer> evalRPN = new Stack<Integer>();
            int intToStack = 0;
            int finalResult = 0;
            int num1;
            int num2;
            String stringToInt = "";
            for (int i = 0; i < postFix.length(); i++) {

                if (i >= 1) {

                    if (Character.isDigit(postFix.charAt(i))) {
                        stringToInt += postFix.charAt(i);
                    }
                } else if (Character.isDigit(postFix.charAt(i))) {
                    stringToInt += postFix.charAt(i);
                }
                if (postFix.charAt(i) == ' ' || i == postFix.length() - 1) {
                    if (!stringToInt.isEmpty()) {
                        intToStack = Integer.parseInt(stringToInt);
                        evalRPN.push(intToStack);
                        stringToInt = "";
                    }
                }
                if (evalRPN.size() > 1) {
                    switch (postFix.charAt(i)) {
                        case '*':
                            num1 = evalRPN.pop();
                            num2 = evalRPN.pop();
                            evalRPN.push(num1 * num2);
                            break;
                        case '/':
                            num1 = evalRPN.pop();
                            num2 = evalRPN.pop();
                            evalRPN.push(num1 / num2);
                            break;
                        case '+':
                            num1 = evalRPN.pop();
                            num2 = evalRPN.pop();
                            evalRPN.push(num1 + num2);
                            break;
                        case '-':
                            num1 = evalRPN.pop();
                            num2 = evalRPN.pop();
                            evalRPN.push(num2 - num1);
                            break;
                    }
                }
            }
            finalResult = evalRPN.pop();
            return finalResult;
        }
    }


