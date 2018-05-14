package com.sg.contactlistspringmvc.controller;

import com.sg.contactlistspringmvc.dao.ContactListDao;
import com.sg.contactlistspringmvc.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@Controller
public class RESTController {

    private ContactListDao dao;

    @Inject
    public RESTController(ContactListDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/ContactListSpringMVC/contact/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Contact getContact(@PathVariable("id") long id) {
        return dao.getContactById(id);
    }

    @RequestMapping(value = "/ContactListSpringMVC/contact", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Contact createContact(@Valid @RequestBody Contact contact) {
        return dao.addContact(contact);
    }

    @RequestMapping(value = "/ContactListSpringMVC/contact/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable("id") long id) {
        dao.removeContact(id);
    }

    @RequestMapping(value = "/ContactListSpringMVC/contact/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContact(@PathVariable("id") long id, @Valid @RequestBody Contact contact)
                throws UpdateIntegrityException{

        if (id != contact.getContactId()) {
            throw
                    new UpdateIntegrityException("Contact Id on URL must match Contact Id in" +
                            "submitted data.");
        }

        //Favor the path variable over the id in the object if they differ

        dao.updateContact(contact);
    }

    @RequestMapping(value = "/ContactListSpringMVC/contacts", method = RequestMethod.GET)
    @ResponseBody
    public List<Contact> getAllContacts() {
        return dao.getAllContacts();
    }
}
