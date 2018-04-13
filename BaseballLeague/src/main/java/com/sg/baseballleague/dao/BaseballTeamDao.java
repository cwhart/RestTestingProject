package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.util.List;

public interface BaseballTeamDao {

    public List<BaseballTeam> retrieveAllTeams();

    public BaseballTeam addTeam(BaseballTeam teamToAdd);

    public BaseballTeam updateTeam(BaseballTeam teamToUpdate);

    public BaseballTeam removeTeam(BaseballTeam teamToRemove);

    public BaseballTeam retrieveSingleTeam(String teamName);
}
