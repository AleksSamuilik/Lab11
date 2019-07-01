package it.alex.lab11;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CalculatorAppTest {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        CalculatorApp calcApp = new CalculatorApp();
        ConverterRPN calcRPN = new ConverterRPN();
        ReadyInput readyInp = new ReadyInput();
        ExpressionCalculator expressionCalc = new ExpressionCalculator();

        String input = "( (4-9) *  2+15  ) /2-9*(2+1  )";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String[] expectedInput = {"(", "(", "4", "-", "9", ")", "*", "2", "+", "15", ")", "/", "2", "-", "9", "*", "(", "2", "+", "1", ")"};
        String[] actualInput = readyInp.readyInput();
        assertArrayEquals("Wrong input", expectedInput, actualInput);
        String expectedRPN = "4 9 - 2 * 15 + 2 / 9 2 1 + * - ";
        Field field = CalculatorApp.class.getDeclaredField("priorityMap");
        field.setAccessible(true);
        Map<String, Integer> map = (Map<String, Integer>) field.get(calcApp);
        String actualRPN = calcRPN.getExpression(actualInput, map);
        assertEquals("Wrong convert RPN", expectedRPN, actualRPN);
        String expectedResult = "-24.5";
        String actualResult = expressionCalc.expressionEvaluation(actualRPN);
        assertEquals("Error", expectedResult,actualResult);

    }

    //((4-9)*2+15)/2-9*(2+1)
    //-24.5
    //4 9 - 2 * 15 + 2 / 9 2 1 + * -
    //5+9*2-(8+4)*9/3
    //=-13
    // 5 9 2 * + 8 4 + 9 * 3 / -
    //3 + 4 * 2 / (1 − 5)*2
    //-1
    //3 4 2 * 1 5 - / 2 * +
}