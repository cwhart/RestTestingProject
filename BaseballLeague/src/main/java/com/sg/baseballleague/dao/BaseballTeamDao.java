package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.util.List;

public interface BaseballTeamDao {

    public List<BaseballTeam> retrieveAllTeams() throws BaseballTeamPersistenceException;

    public BaseballTeam addTeam(BaseballTeam teamToAdd) throws BaseballTeamPersistenceException;

    public BaseballTeam updateTeam(BaseballTeam teamToUpdate) throws BaseballTeamPersistenceException;

    public BaseballTeam removeTeam(BaseballTeam teamToRemove) throws BaseballTeamPersistenceException;

    public BaseballTeam retrieveSingleTeam(String teamName) throws BaseballTeamPersistenceException;
}
