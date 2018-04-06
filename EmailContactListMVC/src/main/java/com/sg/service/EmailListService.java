package com.sg.service;

import com.sg.dto.EmailContact;

import java.util.List;

public interface EmailListService {

    //For now just do pass through methods

    EmailContact createEmailContact(EmailContact emailContact) throws EmailContactDataValidationException;

    List<EmailContact> retrieveAllContacts();

    EmailContact retrieveContactById (int id);

    EmailContact updateEmailContact(EmailContact emailContact);

    void removeEmailContactById(int id);
}
