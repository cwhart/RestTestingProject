package com.sg.baseball.dao.interfaces;


import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;

import java.util.List;

public interface PlayerDao {

    public Player create(Player player);

    public Player read(Long id);  //uppercase Long can be null; long cannot.

    public void update(Player player);

    public void delete(Player player);

    public List<Player> getAll(int limit, int offset);

    //DAO typically comprised of ways to query the data, i.e. getPlayerByTeamCity, getPlayerByTeamAndFirstName, etc.
    //Most common use case for a one-to-many relationship.
    public List<Player> getPlayerByTeam(Team team, int limit, int offset);

    public List<Player> getPlayerByPosition(Position position, int limit, int offset);

}
