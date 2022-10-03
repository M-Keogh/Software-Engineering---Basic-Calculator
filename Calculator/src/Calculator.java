import java.util.Stack;
public class Calculator {

    public boolean isOperand(String opp) {
        return switch (opp) {
            case "+", "-", "*" -> true;
            default -> false;
        };
    }

    public int orderOfOperand(char opp){
        return switch (opp) {
            case '+', '-' -> 1;
            case '*' -> 2;
            default -> -1;
        };
    }

    public String[] toPostFix(String str){
        StringBuilder pf = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <str.length() ; i++) {
            char curChar = str.charAt(i);
            if(orderOfOperand(curChar)>0){
                pf.append(" ");
                while(!stack.isEmpty() && orderOfOperand(stack.peek())>= orderOfOperand(curChar)){
                    pf.append(stack.pop());
                    pf.append(" ");
                }
                stack.push(curChar);
            }else{
                pf.append(curChar);
            }
        }
        for (int i = 0; i <=stack.size() ; i++) {
            pf.append(" ");
            pf.append(stack.pop());
        }
        return pf.toString().split(" ");
    }

    public int calculate(String str) {
        String[] pfExp = toPostFix(str);
        int i = 0;
        int length = pfExp.length;
        while ( length > 1) {
            if (isOperand(pfExp[i])) {
                int var1 = Integer.parseInt(pfExp[i-2]);
                int var2 = Integer.parseInt(pfExp[i-1]);
                switch (pfExp[i]) {
                    case "+" -> pfExp[i - 2] = Integer.toString(var1 + var2);
                    case "-" -> pfExp[i - 2] = Integer.toString(var1 - var2);
                    case "*" -> pfExp[i - 2] = Integer.toString(var1 * var2);
                    default -> {
                    }
                }
                i++;
                for ( int j = i; j < length; j++) {
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

}
