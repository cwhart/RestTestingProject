package com.sg.baseball.service;

import com.sg.baseball.dao.interfaces.PlayerDao;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.PlayerService;

import javax.inject.Inject;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    PlayerDao playerDao;

    @Inject
    public PlayerServiceImpl(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public Player create(Player player) {
        return playerDao.create(player);
    }

    @Override
    public Player read(Long id) {
        return playerDao.read(id);
    }

    @Override
    public void update(Player player) {
        playerDao.update(player);
    }

    @Override
    public void delete(Player player) {
        playerDao.delete(player);
    }

    @Override
    public List<Player> getAll(int limit, int offset) {
        return playerDao.getAll(limit, offset);
    }

    @Override
    public List<Player> getPlayerByPosition(Position position, int limit, int offset) {
        return playerDao.getPlayerByPosition(position, limit, offset);
    }

    @Override
    public List<Player> getPlayerByTeam(Team team, int limit, int offset) {
        return null;
    }
}
