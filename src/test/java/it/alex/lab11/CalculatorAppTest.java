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
                {"1+1*(3-2)", 2},
                {"2+2*2", 6},
                {"((4-   9)  *2+15   )/  2  - 9*(2      + 1)", -24.5},
                {"( 2  +  2 *  2) * ( 9 / 3 * 2 - 3) / 2", 9},
                {"(  (  (  ( 4 - 2 )*3 )/ 2) +5 *9 /3 +2 )/ 2", 10},
                {"5+  9  *2  -( 8+ 4 ) * 9/     3 ", -13}
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void exceptionCheckTest1() {
        thrown.expect(ExceptionCalculator.class);
        thrown.expectMessage("RuntimeException");

        calculator.calculate("1+1(1)");
    }

    @Test
    public void exceptionCheckTest2() {
        thrown.expect(ExceptionCalculator.class);
        thrown.expectMessage("RuntimeException");

        calculator.calculate("((4-   9)  *2+15   )/  2  - 9*(2    a  + 1)");
    }

    @Test
    public void exceptionCheckTest3() {
        thrown.expect(ExceptionCalculator.class);
        thrown.expectMessage("EmptyStack");

        calculator.calculate("((4-   9)  *2+15   )/  2  - 9*(2    (  + 1)");
    }

    @Test
    public void exceptionCheckTest4() {
        thrown.expect(ExceptionCalculator.class);
        thrown.expectMessage("RuntimeException");

        calculator.calculate("((4-   9)  *2+15   )/  2  - 9*(2    *  + 1)");
    }

    @Test
    public void exceptionCheckTest5() {
        thrown.expect(ExceptionCalculator.class);
        thrown.expectMessage("RuntimeException");

        calculator.calculate("1+1*5+2*(5+)");
    }
 @Test
    public void exceptionCheckTest6() {
        thrown.expect(ExceptionCalculator.class);
        thrown.expectMessage("EmptyStack");

        calculator.calculate("(2*3)+((2-1)/2");
    }
@Test
    public void exceptionCheckTest7() {
        thrown.expect(ExceptionCalculator.class);
        thrown.expectMessage("RuntimeException");

        calculator.calculate("5+2*");
    }
@Test
    public void exceptionCheckTest8() {
        thrown.expect(ExceptionCalculator.class);
        thrown.expectMessage("EmptyStack");

        calculator.calculate("(4-2)+7)/2");
    }

    @Test
    public void calculationTest() {
        assertEquals(expected, calculator.calculate(expression), 0.0);
    }


}