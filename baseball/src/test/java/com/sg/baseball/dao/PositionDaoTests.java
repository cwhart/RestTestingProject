package com.sg.baseball.dao;


import com.sg.baseball.TestHelper;
import com.sg.baseball.dao.interfaces.PositionDao;
import com.sg.baseball.dao.interfaces.TeamDao;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.PlayerService;
import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)  //says Spring in charge of running unit tests.
@ContextConfiguration(locations = {"/test-applicationContext.xml"}) //indicates the xml file to use
@Rollback //Automatically all tests will be transactional, and when complete, won't be committed; will be rolled back.
//need @Rollback and @Transactional annotations both.
@Transactional
public class PositionDaoTests {

    @Inject
    TestHelper testHelper;

    @Inject
    PositionDao positionDao;

    @Inject
    PlayerService playerService;

    @Test
    public void testCreate() {

        //Arrange & Act
        Position createdPosition = testHelper.createTestPosition();

        //Assert
        assert createdPosition.getId() != null;
        assert "Pitcher".equals(createdPosition.getName()); //any time a string is compared to a constant, put
        //the constant first.

    }

    @Test
    public void testRead() {

        //Arrange
        Position position = testHelper.createTestPosition();

        //Act
        Position readPosition = positionDao.read(position.getId());

        //Assert
        assertPositionEquals(position, readPosition);

    }

    @Test
    public void testUpdate() {

        //Arrange
        Position position = testHelper.createTestPosition();

        //Change some stuff
        position.setName("Shortstop");

        //Act
        positionDao.update(position);

        //Assert
        Position readPosition = positionDao.read(position.getId());
        assert "Shortstop".equals(readPosition.getName());

    }

    @Test
    public void testDelete() {

        //Arrange
        Position position = testHelper.createTestPosition();

        //Act
        positionDao.delete(position);

        //Assert
        assertNull(positionDao.read(position.getId()));
    }

    @Test
    public void testGetAll() {

        //Arrange
        List<Position> createPositions = createTestPositions(25);

        //Act
        List<Position> positionList = positionDao.getAll(Integer.MAX_VALUE, 0);

        //Assert
        assertPositionListsEqual(positionList, createPositions);

    }
//
    @Test
    public void testGetAllPage() {

        //Arrange
        createTestPositions(25);

        //Act
        List<Position> positionList = positionDao.getAll(10, 5);

        //Assert
        assert positionList.size() == 10;

        for (int i=5; i<15; i++) {
            Position position = positionList.get(i-5);
            assert ("First Base" + i).equals(position.getName());

        }

    }

    @Test
    public void testGetPositionByPlayer() {

            //Arrange
            for(int i=0; i<15; i++) {
                testHelper.createTestPosition();
            }

            Player player = testHelper.createTestPlayerNoTeam();
            for (int i=0; i<10; i++) {
                Position position = testHelper.createTestPosition();
                testHelper.createPlayerPosition(player, position);
            }

            //Act
            List<Position> positionList = positionDao.getPositionByPlayer(player, Integer.MAX_VALUE, 0);

            //Assert
            assert positionList.size() == 10;

            for(Position position : positionList) {
                List<Player> playerList = playerService.getPlayerByPosition(position, Integer.MAX_VALUE, 0);

                boolean exists = false;

                for (Player play : playerList) {
                    if (play.getId().equals(player.getId())) exists = true;
                }

                assert exists;
            }

        }
//
    private void assertPositionListsEqual(List<Position> list1, List<Position> list2) {
        assert list1.size() == list2.size();

        for (Position position : list1) {

            boolean exists = false;

            for (Position originalPosition : list2) {
                if (position.getId().equals(originalPosition.getId())) {
                    exists = true;
                }
            }

            assert exists == true;
        }
    }
//
    private void assertPositionEquals(Position position, Position position2) {
        assert position.getId().equals(position2.getId());
        assert position.getName().equals(position2.getName());

    }

//
//
    private List<Position> createTestPositions(int positionsToCreate) {

        List<Position> createdPositions = new ArrayList<>();
        positionsToCreate = 25;
        for (int i=0; i<positionsToCreate; i++) {
            Position position = new Position();
            position.setName("First Base" + i);

            createdPositions.add(positionDao.create(position));
        }

        return createdPositions;
    }
}