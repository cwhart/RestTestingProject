package com.sg.dao;

import com.sg.dto.EmailContact;

import java.util.List;

public interface EmailContactDao {

    //C - Create
    EmailContact createEmailContact(EmailContact emailContact);

    //R - Read
    List<EmailContact> retrieveAllContacts();

    //R - Read
    EmailContact retrieveContactById (int id);

    //U = Update
    EmailContact updateEmailContact(EmailContact emailContact);

    //D - Delete
    void removeEmailContactById(int id);
}
