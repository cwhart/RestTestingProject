package com.sg.flooringmastery.ui;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserIOConsoleImpl implements com.sg.flooringmastery.ui.UserIO {

    private Scanner scanner = new Scanner(System.in); //'Has-a' - composition.

    public void print(String message) {
        System.out.println(message);
    }

    public double readDouble(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Double.parseDouble(input);
    }

    public double readDouble(String prompt, double min, double max) {
        double myDouble;

        do {
            myDouble = readDouble(prompt);

        } while(myDouble < min || myDouble> max);
        //System.out.println(myDouble);

        return myDouble;

    }

    public float readFloat(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Float.parseFloat(input);
    }

    public float readFloat(String prompt, float min, float max) {
        float myFloat;

        do {
            myFloat = readFloat(prompt);

        } while(myFloat < min || myFloat> max);
        //System.out.println(myFloat);

        return myFloat;
    }

    public int readInt(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Integer.parseInt(input);


    }

    public int readInt(String prompt, int min, int max) {
        int myInt;

        do {
            myInt = readInt(prompt);

        } while(myInt < min || myInt> max);
        //System.out.println(myInt);

        return myInt;
    }

    public long readLong(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Long.parseLong(input);
    }

    public long readLong(String prompt, long min, long max) {
        long myLong;

        do {
            myLong = readLong(prompt);

        } while(myLong < min || myLong> max);
        //System.out.println(myLong);

        return myLong;
    }

    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();

    }

    @Override
    public LocalDate readLocalDate(String prompt) throws DateTimeException {
        print(prompt);
        String inputDateStr = scanner.nextLine();
        return LocalDate.parse(inputDateStr);

    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return new BigDecimal(input);
    }

    //..
}
