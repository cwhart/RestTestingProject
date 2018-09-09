package com.sg.hotelreservations.dao.daoInterface;

import com.sg.hotelreservations.dto.Person;

import java.util.List;

public interface PersonDAO {

    public Person create(Person person);
    public Person retrieve(Long id);
    public void update(Person person);
    public void delete(Person person);
    public List<Person> retrieveAll(int limit, int offset);
}
