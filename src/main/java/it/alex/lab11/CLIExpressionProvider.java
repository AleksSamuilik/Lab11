package it.alex.lab11;

import java.util.Scanner;

public class CLIExpressionProvider implements ExpressionProvider {

    @Override
    public String getExpression() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the expression: ");
        String input = scanner.nextLine();
        return input;
    }

}
