import java.util.Stack;

public class Arithmetic<Static> extends RPN {

    String temp = "";
    static String postFix = "";


    public Arithmetic(String s) {
        temp += "( " + s + " )";

    }

    public boolean isBalance() {

        //System.out.println(temp);
        String postFixExpression = "";
        Stack<Character> balancingStack = new Stack<Character>();

        for (int i = 0; i < temp.length(); i++) {

            //System.out.println(postFixExpression);

            if (Character.isDigit(temp.charAt(i))) {
                postFixExpression += temp.charAt(i);
            }
            if (temp.charAt(i) == '(') {
                balancingStack.push(temp.charAt(i));
            }
            if ((temp.charAt(i) == '-') || (temp.charAt(i) == '+')) {
                if (balancingStack.isEmpty()) {
                    balancingStack.push(temp.charAt(i));
                }
                else if (balancingStack.peek().equals('+') || balancingStack.peek().equals('-')) {
                    postFixExpression = postFixExpression + temp.charAt(i) + " ";
                }
                else {
                    balancingStack.push(temp.charAt(i));
                }
            }

            if ((temp.charAt(i) == '*') || (temp.charAt(i) == '/')) {
                if (balancingStack.isEmpty()) {
                    balancingStack.push(temp.charAt(i));
                }
                else if (balancingStack.peek().equals('*') || balancingStack.peek().equals('/')) {
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


     public void postfixExpression () {
        postFix = "";
        String tempString = "";
        for (int i = 0; i < temp.length(); i++) {
            if (Character.isDigit(temp.charAt(i))) {
                tempString += temp.charAt(i);
            } else if (temp.charAt(i) == '+' || temp.charAt(i) == '-' || temp.charAt(i) == '*' || temp.charAt(i) == '/') {
                postFix += tempString;
                tempString = "";
                tempString += temp.charAt(i);
                postFix += tempString;
                tempString = "";
            }
        }
        postFix += tempString;
    }

    public String getPostfix() {

        return postFix;

    }

    public int evaluateRPN () {
        return 0;
        //FIXME
    }


}
