package com.sg.baseball.webservice;


import com.sg.baseball.TestHelper;
import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.PositionService;
import com.sg.baseball.service.interfaces.TeamService;
import com.sg.baseball.viewmodels.DropDownItemViewModel;
import com.sg.baseball.viewmodels.player.create.CreatePlayerCommandModel;
import com.sg.baseball.viewmodels.player.create.CreatePlayerViewModel;
import com.sg.baseball.viewmodels.player.edit.EditPlayerCommandModel;
import com.sg.baseball.viewmodels.player.edit.EditPlayerViewModel;
import com.sg.baseball.viewmodels.player.list.PlayerListViewModel;
import com.sg.baseball.viewmodels.player.profile.PlayerProfileViewModel;
import com.sg.baseball.webservice.interfaces.PlayerWebService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)  //says Spring in charge of running unit tests.
@ContextConfiguration(locations = {"/test-applicationContext.xml"}) //indicates the xml file to use
@Rollback //Automatically all tests will be transactional, and when complete, won't be committed; will be rolled back.
//need @Rollback and @Transactional annotations both.
@Transactional
public class PlayerWebServiceTests {

    @Inject
    PlayerWebService playerWebService;

    @Inject
    TestHelper testHelper;

    @Inject
    TeamService teamService;

    @Inject
    PositionService positionService;

    @Test
    public void testGetPlayerListViewModel() {

        //Arrange
        Integer offset = 0;

        List<Player> players = new ArrayList<>();
        Team team = testHelper.createTestTeam();
        for (int i=0; i<25; i++) {
            Player player = testHelper.createTestPlayer(team);
            players.add(player);
        }

        //Act
        PlayerListViewModel viewModel = playerWebService.getPlayerListViewModel(0);

        //Assert
        assert viewModel.getSelectedPage() == 1;
        assert viewModel.getPageNumbers().length == 5;
        assert viewModel.getPageNumbers()[0] == 1;
        assert viewModel.getPageNumbers()[4] == 5;

        assert viewModel.getPlayers() != null;
        assert viewModel.getPlayers().size() == 10;

        int counter=0;
        for (PlayerProfileViewModel pViewModel : viewModel.getPlayers()) {
            Player player = players.get(counter);
            assert pViewModel.getFirst().equals((player.getFirst()));
            assert pViewModel.getLast().equals(player.getLast());
            assert pViewModel.getTeamId().equals(team.getId());
            assert pViewModel.getTeamId().equals(player.getTeam().getId());
            counter++;
        }

//        private int[] pageNumbers;
//        private int selectedPage;
//
//        private List<PlayerProfileViewModel> players;
    }

    @Test
    public void testGetPlayerProfileViewModel() {

        //Arrange
        Player player = testHelper.createTestPlayerWithTeam();

        Team team = teamService.read(player.getTeam().getId());

        Position position1 = testHelper.createTestPosition();
        Position position2 = testHelper.createTestPosition();
        testHelper.createPlayerPosition(player, position1);
        testHelper.createPlayerPosition(player, position2);

        //Act
        PlayerProfileViewModel viewModel = playerWebService.getPlayerProfileViewModel(player.getId());

        //Assert
        assert viewModel.getFirst().equals(player.getFirst());
        assert viewModel.getLast().equals(player.getLast());
        assert viewModel.getTeamName().equals(team.getCity() + " " + team.getNickname());
        assert viewModel.getTeamId().equals(team.getId());

        assert viewModel.getPositions().get(0).getId().equals(position1.getId());
        assert viewModel.getPositions().get(0).getName().equals(position1.getName());
        assert viewModel.getPositions().get(1).getId().equals(position2.getId());
        assert viewModel.getPositions().get(1).getName().equals(position2.getName());
    }

    @Test
    public void getCreatePlayerViewModel() {

        //Arrange
        List<Position> positions = testHelper.createTestPositions(15);
        List<Team> teams = testHelper.createTestTeams(15);

        //Act
        CreatePlayerViewModel createPlayerViewModel = playerWebService.getCreatePlayerViewModel();

        //Assert
        assert createPlayerViewModel.getPositions().size() == positions.size();
        assert createPlayerViewModel.getTeams().size() == teams.size();

        for (DropDownItemViewModel createPositionViewModel : createPlayerViewModel.getPositions()) {
            assert createPositionViewModel.getId() != null;
            assert createPositionViewModel.getName() != null;
        }

        for (DropDownItemViewModel createTeamViewModel : createPlayerViewModel.getTeams()) {
            assert createTeamViewModel.getId() != null;
            assert createTeamViewModel.getName() != null;
        }
    }

