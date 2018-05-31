package com.sg.baseball.dao.interfaces;


import com.sg.baseball.dto.PlayerPosition;
import com.sg.baseball.dto.Team;

import java.util.List;

public interface PlayerPositionDao {

    public PlayerPosition create(PlayerPosition playerPosition);

    public PlayerPosition read(Long id);  //uppercase Long can be null; long cannot.

    //public void update(PlayerPosition playerPosition);

    public void delete(PlayerPosition playerPosition);

    //public List<PlayerPosition> getAll(int limit, int offset); //for paging. Limit is how many to get at a time, offset is which
    //one to start at, defaults to 0.

}
