package shuntingyard;
import java.util.*;

public class ShuntingYardAlgorithm {
    Stack<String> stack;
    Queue<String> queue;
    String tokens[];
    String operation;
    
    public ShuntingYardAlgorithm() {
        stack = new Stack<>();
        queue = new LinkedList<>();
        String operation;
    }
    
    public ShuntingYardAlgorithm(String input) {
        stack = new Stack<>();
        queue = new LinkedList<>();
        tokens = input.split(" ");
        operation = "";
        this.toRPD();
        System.out.println(operation);
    }
    
    public void toRPD() {
        for(int i = 0; i < tokens.length; i++) { //While there are tokens to be read.
            String token = tokens[i];
            if(this.isInt(token)) {
                System.out.println("Token identified as a number. Adding " + token + " to the queue...");
                queue.add(token);
            }
            else if(this.isOp(token)) {
                System.out.println("Token identified as an operator.");
                if (stack.empty()) {
                    System.out.println("Stack is empty, adding the operator " + token + " to the stack...");
                    stack.push(token);
                }
                else if(stack.peek().equals("(")) {
                    System.out.println("Previous operator is: ( \nPushing new token " + token + " to the stack...");
                    stack.push(token);
                }
                else {
                    System.out.println("Going to check operator precedence...");
                    System.out.println("Stack's precedence is " + this.checkPrecedence(stack.peek()) + " for " + stack.peek());
                    System.out.println("Current token's precedence is " + this.checkPrecedence(token) + " for " + token);
                    while(!stack.isEmpty() && this.checkPrecedence(stack.peek()) >= this.checkPrecedence(token)) { //While the stack holds an operator of greater or equal precedence.
                        System.out.println("Previous precedence is greater. Moving " + stack.peek() + " stack to queue...");
                        queue.add(stack.pop());
                    }
                    System.out.println("Adding the operator " + token + " to the stack now...");
                    stack.push(token);
                }
            }
            else if(token.equals("(")) {
                System.out.println("Token identified as a (. Pushing to the stack now...");
                stack.push(token);
            }
            else if(token.equals(")")) {
                System.out.println("Ending ) found...");
                while(!stack.peek().equals("("))
                    queue.add(stack.pop());
                stack.pop(); //Gets rid of the (
            }
        }
        while(!stack.isEmpty()) {
            queue.add(stack.pop());
        }
        while(!queue.isEmpty()) {
            operation += queue.poll();
        }
    }
    
    private boolean isInt(String input) {
        boolean valid;
        try {
            Integer.parseInt(input);
            valid = true;
        }
        catch (NumberFormatException e) {
            valid = false;
        }
        return valid;
    }
    
    private boolean isOp(String input) {
        boolean valid = false;
        if(input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/") || input.equals("^"))
            valid = true;
        return valid;
    }
    
    private int checkPrecedence(String input) {
        int precedence = 0;
        if(input.equals("+") || input.equals("-"))
            precedence = 0;
        else if(input.equals("*") || input.equals("/"))
            precedence = 1;
        else if(input.equals("^"))
            precedence = 2;
        return precedence;
    }
}
