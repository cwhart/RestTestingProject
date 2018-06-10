package com.sg.baseball.viewmodels.player.edit;

import com.sg.baseball.viewmodels.DropDownItemViewModel;
import com.sg.baseball.viewmodels.player.create.CreatePlayerCommandModel;

import java.util.List;

public class EditPlayerViewModel {

    //Dropdown fields that will be needed on the Create page.
    private List<DropDownItemViewModel> teams;
    private List<DropDownItemViewModel> positions;
    private EditPlayerCommandModel commandModel; //IGNORE FOR NOW.

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

    public EditPlayerCommandModel getCommandModel() {
        return commandModel;
    }

    public void setCommandModel(EditPlayerCommandModel commandModel) {
        this.commandModel = commandModel;
    }
}
