package com.sg.baseballleague.dto;

public class BaseballPlayer {

    private int playerNumber;
    private String firstName;
    private String lastName;
    private String teamName;

    public BaseballPlayer(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseballPlayer that = (BaseballPlayer) o;

        if (playerNumber != that.playerNumber) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        return teamName != null ? teamName.equals(that.teamName) : that.teamName == null;
    }

    @Override
    public int hashCode() {
        int result = playerNumber;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BaseballPlayer{" +
                "playerNumber=" + playerNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
