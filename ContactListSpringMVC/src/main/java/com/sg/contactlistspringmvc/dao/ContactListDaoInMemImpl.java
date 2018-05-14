package com.sg.contactlistspringmvc.dao;

import com.sg.contactlistspringmvc.model.Contact;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ContactListDaoInMemImpl implements ContactListDao{

    private Map<Long, Contact> contactMap = new HashMap<>();
    private static long contactIdCounter = 0;

    @Override
    public Contact addContact(Contact contact) {
        contact.setContactId(contactIdCounter);
        contactIdCounter++;
        contactMap.put(contact.getContactId(), contact);
        return contact;
    }

    @Override
    public void removeContact(long contactId) {
        contactMap.remove(contactId);
    }

    @Override
    public void updateContact(Contact contact) {
        contactMap.put(contact.getContactId(), contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        Collection<Contact> c = contactMap.values();
        return new ArrayList(c);
    }

    @Override
    public Contact getContactById(long contactId) {
        return contactMap.get(contactId);
    }

    @Override
    public List<Contact> searchContacts(Map<SearchTerm, String> criteria) {
        String firstNameSearchCriteria = criteria.get(SearchTerm.FIRST_NAME);
        String lastNameSearchCriteria = criteria.get(SearchTerm.LAST_NAME);
        String companySearchCriteria = criteria.get(SearchTerm.COMPANY);
        String phoneSearchCriteria = criteria.get(SearchTerm.PHONE);
        String emailSearchCriteria = criteria.get(SearchTerm.EMAIL);

        Predicate<Contact> firstNameMatchPredicate;
        Predicate<Contact> lastNameMatchPredicate;
        Predicate<Contact> companyMatchPredicate;
        Predicate<Contact> phoneMatchPredicate;
        Predicate<Contact> emailMatchPredicate;

        Predicate<Contact> truePredicate = (c) -> {
            return true;
        };

        if (firstNameSearchCriteria == null || firstNameSearchCriteria.isEmpty()) {
            firstNameMatchPredicate = truePredicate;
        } else {
            firstNameMatchPredicate = (c) -> c.getFirstName().equals(firstNameSearchCriteria);
        }

        if (lastNameSearchCriteria == null || lastNameSearchCriteria.isEmpty()) {
            lastNameMatchPredicate = truePredicate;
        } else {
            lastNameMatchPredicate = (c) -> c.getLastName().equals(lastNameSearchCriteria);
        }

        if (companySearchCriteria == null || companySearchCriteria.isEmpty()) {
            companyMatchPredicate = truePredicate;
        } else {
            companyMatchPredicate = (c) -> c.getCompany().equals(companySearchCriteria);
        }

        if (phoneSearchCriteria == null || phoneSearchCriteria.isEmpty()) {
            phoneMatchPredicate = truePredicate;
        } else {
            phoneMatchPredicate = (c) -> c.getPhone().equals(phoneSearchCriteria);
        }

        if (emailSearchCriteria == null || emailSearchCriteria.isEmpty()) {
            emailMatchPredicate = truePredicate;
        } else {
            emailMatchPredicate = (c) -> c.getEmail().equals(emailSearchCriteria);
        }

        return contactMap.values().stream()
                .filter(firstNameMatchPredicate
                .and(lastNameMatchPredicate)
                .and(companyMatchPredicate)
                .and(phoneMatchPredicate)
                .and(emailMatchPredicate))
        .collect(Collectors.toList());

    }
}
