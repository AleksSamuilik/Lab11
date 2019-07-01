package it.alex.lab11;

import java.util.Map;
import java.util.Stack;

public class ConverterRPN extends AbstractMethods {

    public String getExpression(String[] input, Map<String, Integer> operationMap) {
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
}