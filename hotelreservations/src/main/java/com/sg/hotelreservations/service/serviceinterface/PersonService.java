package com.sg.hotelreservations.service.serviceinterface;

import com.sg.hotelreservations.dto.AddOnBillDetail;
import com.sg.hotelreservations.dto.Person;

import java.util.List;

public interface PersonService {

    public Person create(Person person);
    public Person retrieve(Long id);
    public void update(Person person);
    public void delete(Person person);
    public List<Person> retrieveAll(int limit, int offset);
}
