package com.sg.baseball.service;

import com.sg.baseball.dao.interfaces.TeamDao;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.TeamService;

import javax.inject.Inject;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    private TeamDao teamDao;

    @Inject
    public TeamServiceImpl(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    @Override
    public Team create(Team team) {
        return teamDao.create(team);
    }

    @Override
    public Team read(Long id) {
        return teamDao.read(id);
    }

    @Override
    public void update(Team team) {
        teamDao.update(team);

    }

    @Override
    public void delete(Team team) {

        teamDao.delete(team);
    }

    @Override
    public List<Team> getAll(int limit, int offset) {
        return teamDao.getAll(limit, offset);
    }
}
