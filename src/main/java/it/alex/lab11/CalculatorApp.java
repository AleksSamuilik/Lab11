package it.alex.lab11;

public class CalculatorApp {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        ExpressionProvider expressionProvider = new CLIExpressionProvider();
        try {
            System.out.println(calculator.calculate(expressionProvider.getExpression()));
        } catch (ExceptionCalculator exception) {
            System.out.println("Sorry. Runtime error, try again ");
        }
    }
}