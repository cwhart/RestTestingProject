package com.sg.baseballleague.dto;

import java.util.List;

public class BaseballTeam {

    private String teamName;
    private String homeCity;


    public BaseballTeam (String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getHomeCity() {
        return homeCity;
    }

    public void setHomeCity(String homeCity) {
        this.homeCity = homeCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseballTeam that = (BaseballTeam) o;

        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) return false;
        return homeCity != null ? homeCity.equals(that.homeCity) : that.homeCity == null;
    }

    @Override
    public int hashCode() {
        int result = teamName != null ? teamName.hashCode() : 0;
        result = 31 * result + (homeCity != null ? homeCity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseballTeam{" +
                "teamName='" + teamName + '\'' +
                ", homeCity='" + homeCity + '\'' +
                '}';
    }
}
