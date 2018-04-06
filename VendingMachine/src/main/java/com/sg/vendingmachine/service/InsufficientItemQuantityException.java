package com.sg.vendingmachine.service;

public class InsufficientItemQuantityException extends Exception {

    public InsufficientItemQuantityException(String message) {

        super(message);

    }

    public InsufficientItemQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

}