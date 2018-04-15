package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballTeam;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BaseballTeamDaoTest {

    BaseballTeamDao dao = new BaseballTeamDaoImpl();

    @Before
    public void setUp() throws Exception {
        List<BaseballTeam> teams = dao.retrieveAllTeams();
        for (BaseballTeam currentTeam : teams) {
            dao.removeTeam(currentTeam);
        }

    }

    @Test
    public void testCreateAndRetrieveAllTeams() throws BaseballTeamPersistenceException {
        BaseballTeam newTeam1 = new BaseballTeam("Yankees");
        newTeam1.setHomeCity("New York");
        dao.addTeam(newTeam1);

        BaseballTeam newTeam2 = new BaseballTeam("Cubs");
        newTeam2.setHomeCity("Chicago");
        dao.addTeam(newTeam2);

        assertEquals(2, dao.retrieveAllTeams().size());


    }

    @Test
    public void testUpdateTeam() throws BaseballTeamPersistenceException {
        BaseballTeam newTeam1 = new BaseballTeam("Yankees");
        newTeam1.setHomeCity("New York");
        dao.addTeam(newTeam1);

        newTeam1.setHomeCity("Newark");
        dao.updateTeam(newTeam1);

        BaseballTeam actualResults = dao.retrieveSingleTeam("Yankees");
        assertEquals(newTeam1, actualResults);


    }

    @Test
    public void removeTeam() throws BaseballTeamPersistenceException {
        BaseballTeam newTeam1 = new BaseballTeam("Yankees");
        newTeam1.setHomeCity("New York");
        dao.addTeam(newTeam1);

        BaseballTeam newTeam2 = new BaseballTeam("Cubs");
        newTeam2.setHomeCity("Chicago");
        dao.addTeam(newTeam2);

        dao.removeTeam(newTeam1);
        assertNull(dao.retrieveSingleTeam("Yankees"));
        assertEquals(1, dao.retrieveAllTeams().size());

        dao.removeTeam(newTeam2);
        assertNull(dao.retrieveSingleTeam("Cubs"));
        assertEquals(0, dao.retrieveAllTeams().size());

    }

    @Test
    public void retrieveSingleTeam() throws BaseballTeamPersistenceException {
        BaseballTeam newTeam1 = new BaseballTeam("Yankees");
        newTeam1.setHomeCity("New York");
        dao.addTeam(newTeam1);

        BaseballTeam newTeam2 = new BaseballTeam("Cubs");
        newTeam2.setHomeCity("Chicago");
        dao.addTeam(newTeam2);

        BaseballTeam teamRetrieved = dao.retrieveSingleTeam("Yankees");
        assertEquals(newTeam1, teamRetrieved);

        teamRetrieved = dao.retrieveSingleTeam("Cubs");
        assertEquals(newTeam2, teamRetrieved);
    }
}