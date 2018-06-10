package com.sg.baseball.webservice.interfaces;

import com.sg.baseball.dto.Player;
import com.sg.baseball.viewmodels.player.create.CreatePlayerCommandModel;
import com.sg.baseball.viewmodels.player.create.CreatePlayerViewModel;
import com.sg.baseball.viewmodels.player.edit.EditPlayerCommandModel;
import com.sg.baseball.viewmodels.player.edit.EditPlayerViewModel;
import com.sg.baseball.viewmodels.player.list.PlayerListViewModel;
import com.sg.baseball.viewmodels.player.profile.PlayerProfileViewModel;

public interface PlayerWebService {

    //1 method per page. Similar to controller previously.

    public PlayerListViewModel getPlayerListViewModel(Integer offset);

    public PlayerProfileViewModel getPlayerProfileViewModel(Long id); //takes in ID since that's what is sent to the
    //page via URL so it can load.

    //These two are input/output for create player
    public CreatePlayerViewModel getCreatePlayerViewModel();
    public Player saveCreatePlayerCommandModel(CreatePlayerCommandModel commandModel);

    //do not re-use create for edit. Make models for edit. Same pattern.
    public EditPlayerViewModel getEditPlayerViewModel(Long id);
    public void saveEditPlayerCommandModel(EditPlayerCommandModel commandModel);

}
