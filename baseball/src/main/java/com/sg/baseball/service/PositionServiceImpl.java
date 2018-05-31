package com.sg.baseball.service;

import com.sg.baseball.dao.interfaces.PositionDao;
import com.sg.baseball.dao.interfaces.TeamDao;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.PositionService;
import com.sg.baseball.service.interfaces.TeamService;

import javax.inject.Inject;
import java.util.List;

public class PositionServiceImpl implements PositionService {

    private PositionDao positionDao;

    @Inject
    public PositionServiceImpl(PositionDao positionDao) {
        this.positionDao = positionDao;
    }

    @Override
    public Position create(Position position) {
        return positionDao.create(position);
    }

    @Override
    public Position read(Long id) {
        return positionDao.read(id);
    }

    @Override
    public void update(Position position) {
        positionDao.update(position);
    }

    @Override
    public void delete(Position position) {
        positionDao.delete(position);
    }

    @Override
    public List<Position> getAll(int limit, int offset) {
        return positionDao.getAll(limit, offset);
    }

    @Override
    public List<Position> getPositionByPlayer(Player player, int limit, int offset) {
        return positionDao.getPositionByPlayer(player, limit, offset);
    }
}
