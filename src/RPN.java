import java.util.Collection;
import java.util.Stack;

public class RPN {

    public static String[] s = {"5 + ) * ( 2",
            " 2 + ( - 3 * 5 ) ",
            "(( 2 + 3 ) * 5 ) * 8 ",
            "5 * 10 + ( 15 - 20 ) )  - 25",
            " 5 + ( 5 *  10 + ( 15 - 20 )  - 25 ) * 9"
    };

    public static void main(String[] arg) {


        for (int i = 0; i < s.length; i++) {
            Arithmetic a = new Arithmetic(s[i]);

            if (a.isBalance()) {
                System.out.println("Expression " + s[i] + " is balanced\n");
                a.postfixExpression();
                System.out.println("The post fixed expression is " + a.getPostfix());
                //a.evaluateRPN();


            }
            else
                System.out.println("Expression is not balanced\n");


            }


        }

    }
