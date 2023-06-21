package ru.ddc;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class A_Robot {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("input.txt"))) {
            int n = scanner.nextInt();
            int x = 0, y = 0;
            if (n > 0) {
                for (int r = 1; ; r++) {
                    int to = r * (r + 1) * 4;
                    if (n <= to) {
                        int from = r * (r - 1) * 4 + 1;
                        int quarter = (n - from) / (r * 2);
                        int numberInQuarter = (n - from) % (r * 2) + 1;
                        switch (quarter) {
                            case 0 -> {
                                x = -r;
                                y = r - numberInQuarter;
                            }
                            case 1 -> {
                                x = numberInQuarter - r;
                                y = -r;
                            }
                            case 2 -> {
                                x = r;
                                y = numberInQuarter - r;
                            }
                            case 3 -> {
                                x = r - numberInQuarter;
                                y = r;
                            }
                        }
                        break;
                    }
                }
            }
            String result = x + " " + y;
            Files.write(Paths.get("output.txt"), result.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
