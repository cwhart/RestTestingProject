package com.sg.controller;

import com.sg.service.EmailListService;
import com.sg.view.EmailListView;

public class EmailListController {

    //The controller depends on the view and the service
    private EmailListView view;
    private EmailListService service;

    //The constructor is what makes dependency injection possible by accepting the dependencies
    //as parameters and then setting the dependencies values when the object is built.
    //This allows us to swap dependencies more easily as needed.
    public EmailListController(EmailListView view, EmailListService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        System.out.println("Hello");
    }
}
