package com.sg.superhero.dao;

import com.sg.superhero.dao.interfaces.OrganizationDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Super;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrganizationDaoImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public OrganizationDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Organization create(Organization organization) {
        final String QUERY = "insert into organization (name, description, location_id, phone, email ) " +
                "VALUES (?, ?, ?, ?, ?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                organization.getName(),
                organization.getDescription(),
                organization.getLocation().getId(),
                organization.getPhone(),
                organization.getEmail()

        );
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        organization.setId(insertedId);

        //Return team with id
        return organization;
    }

    @Override
    public Organization retrieve(Long id) {
        final String QUERY = "select * from organization where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new OrganizationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    @Override
    public void update(Organization organization) {

        final String QUERY = "update organization set name = ?, description = ?, location_id = ?, " +
                "phone = ?, email = ? where id=?";

        jdbcTemplate.update(QUERY,
                organization.getName(),
                organization.getDescription(),
                organization.getLocation().getId(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getId()
        );

    }

    @Override
    public void delete(Organization organization) {

        final String QUERY = "delete from organization where id=?";

        jdbcTemplate.update(QUERY, organization.getId());

    }

    @Override
    public List<Organization> retrieveAll(int limit, int offset) {
        final String QUERY = "select * from organization limit ? offset ?";

        return jdbcTemplate.query(QUERY, new OrganizationDaoImpl.OrganizationMapper(), limit, offset);
    }

    @Override
    public List<Organization> retrieveOrganizationsBySuper(Super superPerson, int limit, int offset) {
        final String QUERY = "select * from organization o " +
                "inner join superorganization so on o.id = so.organization_id " +
                "where so.super_id = ? " +
                "LIMIT ? OFFSET ?";
        return jdbcTemplate.query(QUERY, new OrganizationMapper(),
                superPerson.getId(),
                limit,
                offset);
    }

    private class OrganizationMapper implements RowMapper<Organization> {
        @Override
        public Organization mapRow(ResultSet resultSet, int i) throws SQLException {

            Organization organization = new Organization();
            organization.setId(resultSet.getLong("id"));
            organization.setName(resultSet.getString("name"));
            organization.setDescription(resultSet.getString("description"));
            organization.setPhone(resultSet.getString("phone"));
            organization.setEmail(resultSet.getString("email"));

            Long locationId = resultSet.getLong("location_id");

            if(locationId >0) {
                Location location = new Location();
                location.setId(locationId);
                organization.setLocation(location);
            }
            return organization;

        }
    }
}
