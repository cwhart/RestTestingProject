package com.sg.view;

import com.sg.dto.EmailContact;
import com.sg.io.UserIO;

import java.util.List;

public class EmailListView {

    private UserIO io;

    public EmailListView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Welcome to Email Contacts System");
        io.print("1. Add an email contact");
        io.print("2. List all email contacts");
        io.print("3. Display an email contact by id");
        io.print("4. Update an email contact");
        io.print("5. Delete an email contact");
        io.print("6. Exit");

        return io.readInt("Please select a menu item between 1 and 6", 1, 6);
    }

    public void createEmailBanner() {
        io.print("=== ADD EMAIL CONTACT ===");
    }

    public EmailContact promptForEmailContactInfo() {
        String firstName = io.readString("Please enter the first name: ");
        String lastName = io.readString("Please enter the last name: ");
        String emailAddress = io.readString("Please enter the email address");

        EmailContact contact = new EmailContact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setEmailAddress(emailAddress);

        return contact;
    }

    public void printAddEmailContactSuccessBanner() {
        io.print("===ADD EMAIL CONTACT SUCCESS===");
    }

    public void printListAllContactsBanner() {
        io.print("===LIST ALL CONTACTS===");
    }

    public void printContactList(List<EmailContact> contacts) {
        for (EmailContact contact : contacts) {
            io.print(contact.toString());
        }
    }

    public void printDeleteContactBanner() {
        io.print("===DELETE CONTACT===");
    }

    public int promptForEmailContactId() {
        return io.readInt("Please enter the ID of the contact: ");
    }

    public void printContact(EmailContact contact) {
        io.print(contact.toString());
    }

    public boolean promptForDeleteConfirmation() {
        String confirmation = io.readString("Please enter y to confirm delete.");
        return confirmation.equalsIgnoreCase("y");
    }

    public void printErrorMessage(String message) {
        io.print(message);
    }
}
