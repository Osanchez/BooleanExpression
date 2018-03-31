import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BooleanExpression {

    public ArrayList<String> validCharacters = new ArrayList<>();
    public Deque<String> stack = new ArrayDeque<>();


    public BooleanExpression() {
        validCharacters.add("1");
        validCharacters.add("0");
        validCharacters.add("+");
        validCharacters.add("*");
    }

    private boolean boolean_expression(String boolean_expressions) throws Exception {
        String[] split_expression = boolean_expressions.split("");

        //checks for invalid character
        for (String character : split_expression) {
            //check if valid character
            if (!validCharacters.contains(character)) {
                throw new Exception("boolean expression contains invalid character.");
            }

            if (character.equals("1") || character.equals("0")) { //add character to stack
                stack.push(character);
            }

            else if (stack.size() >= 2 && character.equals("*")) { //AND operations
                String a = stack.pop();
                String b = stack.pop();
                if (a.equals(b)) {
                    stack.push("1");
                } else {
                    stack.push("0");
                }
            }
            else if (stack.size() >= 2 && character.equals("+")) { //OR operations
                String a = stack.pop();
                String b = stack.pop();
                if (a.equals("0") && b.equals("0")) {
                    stack.push("0");
                } else {
                    stack.push("1");
                }
            } else {
                throw new Exception("Unable to parse boolean expression.");
            }
        }

        //begin creating boolean expressions
        if(stack.size() == 1) {
            return stack.pop().equals("1");
        } else {
            throw new Exception("Unable to parse boolean expression.");
        }
    }

    public static void main(String[] args) throws Exception {
        /*
        P(9.3.3) - Write a boolean method in real java that inputs a string in {0,1,+*}* and outputs the boolean value
        of the corresponding postfix boolean expression, interpreting + as V and * as ^. Your method should throw an
        exception if the input string contains an invalid character or cannot be parsed as a boolean expression.
         */
        BooleanExpression boolean_e = new BooleanExpression();
        boolean result = boolean_e.boolean_expression("01+00*+11+01++*");
        System.out.println(result);

    }
}