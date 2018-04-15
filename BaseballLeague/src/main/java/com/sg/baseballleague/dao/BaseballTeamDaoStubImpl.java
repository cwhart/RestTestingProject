package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballTeam;

import java.util.ArrayList;
import java.util.List;

public class BaseballTeamDaoStubImpl implements BaseballTeamDao {

    List<BaseballTeam> teamList = new ArrayList<>();
    BaseballTeam team1;
    BaseballTeam team2;

    public BaseballTeamDaoStubImpl() {
        team1 = new BaseballTeam("Red Sox");
        team1.setHomeCity("Boston");

        teamList.add(team1);

        team2 = new BaseballTeam("Cubs");
        team2.setHomeCity("Chicago");

        teamList.add(team2);
    }

    @Override
    public List<BaseballTeam> retrieveAllTeams() throws BaseballTeamPersistenceException {
        return teamList;
    }

    @Override
    public BaseballTeam addTeam(BaseballTeam teamToAdd) throws BaseballTeamPersistenceException {
        return teamToAdd;
    }

    @Override
    public BaseballTeam updateTeam(BaseballTeam teamToUpdate) throws BaseballTeamPersistenceException {
        return teamToUpdate;
    }

    @Override
    public BaseballTeam removeTeam(BaseballTeam teamToRemove) throws BaseballTeamPersistenceException {
        return teamToRemove;
    }

    @Override
    public BaseballTeam retrieveSingleTeam(String teamName) throws BaseballTeamPersistenceException {
        if(teamList.get(0).getTeamName().equals(teamName)) {
            return team1;
        } else return team2;
    }
}
