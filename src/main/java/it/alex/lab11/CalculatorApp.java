package it.alex.lab11;

import java.util.HashMap;
import java.util.Map;

public class CalculatorApp {
    private final Map<String, Integer> priorityMap;

    public CalculatorApp() {
        priorityMap = new HashMap<>();
        priorityMap.put("(", 0);
        priorityMap.put(")", 1);
        priorityMap.put("+", 2);
        priorityMap.put("-", 3);
        priorityMap.put("*", 4);
        priorityMap.put("/", 4);
    }

    public static void main(String[] args) {
        CalculatorApp calcApp = new CalculatorApp();
        ConverterRPN calcRPN = new ConverterRPN();
        ReadyInput readyInp = new ReadyInput();
        ExpressionCalculator expressionCalc = new ExpressionCalculator();
        String[] expression = readyInp.readyInput();
        String expressionRPN = calcRPN.getExpression(expression, calcApp.priorityMap);
        String result = expressionCalc.expressionEvaluation(expressionRPN);
        System.out.println(result);
    }
}