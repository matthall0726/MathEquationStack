import java.util.Stack;

public class Test {

    public static void main (String[] args) {
        String[] s = {"5 + ) * ( 2",
                " 2 + ( - 3 * 5 ) ",
                "(( 2 + 3 ) * 5 ) * 8 ",
                "5 * 10 + ( 15 - 20 ) )  - 25",
                " 5 + ( 5 *  10 + ( 15 - 20 )  - 25 ) * 9"
        };

        Stack<String> balanced = new Stack<String>();
        balanced.push("(");
        /*for (int i = 0; i < s[0].length() - 1; i++) {

            if (Character.isDigit(i)) {

            }

            if (s[0].charAt(i) == "(") {
                balanced.push(s[i]);
            } if (s[i].equals("-") || s[i].equals("+")) {
                if (balanced.peek().equals("+") || balanced.peek().equals("-")) {

                }
            }

        }
        System.out.println(s[0]);
    }*/}
}
