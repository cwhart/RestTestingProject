package com.sg.baseball.service;

import com.sg.baseball.dao.interfaces.PlayerPositionDao;
import com.sg.baseball.dto.PlayerPosition;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.PlayerPositionService;

import javax.inject.Inject;
import java.util.List;

public class PlayerPositionServiceImpl implements PlayerPositionService {

    PlayerPositionDao playerPositionDao;

    @Inject
    public PlayerPositionServiceImpl(PlayerPositionDao playerPositionDao) {
        this.playerPositionDao = playerPositionDao;
    }

    @Override
    public PlayerPosition create(PlayerPosition playerPosition) {
        return playerPositionDao.create(playerPosition);
    }

    @Override
    public PlayerPosition read(Long id) {
        return playerPositionDao.read(id);
    }

    @Override
    public void delete(PlayerPosition playerPosition) {
        playerPositionDao.delete(playerPosition);
    }

}
