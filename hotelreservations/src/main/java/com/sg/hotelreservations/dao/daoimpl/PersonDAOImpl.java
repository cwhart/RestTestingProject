package com.sg.hotelreservations.dao.daoimpl;

import com.sg.hotelreservations.dao.daoInterface.PersonDAO;
import com.sg.hotelreservations.dao.daoInterface.RoomDAO;
import com.sg.hotelreservations.dto.Person;
import com.sg.hotelreservations.dto.Room;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public PersonDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Person create(Person person) {

        String dateOfBirth = null;
        String phone = null;
        String email = null;

        if(person.getDateOfBirth() != null) {
            dateOfBirth = person.getDateOfBirth().toString();
        }
        if(person.getPhoneNo() != null) {
            phone = person.getPhoneNo();
        }

        if(person.getEmail() != null) {
            email = person.getEmail();
        }

        final String QUERY = "insert into person (firstname, lastname, dateofbirth, phone, email ) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(QUERY,
                person.getFirstName(),
                person.getLastName(),
                dateOfBirth,
                phone,
                email

        );

        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        person.setId(insertedId);

        //Return team with id
        return person;
    }

    @Override
    public Person retrieve(Long id) {

        final String QUERY = "select * from person where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new PersonMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Person person) {

        final String QUERY = "update person set firstname = ?, lastname = ?, dateofbirth = ?, phone = ?, email = ? where id=?";

        jdbcTemplate.update(QUERY,
                person.getFirstName(),
                person.getLastName(),
                person.getDateOfBirth().toString(),
                person.getPhoneNo(),
                person.getEmail(),
                person.getId()
                );

    }

    @Override
    public void delete(Person person) {

        final String QUERY = "delete from person where id=?";

        jdbcTemplate.update(QUERY, person.getId());

    }

    @Override
    public List<Person> retrieveAll(int limit, int offset) {

        final String QUERY = "select * from person limit ? offset ?";

        List<Person> returnList = jdbcTemplate.query(QUERY, new PersonMapper(), limit, offset);
        return returnList;
    }

    @Override
    public List<Person> retrieveByName(String firstName, String lastName) {
        final String QUERY = "select * from person where firstname = ? and lastname = ?";

        List<Person> returnList = jdbcTemplate.query(QUERY, new PersonMapper(), firstName, lastName);
        return returnList;

    }

    private class PersonMapper implements RowMapper<Person> {
        @Override
        public Person mapRow(ResultSet resultSet, int i) throws SQLException {

            Person person = new Person();
            person.setId(resultSet.getLong("id"));
            person.setFirstName(resultSet.getString("firstname"));
            person.setLastName(resultSet.getString("lastname"));
            if(resultSet.getString("dateofbirth") != null) {
                person.setDateOfBirth((resultSet.getString("dateofbirth")));
            }
            if (resultSet.getString("phone") != null) {
                person.setPhoneNo(resultSet.getString("phone"));
            }
            if (resultSet.getString("email") != null) {
                person.setEmail(resultSet.getString("email"));
            }
            return person;

        }
    }
}
