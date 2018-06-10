package com.sg.baseball.viewmodels.player.profile;

import java.util.List;

public class PlayerProfileViewModel {

    private String first;
    private String last;
    private String teamName;
    private Long teamId;
    private Long playerId;
    private List<PositionViewModel> positions;

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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public List<PositionViewModel> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionViewModel> positions) {
        this.positions = positions;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }
}
