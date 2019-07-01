package it.alex.lab11;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class ReadyInputTest {

    @Test
    public void readyInput() {
        ReadyInput readyInp = new ReadyInput();
        String input = "( (4-9) *  2+15  ) /2-9*(2+1  )";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String[] expected = {"(", "(", "4", "-", "9", ")", "*", "2", "+", "15", ")", "/", "2", "-", "9", "*", "(", "2", "+", "1", ")"};
        assertArrayEquals("Wrong input", expected, readyInp.readyInput());
    }
}