        @Test
        public void saveCreatePlayerCommandModel() {

            //Arrange
            //When testing command model, the 'arrange' arranges the data that the user would enter from the UI.
            Team createdTeam = testHelper.createTestTeam();
            List<Position> createdPositions = testHelper.createTestPositions(2);


            //In the web side of things spring is going to handle this part for us.
            CreatePlayerCommandModel createPlayerCommandModel = new CreatePlayerCommandModel();
            createPlayerCommandModel.setFirst("Pat");
            createPlayerCommandModel.setLast("Toner");
            createPlayerCommandModel.setTeamId(createdTeam.getId());

            Long[] positionIds = new Long[2];
            positionIds[0] = createdPositions.get(0).getId();
            positionIds[1] = createdPositions.get(1).getId();

            createPlayerCommandModel.setPositionIds(positionIds);


            //Act
            Player player = playerWebService.saveCreatePlayerCommandModel(createPlayerCommandModel);

            //Assert
            assert player.getId() != null;
            assert "Pat".equals(player.getFirst());
            assert "Toner".equals(player.getLast());
            assert player.getTeam().getId().equals(createdTeam.getId());

            List<Position> positions = positionService.getPositionByPlayer(player, Integer.MAX_VALUE, 0);

            boolean savedFirstPosition = false;
            boolean savedSecondPosition = false;

            for (Position position : positions) {
                if (position.getId().equals(createdPositions.get(0).getId())) savedFirstPosition = true;
                if (position.getId().equals(createdPositions.get(1).getId())) savedSecondPosition = true;
            }

            assert savedFirstPosition == true;
            assert savedSecondPosition == true;
        }

//    @Test
//    public void getEditPlayerViewModel() {
//        //Arrange
//        List<Position> positions = testHelper.createTestPositions(15);
//        List<Team> teams = testHelper.createTestTeams(15);
//        Player player = testHelper.createTestPlayer(teams.get(0));
//        //String teamName = teams.get(0).getCity() + " " + teams.get(0).getNickname();
//
//        //Act
//        EditPlayerViewModel editPlayerViewModel = playerWebService.getEditPlayerViewModel(player.getId());
//
//        //Assert
//        assert editPlayerViewModel.getPositions().size() == positions.size();
//        assert editPlayerViewModel.getTeams().size() == teams.size();
//        assert editPlayerViewModel.getCommandModel().getFirst().equals("Pat");
//        assert editPlayerViewModel.getCommandModel().getLast().equals("Toner");
//        assert editPlayerViewModel.getCommandModel().getTeamId().equals(player.getTeam().getId());
//
//        for (DropDownItemViewModel editPositionViewModel : editPlayerViewModel.getPositions()) {
//            assert editPositionViewModel.getId() != null;
//            assert editPositionViewModel.getName() != null;
//        }
//
//        for (DropDownItemViewModel editTeamViewModel : editPlayerViewModel.getTeams()) {
//            assert editTeamViewModel.getId() != null;
//            assert editTeamViewModel.getName() != null;
//        }
//
//
//        }
//
//    @Test
//    public void saveEditPlayerCommandModel() {
//
//        //Arrange
//        //When testing command model, the 'arrange' arranges the data that the user would enter from the UI.
//        Team createdTeam = testHelper.createTestTeam();
//        Team otherCreatedTeam = testHelper.createTestTeam();
//        List<Position> createdPositions = testHelper.createTestPositions(4);
//        //List<Position> otherCreatedPositions = testHelper.createTestPositions(2);
//
//
//        //In the web side of things spring is going to handle this part for us.
//        CreatePlayerCommandModel createPlayerCommandModel = new CreatePlayerCommandModel();
//        createPlayerCommandModel.setFirst("Pat");
//        createPlayerCommandModel.setLast("Toner");
//        createPlayerCommandModel.setTeamId(createdTeam.getId());
//
//        Long[] positionIds = new Long[2];
//        positionIds[0] = createdPositions.get(0).getId();
//        positionIds[1] = createdPositions.get(1).getId();
//
//        createPlayerCommandModel.setPositionIds(positionIds);
//        Player player = playerWebService.saveCreatePlayerCommandModel(createPlayerCommandModel);
//
//        //Now update the player info
//        EditPlayerCommandModel updatedPlayerCommandModel = new EditPlayerCommandModel();
//        updatedPlayerCommandModel.setId(player.getId());
//        updatedPlayerCommandModel.setFirst("Joe");
//        updatedPlayerCommandModel.setLast("Schmoe");
//        updatedPlayerCommandModel.setTeamId(otherCreatedTeam.getId());
//
//        Long[] otherPositionIds = new Long[2];
//        otherPositionIds[0] = createdPositions.get(2).getId();
//        otherPositionIds[1] = createdPositions.get(3).getId();
//
//        updatedPlayerCommandModel.setPositionIds(otherPositionIds);
//
//        //Act
//        Player updatedPlayer = playerWebService.saveEditPlayerCommandModel(updatedPlayerCommandModel);
//
//
//        //Assert
//        assert updatedPlayer.getId() != null;
//        assert "Joe".equals(updatedPlayer.getFirst());
//        assert "Schmoe".equals(updatedPlayer.getLast());
//        assert updatedPlayer.getTeam().getId().equals(otherCreatedTeam.getId());
//
//        List<Position> updatedPositions = positionService.getPositionByPlayer(updatedPlayer, Integer.MAX_VALUE, 0);
//
//        boolean updatedFirstPosition = false;
//        boolean updatedSecondPosition = false;
//
//        for (Position position : updatedPositions) {
//            if (position.getId().equals(updatedPositions.get(0).getId())) updatedFirstPosition = true;
//            if (position.getId().equals(updatedPositions.get(1).getId())) updatedSecondPosition = true;
//        }
//
//        assert updatedFirstPosition == true;
//        assert updatedSecondPosition == true;
//    }

