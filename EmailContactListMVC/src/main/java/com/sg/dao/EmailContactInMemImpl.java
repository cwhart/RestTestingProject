package com.sg.dao;

import com.sg.dto.EmailContact;
import com.sg.service.EmailListService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailContactInMemImpl implements EmailContactDao {

    private Map<Integer, EmailContact> contacts = new HashMap<>();
    private int idCounter = 0;

    @Override
    public EmailContact createEmailContact(EmailContact emailContact) {
        emailContact.setId(retrieveNextEmailContactId());
        contacts.put(emailContact.getId(), emailContact);
        return emailContact;
    }

    @Override
    public List<EmailContact> retrieveAllContacts() {
        return new ArrayList<>(contacts.values());
    }

    @Override
    public EmailContact retrieveContactById(int id) {
        return contacts.get(id);
    }

    @Override
    public EmailContact updateEmailContact(EmailContact emailContact) {
        return null;
    }

    @Override
    public void removeEmailContactById(int id) {


    }

    private int retrieveNextEmailContactId() {
        return ++idCounter;
    }
}
