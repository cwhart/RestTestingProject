package com.sg.baseball.viewmodels.player.edit;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class EditPlayerCommandModel {

    //form backed object/command model. Fields represent fields on the screen.

    private Long id;

    @NotEmpty(message = "Please enter a value for first name")
    @Length(min = 4, message = "Your name is too short")
    private String first;
    private String last;

    @NotEmpty(message = "You must select a team")
    private Long teamId;

    @Length(min = 1, message = "You must select a position")
    private Long[] positionIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
