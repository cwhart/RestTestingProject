package com.sg.vendingmachine.service;

public class IncorrectAdminPasswordException extends Exception {

    public IncorrectAdminPasswordException(String message) {

        //..

        super(message);

    }

    public IncorrectAdminPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
