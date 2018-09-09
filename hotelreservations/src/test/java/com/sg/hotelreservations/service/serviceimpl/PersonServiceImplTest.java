package com.sg.hotelreservations.service.serviceimpl;

import com.sg.TestHelper;
import com.sg.hotelreservations.dao.daoInterface.PersonDAO;
import com.sg.hotelreservations.dto.Person;
import com.sg.hotelreservations.service.serviceinterface.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class PersonServiceImplTest {

    @Inject
    PersonService personService;

    @Inject
    TestHelper testHelper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void create() {
        //Arrange
        Person person = testHelper.createTestPerson();
        personService.create(person);

        //Act
        Person createdPerson = personService.create(person);

        //Assert
        assert (createdPerson.getId() != null);
        assertEquals(createdPerson, person);
    }

    @Test
    public void retrieve() {

        //Arrange
        Person person = testHelper.createTestPerson();
        personService.create(person);

        //Act
        Person readPerson = personService.retrieve(person.getId());

        //Assert
        assert (readPerson.getId() != null);
        assertEquals(person.getId(), readPerson.getId());
        assertEquals(person.getFirstName(), readPerson.getFirstName());
        assertEquals(person.getLastName(), readPerson.getLastName());
        assertEquals(person.getDateOfBirth(), readPerson.getDateOfBirth());
        assertEquals(person.getPhoneNo(), readPerson.getPhoneNo());
        assertEquals(person.getEmail(), readPerson.getEmail());

    }

    @Test
    public void update() {

        //Arrange
        Person person = testHelper.createTestPerson();
        personService.create(person);

        //Change some stuff
        person.setFirstName("Homer J");
        person.setLastName("Simpson");
        person.setEmail("homer@simpsons.com");

        //Act
        personService.update(person);

        //Assert
        Person readPerson = personService.retrieve(person.getId());
        assert "Homer J".equals(readPerson.getFirstName());
        assert "Simpson".equals(readPerson.getLastName());
        assert "homer@simpsons.com".equals(readPerson.getEmail());

    }

    @Test
    public void delete() {

        //Arrange
        Person person = testHelper.createTestPerson();
        personService.create(person);

        //Act
        personService.delete(person);

        Person readPerson = personService.retrieve(person.getId());

        //Assert
        assert (null == readPerson);
    }

    @Test
    public void retrieveAll() {

        //Arrange
        testHelper.createMultiplePersons(25);

        //Act
        List<Person> personList = personService.retrieveAll(Integer.MAX_VALUE, 0);

        //Assert
        assert personList.size() == 25;
    }
}