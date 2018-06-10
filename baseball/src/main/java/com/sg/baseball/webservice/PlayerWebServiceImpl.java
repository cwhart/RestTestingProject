package com.sg.baseball.webservice;

import com.sg.baseball.dto.Player;
import com.sg.baseball.dto.PlayerPosition;
import com.sg.baseball.dto.Position;
import com.sg.baseball.dto.Team;
import com.sg.baseball.service.interfaces.PlayerPositionService;
import com.sg.baseball.service.interfaces.PlayerService;
import com.sg.baseball.service.interfaces.PositionService;
import com.sg.baseball.service.interfaces.TeamService;
import com.sg.baseball.util.PagingUtils;
import com.sg.baseball.viewmodels.DropDownItemViewModel;
import com.sg.baseball.viewmodels.player.profile.PlayerProfileViewModel;
import com.sg.baseball.viewmodels.player.profile.PositionViewModel;
import com.sg.baseball.webservice.interfaces.PlayerWebService;
import com.sg.baseball.viewmodels.player.create.CreatePlayerCommandModel;
import com.sg.baseball.viewmodels.player.create.CreatePlayerViewModel;
import com.sg.baseball.viewmodels.player.edit.EditPlayerCommandModel;
import com.sg.baseball.viewmodels.player.edit.EditPlayerViewModel;
import com.sg.baseball.viewmodels.player.list.PlayerListViewModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerWebServiceImpl implements PlayerWebService {

    PlayerService playerService;

    TeamService teamService;

    PositionService positionService;

    PlayerPositionService playerPositionService;

    @Inject
    public PlayerWebServiceImpl(PlayerService playerService, TeamService teamService,
                                PositionService positionService, PlayerPositionService playerPositionService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.positionService = positionService;
        this.playerPositionService = playerPositionService;
    }

    @Override
    public PlayerListViewModel getPlayerListViewModel(Integer offset) {

        Integer limit = 10;
        PlayerListViewModel viewModel = new PlayerListViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<Player> playerList = playerService.getAll(limit, offset);
        List<PlayerProfileViewModel> players = translate(playerList);

        viewModel.setSelectedPage(selectedPage);
        //viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setPlayers(players);

        return viewModel;
    }

    private List<PlayerProfileViewModel> translate(List<Player> playerList) {

        List<PlayerProfileViewModel> viewModels = new ArrayList<>();

        for (Player player: playerList) {
            PlayerProfileViewModel viewModel = new PlayerProfileViewModel();
            viewModel.setFirst(player.getFirst());
            viewModel.setLast(player.getLast());

            if(player.getTeam() != null) {
                viewModel.setTeamName(player.getTeam().getCity() + " " + player.getTeam().getNickname());
                viewModel.setPlayerId(player.getId());
                viewModel.setTeamId(player.getTeam().getId());
                viewModels.add(viewModel);
            }
        }

        return viewModels;

    }


    @Override
    public PlayerProfileViewModel getPlayerProfileViewModel(Long id) {

        //Instantiate stuff
        PlayerProfileViewModel viewModel = new PlayerProfileViewModel();


        //Look up stuff
        Player player = playerService.read(id);
        Team team = null;
        if (player.getTeam() != null) {
            team = teamService.read(player.getTeam().getId());
        }

        List<Position> positions = positionService.getPositionByPlayer(player, Integer.MAX_VALUE, 0);


        //Set stuff
        viewModel.setFirst(player.getFirst());
        viewModel.setLast(player.getLast());

        //Lazy loaded - can't rely on the queries to return, hence additional logic above to instantiate Team and
        //check that it's not null.
        if (team != null) {


            viewModel.setTeamId(team.getId());
            viewModel.setTeamName(team.getCity() + " " + team.getNickname());
        }

        if (positions != null && positions.size() > 0) {
            List <PositionViewModel> positionViewModels = translatePositions(positions);
            viewModel.setPositions(positionViewModels);
        }

        //Return stuff
        return viewModel;
    }

    private List<PositionViewModel> translatePositions(List<Position> positions) {
        List<PositionViewModel> positionViewModels = new ArrayList<>();

        for (Position position : positions) {
            PositionViewModel vm = new PositionViewModel();
            vm.setId(position.getId());
            vm.setName(position.getName());
            positionViewModels.add(vm);
        }

        return positionViewModels;
    }

    @Override
    public CreatePlayerViewModel getCreatePlayerViewModel() {
        CreatePlayerViewModel result = new CreatePlayerViewModel();

        List<Position> positions = this.positionService.getAll(Integer.MAX_VALUE,0);
        List<DropDownItemViewModel> dropdownPositions = new ArrayList<>();
        for(Position position: positions){
            DropDownItemViewModel ddi = new DropDownItemViewModel();
            ddi.setId(position.getId());
            ddi.setName(position.getName());
            dropdownPositions.add(ddi);
        }
        result.setPositions(dropdownPositions);

        List<Team> teams = this.teamService.getAll(Integer.MAX_VALUE,0  );
        List<DropDownItemViewModel> dropdownTeams = new ArrayList<>();
        for(Team team: teams){
            DropDownItemViewModel ddi = new DropDownItemViewModel();
            ddi.setId(team.getId());
            ddi.setName(team.getCity() + " " + team.getNickname());
            dropdownTeams.add(ddi);
        }
        result.setTeams(dropdownTeams);

        result.setCommandModel(new CreatePlayerCommandModel());

        return result;
    }

    @Override
    public Player saveCreatePlayerCommandModel(CreatePlayerCommandModel commandModel) {
        Player player = new Player();
        player.setFirst(commandModel.getFirst());
        player.setLast(commandModel.getLast());
        Team team = teamService.read(commandModel.getTeamId());
        player.setTeam(team);

        player = playerService.create(player);
        //List<PlayerPosition> playerPositions = new ArrayList<>();
        for (Long positionId : commandModel.getPositionIds()) {
            PlayerPosition playerPosition = new PlayerPosition();
            playerPosition.setPlayer(player);
            Position position = positionService.read(positionId);
            playerPosition.setPosition(position);
            playerPosition = playerPositionService.create(playerPosition);
            //playerPositions.add(playerPosition);
        }

        return player;
    }

//    @Override
//    public EditPlayerViewModel getEditPlayerViewModel(Long id) {
//        EditPlayerViewModel result = new EditPlayerViewModel();
//
//        List<Position> positions = this.positionService.getAll(Integer.MAX_VALUE,0);
//        List<DropDownItemViewModel> dropdownPositions = new ArrayList<>();
//        for(Position position: positions){
//            DropDownItemViewModel ddi = new DropDownItemViewModel();
//            ddi.setId(position.getId());
//            ddi.setName(position.getName());
//            dropdownPositions.add(ddi);
//        }
//        result.setPositions(dropdownPositions);
//
//        List<Team> teams = this.teamService.getAll(Integer.MAX_VALUE,0  );
//        List<DropDownItemViewModel> dropdownTeams = new ArrayList<>();
//        for(Team team: teams){
//            DropDownItemViewModel ddi = new DropDownItemViewModel();
//            ddi.setId(team.getId());
//            ddi.setName(team.getCity() + " " + team.getNickname());
//            dropdownTeams.add(ddi);
//        }
//        result.setTeams(dropdownTeams);
//
//        EditPlayerCommandModel cmd = new EditPlayerCommandModel();
//        cmd.setFirst(playerService.read(id).getFirst());
//        cmd.setLast(playerService.read(id).getLast());
//        cmd.setTeamId(playerService.read(id).getTeam().getId());
//
//        result.setCommandModel(cmd);
//
//        return result;
//    }
//
//    @Override
//    public Player saveEditPlayerCommandModel(EditPlayerCommandModel commandModel) {
//
//        Player player = playerService.read(commandModel.getId());
//        if(player != null) {
//            player.setFirst(commandModel.getFirst());
//            player.setLast(commandModel.getLast());
//            Team team = teamService.read(commandModel.getTeamId());
//            player.setTeam(team);
//
//            playerService.update(player);
//
//            List<Position> positions = positionService.getPositionByPlayer(player, Integer.MAX_VALUE, 0);
//            for (Position currentPosition : positions) {
//                PlayerPosition currentPlayerPosition = new PlayerPosition();
//                currentPlayerPosition.setPosition(currentPosition);
//                currentPlayerPosition.setPlayer(player);
//                //currentPlayerPosition.getId();
//                playerPositionService.delete(currentPlayerPosition);
//            }
//
//        }
//        //Following copied from Create method:
//        List<PlayerPosition> playerPositions = new ArrayList<>();
//        for (Long positionId : commandModel.getPositionIds()) {
//            PlayerPosition playerPosition = new PlayerPosition();
//            playerPosition.setPlayer(player);
//            Position position = positionService.read(positionId);
//            playerPosition.setPosition(position);
//            playerPosition = playerPositionService.create(playerPosition);
//            //playerPositions.add(playerPosition);
//        }
//
//        return player;
//    }

    @Override
    public EditPlayerViewModel getEditPlayerViewModel(Long id) {
        EditPlayerViewModel model = new EditPlayerViewModel();
        Player player = this.playerService.read(id);
        List<Team> teams = this.teamService.getAll(Integer.MAX_VALUE,0);
        List<Position> positions = this.positionService.getAll(Integer.MAX_VALUE,0);
        EditPlayerCommandModel commandModel = new EditPlayerCommandModel();
        model.setTeams(new ArrayList<DropDownItemViewModel>());
        model.setPositions(new ArrayList<DropDownItemViewModel>());
        for (Position position:positions) {
            DropDownItemViewModel ddl = new DropDownItemViewModel();
            ddl.setId(position.getId());
            ddl.setName(position.getName());
            model.getPositions().add(ddl);
        }
        for (Team team:teams) {
            DropDownItemViewModel ddl = new DropDownItemViewModel();
            ddl.setId(team.getId());
            ddl.setName(team.getCity() + " " + team.getNickname());
            model.getTeams().add(ddl);
        }
        List<Position> playerPositions = positionService.getPositionByPlayer(player,Integer.MAX_VALUE,0);
        List<Long> positionIds = playerPositions
                .stream()
                .map(x -> x.getId())
                .collect(Collectors.toList());

        commandModel.setId(player.getId());
        commandModel.setFirst(player.getFirst());
        commandModel.setLast(player.getLast());

        commandModel.setTeamId(player.getTeam().getId());
        commandModel.setPositionIds(positionIds.toArray(new Long[positionIds.size()]));
        model.setCommandModel(commandModel);
        return model;
    }

    @Override
    public void saveEditPlayerCommandModel(EditPlayerCommandModel commandModel) {

        Player player = playerService.read(commandModel.getId());
        if(player == null) return;
        player.setTeam(this.teamService.read(commandModel.getTeamId()));
        player.setFirst(commandModel.getFirst());
        player.setLast(commandModel.getLast());

        playerService.update(player);
        //update the positions
        List<Position> playerPositions = this.positionService.getPositionByPlayer(player,Integer.MAX_VALUE,0);
        for (Position position : playerPositions) {
            PlayerPosition pp = new PlayerPosition();
            pp.setPosition(position);
            pp.setPlayer(player);
            this.playerPositionService.delete(pp);

        }
        for (Long positionId :
                commandModel.getPositionIds()) {
            PlayerPosition pp = new PlayerPosition();
            pp.setPlayer(player);
            pp.setPosition(this.positionService.read(positionId));
            this.playerPositionService.create(pp);
        }

        //return player;
    }


}
