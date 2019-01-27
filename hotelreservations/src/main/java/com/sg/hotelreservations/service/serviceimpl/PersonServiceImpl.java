package com.sg.hotelreservations.service.serviceimpl;

import com.sg.hotelreservations.dao.daoInterface.AddOnBillDetailDAO;
import com.sg.hotelreservations.dao.daoInterface.PersonDAO;
import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Person;
import com.sg.hotelreservations.service.serviceinterface.AddOnBillDetailService;
import com.sg.hotelreservations.service.serviceinterface.PersonService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Inject
    PersonDAO personDAO;

    @Override
    public Person create(Person person) {
        return personDAO.create(person);
    }

    @Override
    public Person retrieve(Long id) {
        return personDAO.retrieve(id);
    }

    @Override
    public void update(Person person) {
        personDAO.update(person);
    }

    @Override
    public void delete(Person person) {
        personDAO.delete(person);
    }

    @Override
    public List<Person> retrieveAll(int limit, int offset) {
        return personDAO.retrieveAll(limit, offset);
    }

    @Override
    public List<Person> retrieveByName(String firstName, String lastName) {
        return personDAO.retrieveByName(firstName, lastName);
    }
}
