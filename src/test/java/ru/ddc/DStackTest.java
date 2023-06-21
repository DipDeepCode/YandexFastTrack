package ru.ddc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DStackTest {
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private ByteArrayInputStream in;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
    }

    @Test
    public void test1() {
        in = new ByteArrayInputStream(("""
                5
                push 2
                push 1
                max
                pop
                max""").getBytes());
        System.setIn(in);
        D_Stack.main(new String[]{});
        assertEquals("""
                2
                2
                """, output.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    public void test2() {
        in = new ByteArrayInputStream(("""
                5
                push 1
                push 2
                max
                pop
                max""").getBytes());
        System.setIn(in);
        D_Stack.main(new String[]{});
        assertEquals("""
                2
                1
                """, output.toString().replaceAll("\r\n", "\n"));
    }

    @Test
    public void test3() {
        in = new ByteArrayInputStream(("""
                10
                push 2
                push 3
                push 9
                push 7
                push 2
                max
                max
                max
                pop
                max""").getBytes());
        System.setIn(in);
        D_Stack.main(new String[]{});
        assertEquals("""
                9
                9
                9
                9
                """, output.toString().replaceAll("\r\n", "\n"));
    }
}
