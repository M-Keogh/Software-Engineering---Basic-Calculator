import java.util.Stack;
public class Calculator {

    // Function that checks if input is a valid operand
    public boolean isOperand(char opp) {
        return switch (opp) {
            case '+', '-', '*' -> true;
            default -> false;
        };
    }

    // Function that specifies priority of operand
    public int orderOfOperand(char opp){
        return switch (opp) {
            case '+', '-' -> 1;
            case '*' -> 2;
            default -> -1;
        };
    }

    // Funtion that converts a string to postfix notation
    public String[] toPostFix(String str){
        StringBuilder pf = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for ( int i = 0; i < str.length(); i++ ) { // interates through the string
            char curChar = str.charAt(i);
            if ( orderOfOperand(curChar) > 0 ) { // enters code block if current character is an operand
                pf.append(" ");
                while ( !stack.isEmpty() && orderOfOperand(stack.peek()) >= orderOfOperand(curChar) ) { // inserts characters in the correct order
                    pf.append(stack.pop());
                    pf.append(" ");
                }
                stack.push(curChar);
            } else {
                pf.append(curChar);
            }
        }
        for ( int i = 0; i <= stack.size(); i++ ) { // appends final characters from stack to the string builder
            pf.append(" ");
            pf.append(stack.pop());
        }
        return pf.toString().split(" "); // returns string array
    }

    // Function that calculates sum of a postfix expression
    public int calculate(String str) {
        if ( isValid(str) ) {
            String[] pfExp = toPostFix(str);
            int i = 0;
            int length = pfExp.length;
            while ( length > 1) {
                if ( isOperand(pfExp[i].charAt(0)) ) {
                    int var1 = Integer.parseInt(pfExp[i-2]);
                    int var2 = Integer.parseInt(pfExp[i-1]);
                    switch ( pfExp[i] ) {
                        case "+" -> pfExp[i - 2] = Integer.toString(var1 + var2);
                        case "-" -> pfExp[i - 2] = Integer.toString(var1 - var2);
                        case "*" -> pfExp[i - 2] = Integer.toString(var1 * var2);
                        default -> {
                        }
                    }
                    i++;
                    for ( int j = i; j < length; j++ ) {
                        pfExp[j-2] = pfExp[j];
                        pfExp[j] = null;
                    }
                    length = length-2;
                    i = i-3;
                }
                i++;
            }
            return Integer.parseInt(pfExp[0]);
        }
        return 0;
    }

    public boolean isValid(String exp){
        if ( isOperand(exp.charAt(0)) || isOperand(exp.charAt(exp.length()-1)) )
            return false;

        boolean lastWasOperator = false; //if last character read was an operator

        for ( char c : exp.toCharArray() ) {
            if ( isOperand(c) ) {
                if (lastWasOperator) return false;
                lastWasOperator = true;
                continue;
            } else if ( c < '0' || c > '9' ){
                return false;
            }
            lastWasOperator = false;
        }
        if ( lastWasOperator ) return false;
        return true;
    }
}
