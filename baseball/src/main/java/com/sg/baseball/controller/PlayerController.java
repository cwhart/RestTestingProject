package com.sg.baseball.controller;

import com.sg.baseball.dto.Player;
import com.sg.baseball.viewmodels.player.create.CreatePlayerCommandModel;
import com.sg.baseball.viewmodels.player.create.CreatePlayerViewModel;
import com.sg.baseball.viewmodels.player.edit.EditPlayerCommandModel;
import com.sg.baseball.viewmodels.player.edit.EditPlayerViewModel;
import com.sg.baseball.viewmodels.player.list.PlayerListViewModel;
import com.sg.baseball.webservice.interfaces.PlayerWebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/player")
public class PlayerController {

    private PlayerWebService playerWebService;

    @Inject
    public PlayerController(PlayerWebService playerWebService) {
        this.playerWebService = playerWebService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false) Integer offset, Model model) {

        PlayerListViewModel viewModel = playerWebService.getPlayerListViewModel(offset);

        model.addAttribute("viewModel", viewModel);

        return "player/list";
    }

    @RequestMapping(value= "/edit")
    public String edit(@RequestParam Long id, Model model) {

        EditPlayerViewModel viewModel = playerWebService.getEditPlayerViewModel(id);

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());

        return "player/edit";
    }

    public String saveEdit(@Valid @ModelAttribute("commandModel") EditPlayerCommandModel commandModel,
                           BindingResult bindingResult, Model model) { //Take in model in case we need to return
                            //to the edit page - i.e. if the user typed in an invalid value, this will save
                            //the values they previously input.
        if(bindingResult.hasErrors()) {
            EditPlayerViewModel viewModel = playerWebService.getEditPlayerViewModel(commandModel.getId());

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "player/edit";
        }

        playerWebService.saveEditPlayerCommandModel(commandModel);

        return "redirect:/player?show?id=" + commandModel.getId();
    }

    @RequestMapping(value= "/create")
    public String create(Model model) {

        CreatePlayerViewModel viewModel = playerWebService.getCreatePlayerViewModel();

        model.addAttribute("viewModel", viewModel);
        model.addAttribute("commandModel", viewModel.getCommandModel());

        return "player/create";
    }

    @RequestMapping(value = "/create", method= RequestMethod.POST)
    public String savecreate(@Valid @ModelAttribute("commandModel") CreatePlayerCommandModel commandModel,
                           BindingResult bindingResult, Model model) { //Take in model in case we need to return
        //to the edit page - i.e. if the user typed in an invalid value, this will save
        //the values they previously input.
        if(bindingResult.hasErrors()) {
            CreatePlayerViewModel viewModel = playerWebService.getCreatePlayerViewModel();

            model.addAttribute("viewModel", viewModel);
            model.addAttribute("commandModel", commandModel);
            return "player/create";
        }

        Player player = playerWebService.saveCreatePlayerCommandModel(commandModel);

        return "redirect:/player?show?id=" + player.getId();
    }

}
