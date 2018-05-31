package com.sg.vendingmachinespringmvc.service;

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {

        //..

        super(message);

    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }

    //Adding a comment for commit
}
