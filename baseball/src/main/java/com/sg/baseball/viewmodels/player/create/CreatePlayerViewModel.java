package com.sg.baseball.viewmodels.player.create;

import com.sg.baseball.viewmodels.DropDownItemViewModel;

import java.util.List;

public class CreatePlayerViewModel {

    //Dropdown fields that will be needed on the Create page.
    private List<DropDownItemViewModel> teams;
    private List<DropDownItemViewModel> positions;
    private CreatePlayerCommandModel commandModel; //IGNORE FOR NOW.

    public List<DropDownItemViewModel> getTeams() {
        return teams;
    }

    public void setTeams(List<DropDownItemViewModel> teams) {
        this.teams = teams;
    }

    public List<DropDownItemViewModel> getPositions() {
        return positions;
    }

    public void setPositions(List<DropDownItemViewModel> positions) {
        this.positions = positions;
    }

    public CreatePlayerCommandModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(CreatePlayerCommandModel commandModel) {
        this.commandModel = commandModel;
    }
}
