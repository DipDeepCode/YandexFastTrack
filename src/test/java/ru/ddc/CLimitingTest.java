package ru.ddc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CLimitingTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private ByteArrayInputStream in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void test1() {
        in = new ByteArrayInputStream(("""
                2 5 5
                1 100
                1 100
                2 100
                2 200
                2 300
                2 400
                2 500
                3 500
                5 200
                6 100
                7 200
                -1""").getBytes());
        System.setIn(in);
        C_Limiting.main(new String[]{});
        assertEquals("""
                200
                200
                429
                200
                200
                200
                503
                503
                503
                429
                200
                """, output.toString().replaceAll("\r\n", "\n"));
    }
}