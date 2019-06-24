package it.alex.lab11;

import java.util.Stack;

public class Calculate {

    public boolean isDelimeter(char a) {
        String delimeter = " =";
        return checkCycle(delimeter, a);
    }

    public boolean isOperator(char a) {
        String operator = "+-/*()";
        return checkCycle(operator, a);
    }

    private boolean checkCycle(String string, char a) {
        boolean valid = false;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == a) {
                valid = true;
                break;
            } else {
                valid = false;
            }
        }
        return valid;
    }

    public byte getPriority(char a) {
        switch (a) {
            case '(':
                return 0;

            case ')':
                return 1;

            case '+':
                return 2;

            case '-':
                return 3;

            case '*':
                return 4;

            case '/':
                return 4;

            default:
                return 5;

        }
    }


    public void Calculate(String input) {
        String output = getExpression(input);
        System.out.println(output);
        // double result = counting(output);
        // return result;
    }


    private String getExpression(String input) {
        Stack<Character> operatorStack = new Stack<>(); //Стек для хранения операторов
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) { //Для каждого символа в входной строке
            //если рпзделитель
            if (isDelimeter(input.charAt(i))) {
                continue;
                // если число
            } else if (Character.isDigit(input.charAt(i))) {
                while (!isOperator(input.charAt(i)) && !isDelimeter(input.charAt(i))) {
                    builder.append(input.charAt(i));
                    i++;
                    if (i == input.length()) {
                        break;
                    }
                }
                builder.append(" ");
                i--;
                // если оператор
            } else if (isOperator(input.charAt(i))) {
                if (input.charAt(i) == '(') {
                    operatorStack.push(input.charAt(i));
                } else if (input.charAt(i) == ')') {
                    char symbol = operatorStack.pop();
                    System.out.println(symbol);
                    while (true) {
                        if (symbol == '(') {
                            break;
                        } else {
                            builder.append(symbol + " ");
                            symbol = operatorStack.pop();
                        }
                    }
                } else {
                    if (!operatorStack.empty() && getPriority(input.charAt(i)) <= getPriority(operatorStack.peek())) {
                        builder.append(operatorStack.pop() + " ");
                        operatorStack.push(input.charAt(i));
                    } else {
                        operatorStack.push(input.charAt(i));
                    }
                }
            }
        }
        while (!operatorStack.empty()) {
            builder.append(operatorStack.pop() + " ");
        }
        return builder.toString(); //Возвращаем выражение в постфиксной записи
    }


    public static void main(String[] args) {
        Calculate calculate = new Calculate();
        calculate.Calculate("3 + 4 * 2 / (1 − 5)*2");
        //5+9*2-(8+4)*9/3
        // 5 9 2 * + 8 4 + 9 * 3 / -

        //3 + 4 * 2 / (1 − 5)*2
        //3 4 2 * 1 5 - / 2 * +



    }
}
