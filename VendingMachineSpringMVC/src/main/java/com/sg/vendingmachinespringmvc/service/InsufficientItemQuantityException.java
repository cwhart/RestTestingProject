package com.sg.vendingmachinespringmvc.service;

public class InsufficientItemQuantityException extends Exception {

    public InsufficientItemQuantityException(String message) {

        //..

        super(message);

    }

    public InsufficientItemQuantityException(String message, Throwable cause) {
        super(message, cause);
    }

    //Adding a comment for commit

}