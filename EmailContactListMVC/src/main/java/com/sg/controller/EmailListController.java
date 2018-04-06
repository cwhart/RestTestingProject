package com.sg.controller;

import com.sg.dto.EmailContact;
import com.sg.service.EmailContactDataValidationException;
import com.sg.service.EmailListService;
import com.sg.view.EmailListView;

import java.util.List;

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

        //get menu selection


        boolean keepGoing = true;
        //run a switch statement on that selection to kick off the flow for that menu item.
        do {
            int menuSelection = view.printMenuAndGetSelection();
            switch (menuSelection) {

                case 1:
                    addEmailContact();
                    break;
                case 2:
                    listAllEmailContacts();
                    break;
                case 3:

                    displayEmailContactByID(promptUserForContactId());
                    break;
                case 4:
                    updateEmailContact();
                    break;
                case 5:
                    deleteEmailContact();
                    break;
                case 6:
                    exit();
                    keepGoing = false;
                    break;
            }
        } while(keepGoing);
    }



    private void addEmailContact() {
        //print starting baanner
        view.createEmailBanner();
        //prompt the user for email contact information
        EmailContact contactToAdd = view.promptForEmailContactInfo();
        //save the contact
        try {
            service.createEmailContact(contactToAdd);
            //print success banner
            view.printAddEmailContactSuccessBanner();
        } catch (EmailContactDataValidationException e) {
            view.printErrorMessage(e.getMessage());
        };
    }

    private void  listAllEmailContacts() {
        //show a banner
        view.printListAllContactsBanner();
        //retrieve all contacts
        List<EmailContact> emailContacts = service.retrieveAllContacts();
        //display contacts to user
        view.printContactList(emailContacts);
    }

    private void displayEmailContactByID() {
        int id = view.promptForEmailContactId();
        //retrieve the contact by id
        EmailContact contact = service.retrieveContactById(id);
        //display the contact
        view.printContact(contact);

    }

    private void updateEmailContact() {

    }

    private void deleteEmailContact() {
        //show starting banner for delete
        view.printDeleteContactBanner();
        //show a list of existing contacts
        listAllEmailContacts();
        int id = view.promptForEmailContactId();
        displayEmailContactByID();
        //prompt the user for the ID of the email contact they wish to delete
        //and display for confirmation
        displayEmailContactByID();
        if (view.promptForDeleteConfirmation()) {
            service.removeEmailContactById();
        } else {
            return;
        }
        //prompt for confirmation

        //delete the contact

        //show success banner

    }

    private void exit() {

    }

    private int promptUserForContactId() {
        int id = view.promptForEmailContactId();
        return id;
    }
}
