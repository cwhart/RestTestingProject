package com.sg.baseball.dao.interfaces;


import com.sg.baseball.dto.Team;

import java.util.List;

public interface TeamDao {

    public Team create(Team team);

    public Team read(Long id);  //uppercase Long can be null; long cannot.

    public void update(Team team);

    public void delete(Team team);

    public List<Team> getAll(int limit, int offset); //for paging. Limit is how many to get at a time, offset is which
    //one to start at, defaults to 0.

}
