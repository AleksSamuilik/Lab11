package it.alex.lab11;

import org.junit.Test;

import org.junit.Assert.*;

import static org.junit.Assert.assertEquals;


public class CalculatorAppTest {

    Calculator calculator = new Calculator();

    @Test(expected = ExceptionCalculator.class)
    public void exceptionCheckWhenEnterLetter() {
        String input = "((4-   9)  *2+15   )/  2  - 9*(2    a  + 1)";

        calculator.calculate(input);
    }

    @Test(expected = ExceptionCalculator.class)
    public void exceptionCheckWhenEnterBracket() {
        String input = "((4-   9)  *2+15   )/  2  - 9*(2    (  + 1)";

        calculator.calculate(input);
    }

    @Test(expected = ExceptionCalculator.class)
    public void exceptionCheckWhenEnterOperationSymbol() {
        String input = "((4-   9)  *2+15   )/  2  - 9*(2    *  + 1)";

        calculator.calculate(input);
    }

    @Test
    public void calculationTest() {
        double expected1 = -24.5;
        double expected2 = 9;
        double expected3 = 10;
        double expected4 = 0;
        double expected5 = -24.5;
        String input1 = "((4-   9)  *2+15   )/  2  - 9*(2      + 1)";
        String input2 = "( 2  +  2 *  2) * ( 9 / 3 * 2 - 3) / 2";
        String input3 = "(  (  (  ( 4 - 2 )*3 )/ 2) +5 *9 /3 +2 )/ 2";
        String input4 = "()";
        String input5 = "((4-   9)  *2+15   )/  2  - 9*(2      + 1)";

        double actual1 = calculator.calculate(input1);
        double actual2 = calculator.calculate(input2);
        double actual3 = calculator.calculate(input3);
        double actual4 = calculator.calculate(input4);
        double actual5 = calculator.calculate(input5);

        assertEquals(expected1, actual1, 0.0);
        assertEquals(expected2, actual2, 0.0);
        assertEquals(expected3, actual3, 0.0);
        assertEquals(expected4, actual4, 0.0);
        assertEquals(expected5, actual5, 0.0);
    }


    //  ((4-   9)  *2+15   )/  2  - 9*(2      + 1)
//-24.5
//4 9 - 2 * 15 + 2 / 9 2 1 + * -
//

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