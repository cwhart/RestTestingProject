package com.sg.dvdlibrary.service;

public class DvdLibraryDuplicateTitleException extends Exception {

    public DvdLibraryDuplicateTitleException(String message) {
        super(message);
    }

    public DvdLibraryDuplicateTitleException(String message, Throwable cause) {
        super(message, cause);
    }
}