    @Test
    public void testGetEditPlayerViewModel(){
        //arrange
        List<Team> teams = testHelper.createTestTeams(3);
        List<Position> positions = testHelper.createTestPositions(5);

        Player player = testHelper.createTestPlayer(teams.get(0));
        testHelper.createPlayerPosition(player,positions.get(0));
        testHelper.createPlayerPosition(player,positions.get(1));


        //act
        EditPlayerViewModel vm = this.playerWebService.getEditPlayerViewModel(player.getId());

        //assert
        assert vm.getTeams().size() == teams.size();
        assert vm.getPositions().size() == positions.size();

        // check list for ids

        assert player.getFirst().equals(vm.getCommandModel().getFirst());
        assert player.getLast().equals(vm.getCommandModel().getLast());
        assert player.getId().equals(vm.getCommandModel().getId());

        assert player.getTeam().getId().equals(vm.getCommandModel().getTeamId());

        int counter = 0;
        List<DropDownItemViewModel> vmList = vm.getPositions();
        for (DropDownItemViewModel ddvm : vmList) {
            assert (ddvm.getName().equals(positions.get(counter).getName()));
            counter++;
        }
//        boolean savedFirstPosition = false;
//        boolean savedSecondPosition = false;
//        for (Position position : positions) {
//            if (position.getId().equals(positions.get(0).getId())) savedFirstPosition = true;
//            if (position.getId().equals(positions.get(1).getId())) savedSecondPosition = true;
//        }
//        assert savedFirstPosition;
//        assert savedSecondPosition;
    }

    @Test
    public void testSaveEditPlayerViewModel(){
        //arrange

        //  Set up the Player in the database we are going to edit using the helpers
        List<Team> teams = testHelper.createTestTeams(3);
        List<Position> positions = testHelper.createTestPositions(5);
        Player player = testHelper.createTestPlayer(teams.get(0));
        testHelper.createPlayerPosition(player,positions.get(0));
        testHelper.createPlayerPosition(player,positions.get(1));

        //Retrieve the viewModel (acting like we are going to the edit page ourselves)
        EditPlayerViewModel viewModel = this.playerWebService.getEditPlayerViewModel(player.getId());
        EditPlayerCommandModel commandModel = viewModel.getCommandModel();

        // Change the items on the form (commandModel) to what we want
        commandModel.setFirst("Randall");
        commandModel.setLast("Clapper");
        commandModel.setTeamId(viewModel.getTeams().get(1).getId());

        // Here we are selecting to items in the drop down on the page.
        Long[] positionIds = new Long[2];
        positionIds[0] = viewModel.getPositions().get(0).getId();
        positionIds[1] = viewModel.getPositions().get(2).getId();
        commandModel.setPositionIds(positionIds);
        //act
        this.playerWebService.saveEditPlayerCommandModel(commandModel);

        // once we save the changes, we want to make sure the viewModel of the edit pages if visited again has those
        // changes saved and retrieved from the database.
        PlayerProfileViewModel playerViewModel = this.playerWebService.getPlayerProfileViewModel(player.getId());
        assert playerViewModel.getFirst().equals("Randall");
        assert playerViewModel.getLast().equals("Clapper");
        assert playerViewModel.getTeamId().equals(viewModel.getTeams().get(1).getId());
        boolean savedFirstPosition = false;
        boolean savedSecondPosition = false;
        for (Position position : positions) {
            if (position.getId().equals(positions.get(0).getId())) savedFirstPosition = true;
            if (position.getId().equals(positions.get(2).getId())) savedSecondPosition = true;
        }
        assert savedFirstPosition;
        assert savedSecondPosition;

//        playerViewModel.
    }

}
