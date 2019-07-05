package it.alex.lab11;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {
    private final Map<String, Integer> priorityMap;

    public Calculator() {
        priorityMap = new HashMap<>();
        priorityMap.put("(", 0);
        priorityMap.put(")", 1);
        priorityMap.put("+", 2);
        priorityMap.put("-", 3);
        priorityMap.put("*", 4);
        priorityMap.put("/", 4);
    }

    private boolean isNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private String[] convertInputToArray(String string) {
        string = string.replaceAll("\\s", "");
        String operators = "(\\(|\\)|\\+|-|\\*|/)";
        return string.split(String.format("(?<=%s)|(?=%s)", operators, operators));
    }

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


    private String expressionEvaluation(String input) {
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

    private String getReversePolishNotation(String[] input, Map<String, Integer> operationMap) {
        Stack<String> operatorStack = new Stack<>(); //Стек для хранения операторов
        String output = "";
        for (int i = 0; i < input.length; i++) {
            //Если число
            if (isNumber(input[i])) {
                output += input[i] + " ";

                //Если оператор
            } else if (operationMap.containsKey(input[i])) {
                if (input[i].equals("(")) {
                    operatorStack.push(input[i]);
                } else if (input[i].equals(")")) {
                    String symbol = operatorStack.pop();
                    while (!(symbol.equals("("))) {
                        output += symbol + " ";
                        symbol = operatorStack.pop();
                    }
                } else {
                    if (!operatorStack.empty() && operationMap.get(input[i]) <= operationMap.get(operatorStack.peek())) {
                        output += operatorStack.pop() + " ";
                        operatorStack.push(input[i]);
                    } else {
                        operatorStack.push(input[i]);
                    }
                }
            }
        }
        while (!operatorStack.empty()) {
            output += operatorStack.pop() + " ";
        }
        return output;
    }

    public double calculate(String expression) throws ExceptionCalculator {
        try {
            String[] array = convertInputToArray(expression);
            String expressionRPN = getReversePolishNotation(array, priorityMap);
            return Double.valueOf(expressionEvaluation(expressionRPN));
        } catch (RuntimeException e){
            throw new ExceptionCalculator(e);
        }
    }

}