package com.sg.superhero.dao;

import com.sg.superhero.dao.interfaces.SuperDao;
import com.sg.superhero.dto.Location;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Sighting;
import com.sg.superhero.dto.Super;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SuperDaoImpl implements SuperDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public SuperDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Super create(Super superPerson) {
        final String QUERY = "insert into super (name, description ) VALUES (?, ?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                superPerson.getName(),
                superPerson.getDescription()

        );
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        superPerson.setId(insertedId);

        //Return team with id
        return superPerson;
    }

    @Override
    public Super retrieve(Long id) {
        final String QUERY = "select * from super where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new SuperMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void update(Super superPerson) {

        final String QUERY = "update super set name = ?, description = ? where id=?";

        jdbcTemplate.update(QUERY,
                superPerson.getName(),
                superPerson.getDescription(),
                superPerson.getId()
        );

    }

    @Override
    public void delete(Super superPerson) {

        final String QUERY = "delete from super where id=?";

        jdbcTemplate.update(QUERY, superPerson.getId());

    }

    @Override
    public List<Super> retrieveAll(int limit, int offset) {
        final String QUERY = "select * from super limit ? offset ?";

        return jdbcTemplate.query(QUERY, new SuperDaoImpl.SuperMapper(), limit, offset);
    }

    @Override
    public List<Super> retrieveSupersByOrganization(Organization organization, int i, int i1) {
        return null;
    }

    @Override
    public List<Super> retrieveSupersByLocation(Location location, int i, int i1) {
        return null;
    }

    @Override
    public List<Super> retrieveSupersBySighting(Sighting sighting, int i, int i1) {
        return null;
    }

    private class SuperMapper implements RowMapper<Super> {
        @Override
        public Super mapRow(ResultSet resultSet, int i) throws SQLException {

            Super superPerson = new Super();
            superPerson.setId(resultSet.getLong("id"));
            superPerson.setName(resultSet.getString("name"));
            superPerson.setDescription(resultSet.getString("description"));
            return superPerson;

        }
    }
}
