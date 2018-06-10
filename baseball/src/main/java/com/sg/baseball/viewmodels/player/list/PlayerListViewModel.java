package com.sg.baseball.viewmodels.player.list;

import com.sg.baseball.viewmodels.player.profile.PlayerProfileViewModel;

import java.util.List;

public class PlayerListViewModel {

    private int[] pageNumbers;
    private int selectedPage;
    private List<PlayerProfileViewModel> players;

    public int[] getPageNumbers() {
        return pageNumbers;
    }

    public void setPageNumbers(int[] pageNumbers) {
        this.pageNumbers = pageNumbers;
    }

    public int getSelectedPage() {
        return selectedPage;
    }

    public void setSelectedPage(int selectedPage) {
        this.selectedPage = selectedPage;
    }

    public List<PlayerProfileViewModel> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerProfileViewModel> players) {
        this.players = players;
    }
}
