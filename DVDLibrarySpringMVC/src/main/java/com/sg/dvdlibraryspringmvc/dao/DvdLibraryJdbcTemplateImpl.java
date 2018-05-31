package com.sg.dvdlibraryspringmvc.dao;

import com.sg.dvdlibraryspringmvc.model.Director;
import com.sg.dvdlibraryspringmvc.model.Dvd;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.sql.Date.valueOf;

public class DvdLibraryJdbcTemplateImpl implements DvdListDao {

    private JdbcTemplate jdbcTemplate;
    //private DirectorDao directorDao = new DirectorJdbcTemplateImpl();

    public DvdLibraryJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    private static final String SQL_INSERT_DVD
            = "insert into dvd (dvd_title, release_date, director_id, rating, notes) "
            + " values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_DVD
            = "delete from dvd where dvd_id = ?";

    private static final String SQL_UPDATE_DVD
            = "update dvd set dvd_title = ?, release_date = ?, director_id = ?, rating = ?, notes = ? "
            + "where dvd_id = ?";

    private static final String SQL_SELECT_DVD
            = "select * from dvd inner join director on dvd.director_id=director.director_id where dvd_id = ?";

    private static final String SQL_SELECT_ALL_DVDS
            = "select * from dvd_library.dvd" +
            " left join director on dvd.director_id=director.director_id;";

    private static final String SQL_SELECT_ALL_DVDS_BY_DIRECTOR
            = "select * from dvd inner join director on dvd.director_id=director.director.id" +
            " where director_id = ?";

    private static final String SQL_SELECT_DIRECTOR
            = "select * from director where director_id = ?";

    private static final String SQL_SELECT_DIRECTOR_BY_LASTNAME
            = "select * from director where last_name = ?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_INSERT_DVD,
                dvd.getDvdTitle(),
                valueOf(dvd.getReleaseDate()),
                dvd.getDirector().getDirector_id(),
                dvd.getRating(),
                dvd.getNotes());

        int dvdId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        dvd.setDvdId(dvdId);
    }

    @Override
    public void removeDvd(int dvdId) {
        jdbcTemplate.update(SQL_DELETE_DVD, dvdId);

    }

    @Override
    public void updateDvd(Dvd dvd) {
        jdbcTemplate.update(SQL_UPDATE_DVD,
                dvd.getDvdTitle(),
                valueOf(dvd.getReleaseDate()),
                dvd.getDirector().getDirector_id(),
                dvd.getRating(),
                dvd.getNotes(),
                dvd.getDvdId());

    }

    @Override
    public List<Dvd> getAllDvds() {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS, new DvdMapper());
    }

    @Override
    public Dvd getDvdById(int dvdId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DVD, new DvdMapper(), dvdId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    public List<Dvd> searchDvdsByDirectorId(int directorId) {
        return jdbcTemplate.query(SQL_SELECT_ALL_DVDS_BY_DIRECTOR, new DvdMapper(), directorId);
    }

    /*public Director getDirectorById(int directorId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR, new DirectorMapper(), directorId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public Director getDirectorByLastname(String lastName) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_DIRECTOR_BY_LASTNAME,
                    new DvdLibraryJdbcTemplateImpl.DirectorMapper(), lastName);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }*/

    //@Override
    //public List<Dvd> searchDvds(Map<SearchTerm, String> criteria) {
    //    return null;
    //}

    private static final class DvdMapper implements RowMapper<Dvd> {

        @Override
        public Dvd mapRow(ResultSet rs, int i) throws SQLException {
            Dvd dvd = new Dvd();
            dvd.setDvdId(rs.getInt("dvd_id"));
            dvd.setDvdTitle(rs.getString("dvd_title"));
            dvd.setReleaseDate((rs.getDate("release_date").toLocalDate()));
            int directorID = rs.getInt("director_id");
            String directorFirstName = rs.getString("first_name");
            String directorLastName = rs.getString("last_name");
            Director thisDirector = new Director(directorLastName);
            thisDirector.setFirst_name(directorFirstName);
            thisDirector.setDirector_id(directorID);
            dvd.setDirector(thisDirector);
            dvd.setRating(rs.getString("rating"));
            dvd.setNotes(rs.getString("notes"));
            return dvd;
        }




    }




}
