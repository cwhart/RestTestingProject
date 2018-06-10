package com.sg.baseball.viewmodels.player.create;

public class CreatePlayerCommandModel {

    //form backed object/command model. Fields represent fields on the screen.

    private String first;
    private String last;
    private Long teamId;
    private Long[] positionIds;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long[] getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(Long[] positionIds) {
        this.positionIds = positionIds;
    }
}
