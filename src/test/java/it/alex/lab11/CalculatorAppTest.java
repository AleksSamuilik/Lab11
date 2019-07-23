package it.alex.lab11;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculatorAppTest {
    @Parameterized.Parameters(name = "{index}:Expression{0}={1}")
    public static Iterable<Object[]> data() {
        Object[][] data = new Object[][]{
                {"1+1*(3-2)", 2},
                {"2+2*2", 6},
                {"((4-   9)  *2+15   )/  2  - 9*(2      + 1)", -24.5},
                {"( 2  +  2 *  2) * ( 9 / 3 * 2 - 3) / 2", 9},
                {"(  (  (  ( 4 - 2 )*3 )/ 2) +5 *9 /3 +2 )/ 2", 10},
                {"5+  9  *2  -( 8+ 4 ) * 9/     3 ", -13},
                {"1+1(1)", 0},
                {"((4-   9)  *2+15   )/  2  - 9*(2    a  + 1)", 0},
                {"((4-   9)  *2+15   )/  2  - 9*(2    (  + 1)", 0},
                {"((4-   9)  *2+15   )/  2  - 9*(2    *  + 1)", 0},
                {"1+1*5+2*(5+)", 0},
                {"(2*3)+((2-1)/2", 0},
                {"5+2*", 0},
                {"(4-2)+7)/2", 0},
                {"dsa+sad-ssd", 0}
        };
        return Arrays.asList(data);
    }

    private String expression;
    private double expected;

    public CalculatorAppTest(String expression, double expected) {
        this.expression = expression;
        this.expected = expected;
    }

    Calculator calculator = new Calculator();

    @Test
    public void calculationTest() {
        try {
            assertEquals(expected, calculator.calculate(expression), 0.0);
        } catch (Exception e) {
            if (e.getCause() instanceof RuntimeException) {
            } else {
                Assert.fail("unexpected exception");
            }
        }
    }
}