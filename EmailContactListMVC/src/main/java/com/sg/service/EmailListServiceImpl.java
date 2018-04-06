package com.sg.service;

import com.sg.dao.EmailContactDao;
import com.sg.dto.EmailContact;

import java.util.List;

public class EmailListServiceImpl implements EmailListService {

    private EmailContactDao dao;

    public EmailListServiceImpl(EmailContactDao dao) {
        this.dao = dao;
    }

    @Override
    public EmailContact createEmailContact(EmailContact emailContact) throws EmailContactDataValidationException {
        return dao.createEmailContact(emailContact);
    }

    @Override
    public List<EmailContact> retrieveAllContacts() {
        return dao.retrieveAllContacts();
    }

    @Override
    public EmailContact retrieveContactById(int id) {
        return dao.retrieveContactById(id);
    }

    @Override
    public EmailContact updateEmailContact(EmailContact emailContact) {
        return null;
    }

    @Override
    public void removeEmailContactById(int id) {

    }

    private void validateEmailContactData (EmailContact contact) throws EmailContactDataValidationException {
        if(contact.getFirstName().equals("") || contact.getFirstName()==null
                || contact.getLastName() == null || contact.getLastName().equals("")) {
            throw new EmailContactDataValidationException("First and Last name must have values.");
        }

    }

    private void validateEmailAddress(EmailContact contact) throws EmailContactDataValidationException {
        if(!(contact.getEmailAddress().contains("@") && contact.getEmailAddress().contains("."))) {
            throw new EmailContactDataValidationException("Email address is invalid.");
        }

    }
}
