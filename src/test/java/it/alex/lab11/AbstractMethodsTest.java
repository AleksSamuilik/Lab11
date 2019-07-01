package it.alex.lab11;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractMethodsTest {

    @Test
    public void isNumber() {
        AbstractMethods abstractMethods = new AbstractMethods() {
            @Override
            public boolean isNumber(String number) {
                return super.isNumber(number);
            }
        };
        assertEquals("Wrong boolean operator.", true, abstractMethods.isNumber("5678"));
    }

    @Test
    public void createInputArray() {
        AbstractMethods abstractMethods = new AbstractMethods() {
            @Override
            public boolean isNumber(String number) {
                return super.isNumber(number);
            }
        };
        String input = "( (4-9) *  2+15  ) /2-9*(2+1  )";
        String[] expected = {"(", "(", "4", "-", "9", ")", "*", "2", "+", "15", ")", "/", "2", "-", "9", "*", "(", "2", "+", "1", ")"};
        assertArrayEquals("Wrong Array", expected, abstractMethods.createInputArray(input));
    }
}