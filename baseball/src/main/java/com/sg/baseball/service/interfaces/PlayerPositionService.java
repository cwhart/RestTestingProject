package com.sg.baseball.service.interfaces;

import com.sg.baseball.dto.PlayerPosition;
import com.sg.baseball.dto.Team;

import java.util.List;

public interface PlayerPositionService {

    public PlayerPosition create(PlayerPosition playerPosition);

    public PlayerPosition read(Long id);  //uppercase Long can be null; long cannot.

    public void delete(PlayerPosition playerPosition);


}
