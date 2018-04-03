package com.sg.io;


import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {

    private Scanner scanner = new Scanner(System.in); //'Has-a' - composition.

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Double.parseDouble(input);
    }

    @Override
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

    @Override
    public float readFloat(String prompt, float min, float max) {
        float myFloat;

        do {
            myFloat = readFloat(prompt);

        } while(myFloat < min || myFloat> max);
        //System.out.println(myFloat);

        return myFloat;
    }

    @Override
    public int readInt(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Integer.parseInt(input);
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int myInt;

        do {
            myInt = readInt(prompt);

        } while(myInt < min || myInt> max);
        //System.out.println(myInt);

        return myInt;
    }

    @Override
    public long readLong(String prompt) {
        print(prompt);
        String input = scanner.nextLine();
        return Long.parseLong(input);
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long myLong;

        do {
            myLong = readLong(prompt);

        } while(myLong < min || myLong> max);
        //System.out.println(myLong);

        return myLong;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return scanner.nextLine();

    }
}

