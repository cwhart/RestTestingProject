package com.sg.baseballleague.dao;

import com.sg.baseballleague.dto.BaseballPlayer;
import com.sg.baseballleague.dto.BaseballTeam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseballTeamDaoImpl implements BaseballTeamDao {

    Map<String, BaseballTeam> mapOfTeams = new HashMap<>();

    @Override
    public List<BaseballTeam> retrieveAllTeams() {
        List<BaseballTeam> teamList = new ArrayList<>();

        for (BaseballTeam currentTeam : mapOfTeams.values()) {
            teamList.add(currentTeam);
        } return teamList;
    }

    @Override
    public BaseballTeam addTeam(BaseballTeam teamToAdd) {
        BaseballTeam newTeam = mapOfTeams.put(teamToAdd.getTeamName(), teamToAdd);
        return newTeam;
    }

    @Override
    public BaseballTeam updateTeam(BaseballTeam teamToUpdate) {

        BaseballTeam updatedTeam = mapOfTeams.put(teamToUpdate.getTeamName(), teamToUpdate);
        return mapOfTeams.get(updatedTeam.getTeamName());
    }

    @Override
    public BaseballTeam removeTeam(BaseballTeam teamToRemove) {
        String teamNumber = teamToRemove.getTeamName();
        mapOfTeams.remove(teamNumber);

        return teamToRemove;
    }

    @Override
    public BaseballTeam retrieveSingleTeam(String teamName) {
        return mapOfTeams.get(teamName);
    }
}
