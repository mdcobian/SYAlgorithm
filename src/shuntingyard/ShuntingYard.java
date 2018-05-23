package shuntingyard;

public class ShuntingYard {

    public static void main(String[] args) {
        String operation = "9 + 24 / ( 7 - 3 )";
        ShuntingYardAlgorithm RPN = new ShuntingYardAlgorithm(operation);
    }
    
}
