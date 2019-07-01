package it.alex.lab11;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ConverterRPNTest {

    @Test
    public void getExpression() {
        ConverterRPN converterRPN = new ConverterRPN();
        Map<String, Integer> priorityMap = new HashMap<>();
        priorityMap.put("(", 0);
        priorityMap.put(")", 1);
        priorityMap.put("+", 2);
        priorityMap.put("-", 3);
        priorityMap.put("*", 4);
        priorityMap.put("/", 4);
        String[] array = {"(", "(", "4", "-", "9", ")", "*", "2", "+", "15", ")", "/", "2", "-", "9", "*", "(", "2", "+", "1", ")"};
        String expected = "4 9 - 2 * 15 + 2 / 9 2 1 + * - ";
        assertEquals("Wrong converts to RPN", expected, converterRPN.getExpression(array, priorityMap));
    }
}