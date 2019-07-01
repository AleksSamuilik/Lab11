package it.alex.lab11;

import java.util.Stack;

public class ExpressionCalculator extends AbstractMethods {

    private Double getOperate(String symbol, String firstNumber, String secondNumber) {
        if (symbol.equals("+")) {
            return addition(firstNumber, secondNumber);
        } else if (symbol.equals("-")) {
            return subtraction(firstNumber, secondNumber);
        } else if (symbol.equals("/")) {
            return division(firstNumber, secondNumber);
        } else {
            return multiplication(firstNumber, secondNumber);
        }
    }

    private double addition(String firstNumber, String secondNumber) {
        return Double.parseDouble(firstNumber) + Double.parseDouble(secondNumber);
    }

    private double subtraction(String firstNumber, String secondNumber) {
        return Double.parseDouble(firstNumber) - Double.parseDouble(secondNumber);
    }

    private double division(String firstNumber, String secondNumber) {
        return Double.parseDouble(firstNumber) / Double.parseDouble(secondNumber);
    }

    private double multiplication(String firstNumber, String secondNumber) {
        return Double.parseDouble(firstNumber) * Double.parseDouble(secondNumber);
    }


    public String expressionEvaluation(String input) {
        String firstNumber;
        String secondNumber;
        Stack<String> temp = new Stack<>();
        String[] array = input.split("\\s");

        for (int i = 0; i < array.length; i++) {
            if (isNumber(array[i])) {
                temp.push(array[i]);
            } else {
                secondNumber = temp.pop();
                firstNumber = temp.pop();
                temp.push(String.valueOf(getOperate(array[i], firstNumber, secondNumber)));
            }
        }
        return temp.peek();
    }
}