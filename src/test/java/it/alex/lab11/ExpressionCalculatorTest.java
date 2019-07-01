package it.alex.lab11;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionCalculatorTest {

    @Test
    public void expressionEvaluation() {
        ExpressionCalculator expressionCalculator = new ExpressionCalculator();
        String input = "4 9 - 2 * 15 + 2 / 9 2 1 + * - ";
        String expected = "-24.5";
        assertEquals("Wrong count", expected, expressionCalculator.expressionEvaluation(input));
    }

}