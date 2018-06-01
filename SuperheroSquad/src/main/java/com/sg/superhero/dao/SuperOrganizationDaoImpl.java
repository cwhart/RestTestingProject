package com.sg.superhero.dao;

import com.sg.superhero.dao.interfaces.SuperOrganizationDao;
import com.sg.superhero.dto.Organization;
import com.sg.superhero.dto.Super;
import com.sg.superhero.dto.SuperOrganization;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SuperOrganizationDaoImpl implements SuperOrganizationDao {

    private JdbcTemplate jdbcTemplate;

    @Inject
    public SuperOrganizationDaoImpl (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SuperOrganization create(SuperOrganization superOrganization) {
        final String QUERY = "insert into superorganization (super_id, organization_id ) VALUES (?, ?)";

        //Get newly inserted id
        jdbcTemplate.update(QUERY,
                superOrganization.getSuperPerson().getId(),
                superOrganization.getOrganization().getId()

        );
        Long insertedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        superOrganization.setId(insertedId);

        //Return team with id
        return superOrganization;
    }//

    @Override
    public SuperOrganization retrieve(long id) {
        final String QUERY = "select * from superorganization where id = ?";

        try{
            return jdbcTemplate.queryForObject(QUERY, new SuperOrganizationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public void delete(SuperOrganization superOrganization) {

        final String QUERY = "delete from superorganization where id=?";

        jdbcTemplate.update(QUERY, superOrganization.getId());

    }


    //MAPPER
    private class SuperOrganizationMapper implements RowMapper<SuperOrganization> {
        @Override
        public SuperOrganization mapRow(ResultSet resultSet, int i) throws SQLException {
            SuperOrganization superOrganization = new SuperOrganization();

            superOrganization.setId(resultSet.getLong("id"));
            //Lazy load superPerson
            Long superId = resultSet.getLong("super_id");
            if (superId > 0) {
                Super superPerson = new Super();
                superPerson.setId(superId);
                superOrganization.setSuperPerson(superPerson);
            }

            //Lazy load organization
            Long organizationId = resultSet.getLong("organization_id");
            if (organizationId > 0) {
                Organization organization = new Organization();
                organization.setId(organizationId);
                superOrganization.setOrganization(organization);
            }

            return superOrganization;
        }
    }

}
