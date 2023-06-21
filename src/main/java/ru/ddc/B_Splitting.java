package ru.ddc;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.*;
import java.util.Scanner;

import static java.time.DayOfWeek.MONDAY;
import static java.time.temporal.TemporalAdjusters.*;

public class B_Splitting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String type = scanner.next();

        LocalDate start = LocalDate.parse(scanner.next());
        LocalDate end = LocalDate.parse(scanner.next());

        LocalDate cursor;
        TemporalAmount step;

        switch (type) {
            case "WEEK" -> {
                cursor = start.with(previousOrSame(MONDAY));
                step = Period.ofWeeks(1);
            }
            case "MONTH" -> {
                cursor = start.withDayOfMonth(1);
                step = Period.ofMonths(1);
            }
            case "QUARTER" -> {
                int newMonth = ((start.getMonthValue() - 1) / 3) * 3 + 1;
                cursor = start.withMonth(newMonth).withDayOfMonth(1);
                step = Period.ofMonths(3);
            }
            case "YEAR" -> {
                cursor = start.with(firstDayOfYear());
                step = Period.ofYears(1);
            }
            case "REVIEW" -> {
                int month = start.getMonthValue();
                if (month >= 4 && month < 10) {
                    cursor = start.withMonth(4).withDayOfMonth(1);
                } else {
                    cursor = start.withMonth(10).withDayOfMonth(1);
                }
                step = Period.ofMonths(6);
            }
            default -> throw new IllegalArgumentException();
        }


        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for ( ; cursor.isBefore(end) || cursor.isEqual(end); cursor = cursor.plus(step), counter++) {
            stringBuilder.append(cursor.isBefore(start) ? start : cursor).append(" ");
            LocalDate stepEnd = cursor.plus(step).minusDays(1);
            stringBuilder.append(stepEnd.isBefore(end) ? stepEnd : end).append("\r\n");
        }
        System.out.println(counter);
        System.out.print(stringBuilder);
    }
}