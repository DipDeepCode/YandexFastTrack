package ru.ddc;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ARobotTest {
    String inputFilename = "input.txt";
    String outputFilename = "output.txt";

    @Test
    public void test1() {
        String input = "0";
        String expected = "0 0";
        assertEquals(expected, getActual(input));
    }

    @Test
    public void test2() {
        String input = "1";
        String expected = "-1 0";
        assertEquals(expected, getActual(input));
    }

    @Test
    public void test3() {
        String input = "2";
        String expected = "-1 -1";
        assertEquals(expected, getActual(input));
    }

    @Test
    public void test4() {
        String input = "14";
        String expected = "0 -2";
        assertEquals(expected, getActual(input));
    }

    private String getActual(String input) {
        try {
            Files.write(Paths.get(inputFilename), input.getBytes());
            A_Robot.main(new String[]{});
            return Files.readAllLines(Paths.get(outputFilename)).get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
