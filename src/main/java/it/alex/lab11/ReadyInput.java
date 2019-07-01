package it.alex.lab11;

import java.util.Scanner;

public class ReadyInput extends AbstractMethods {

    private boolean checkArray(String[] expression) {
        int bracket = 0;
        int countDifference=0;
        boolean digit = false;
        String operators = "\\+\\-\\/\\*";
        for (int i = 0; i < expression.length; i++) {
            if (expression[i].equals("(")) {
                bracket++;
            } else if (expression[i].equals(")")) {
                bracket--;
            } else if (isNumber(expression[i])) {
                digit = true;
                countDifference++;
            } else if (operators.contains(expression[i])) {
                digit = false;
                countDifference--;
            } else {
                return false;
            }
        }
        if (bracket != 0) {
            return false;
        }
        if (digit != true) {
            return false;
        }
        if (countDifference!=1){
            return false;
        }
        return true;
    }

    public String[] readyInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] array = new String[0];
        System.out.println("Enter the expression: ");
        boolean valid;
        do {
            input = scanner.nextLine();
            if (input.replaceAll("(\\s+)|(\\d)|(\\(|\\)|\\+|-|\\*|/)", "").equals("")) {
                array = createInputArray(input);
            } else {
                System.out.println("Sorry. Try again");
                valid = true;
                continue;
            }
            if (checkArray(array)) {
                break;
            } else {
                System.out.println("Sorry. Try again");
                valid = true;
            }
        } while (valid);

        return array;

    }
}
