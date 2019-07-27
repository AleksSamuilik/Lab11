package it.alex.lab11;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculatorAppTest {
    @Parameterized.Parameters(name = "{index}:Expression{0}={1}")
    public static Iterable<Object[]> data() {
        Object[][] data = new Object[][]{
                {"1+1*(3-2)", 2d},
                {"2+2*2", 6d},
                {"((4-   9)  *2+15   )/  2  - 9*(2      + 1)", -24.5},
                {"( 2  +  2 *  2) * ( 9 / 3 * 2 - 3) / 2", 9d},
                {"(  (  (  ( 4 - 2 )*3 )/ 2) +5 *9 /3 +2 )/ 2", 10d},
                {"5+  9  *2  -( 8+ 4 ) * 9/     3 ", -13d},
                {"1+1(1)", "RuntimeException"},
                {"((4-   9)  *2+15   )/  2  - 9*(2    a  + 1)", "RuntimeException"},
                {"((4-   9)  *2+15   )/  2  - 9*(2    (  + 1)", "EmptyStackException"},
                {"((4-   9)  *2+15   )/  2  - 9*(2    *  + 1)", "RuntimeException"},
                {"1+1*5+2*(5+)", "RuntimeException"},
                {"(2*3)+((2-1)/2", "EmptyStackException"},
                {"5+2*", "RuntimeException"},
                {"(4-2)+7)/2", "EmptyStackException"},
                {"dsa+sad-ssd", "RuntimeException"}
        };
        return Arrays.asList(data);
    }


    private String expression;
    private double expected;
    private String exception;

    public CalculatorAppTest(String expression, Object expected) {
        this.expression = expression;
        if (expected instanceof String) {
            this.exception = (String) expected;
        } else {
            this.expected = (Double) expected;
        }
    }

    Calculator calculator = new Calculator();

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void calculationTest() {
        if (null == exception) {
            assertEquals(expected, calculator.calculate(expression), 0.0);
        } else {
            thrown.expect(ExceptionCalculator.class);
            thrown.expectMessage(exception);
            calculator.calculate(expression);
        }
    }
}