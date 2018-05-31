package com.sg.baseball.service.interfaces;

import com.sg.baseball.dto.Team;

import java.util.List;

public interface TeamService {

    public Team create(Team team);

    public Team read(Long id);  //uppercase Long can be null; long cannot.

    public void update(Team team);

    public void delete(Team team);

    public List<Team> getAll(int limit, int offset);
}
