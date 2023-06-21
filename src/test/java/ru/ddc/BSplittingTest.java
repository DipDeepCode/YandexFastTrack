package ru.ddc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BSplittingTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private ByteArrayInputStream in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void test1() {
        in = new ByteArrayInputStream(("""
                MONTH
                2020-01-10 2020-03-25""").getBytes());
        System.setIn(in);
        B_Splitting.main(new String[]{});
        assertEquals("""
                3
                2020-01-10 2020-01-31
                2020-02-01 2020-02-29
                2020-03-01 2020-03-25
                """, output.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    public void test2() {
        in = new ByteArrayInputStream(("""
                WEEK
                2020-01-26 2020-03-23""").getBytes());
        System.setIn(in);
        B_Splitting.main(new String[]{});
        assertEquals("""
                10
                2020-01-26 2020-01-26
                2020-01-27 2020-02-02
                2020-02-03 2020-02-09
                2020-02-10 2020-02-16
                2020-02-17 2020-02-23
                2020-02-24 2020-03-01
                2020-03-02 2020-03-08
                2020-03-09 2020-03-15
                2020-03-16 2020-03-22
                2020-03-23 2020-03-23
                """, output.toString().replaceAll("\r\n", "\n"));
    }
}
