package com.sg.baseball.service.interfaces;


import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;

import java.util.List;

public interface PositionService {

    public Position create(Position position);

    public Position read(Long id);  //uppercase Long can be null; long cannot.

    public void update(Position position);

    public void delete(Position position);

    public List<Position> getAll(int limit, int offset); //for paging. Limit is how many to get at a time, offset is which
    //one to start at, defaults to 0.

    public List<Position> getPositionByPlayer(Player player, int limit, int offset);

}
