package com.sg.baseball.service.interfaces;

import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;

import java.util.List;

public interface PlayerService {

    public Player create(Player player);

    public Player read(Long id);  //uppercase Long can be null; long cannot.

    public void update(Player player);

    public void delete(Player player);

    public List<Player> getAll(int limit, int offset);

    public List<Player> getPlayerByTeam(Team team, int limit, int offset);

    public List<Player> getPlayerByPosition(Position position, int limit, int offset);
}
