package com.sg.hotelreservations.controller;

import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.viewmodels.room.create.CreateRoomCommandModel;
import com.sg.hotelreservations.viewmodels.room.create.CreateRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.edit.EditRoomCommandModel;
import com.sg.hotelreservations.viewmodels.room.edit.EditRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.list.ListRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.profile.ProfileRoomViewModel;
import com.sg.hotelreservations.webservice.webinterface.RoomWebService;
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
@RequestMapping(value = "/room")
public class RoomController {

        RoomWebService roomWebService;

        @Inject
        public RoomController(RoomWebService roomWebService) {
            this.roomWebService = roomWebService;
        }

        @RequestMapping(value = "/list", method = RequestMethod.GET)
        public String list(@RequestParam(required = false) Integer offset, Model model) {
            if(offset == null) {
                offset = 0;
            }
            ListRoomViewModel listViewModel = roomWebService.getRoomListViewModel(offset);

            model.addAttribute("listViewModel", listViewModel);

            return "room/list";
        }//*


        //@RequestMapping(value = "/search", method = RequestMethod.POST)
//        @RequestMapping(value = "/search", method = RequestMethod.GET)
//        public String search( Model model) {
//            //public String search() {
//            CreateRoomViewModel viewModel = roomWebService.getCreateRoomViewModel();
//
//            model.addAttribute("viewModel",viewModel);
//            model.addAttribute("commandModel",viewModel.getCommandModel());
//
//            return "room/search";
//        }
//
//        @RequestMapping(value = "/search", method= RequestMethod.POST)
//        public String saveCreate(@Valid @ModelAttribute("commandModel") CreateRoomCommandModel commandModel, BindingResult bindingResult, Model model){
//            if(bindingResult.hasErrors()){
//                CreateRoomViewModel viewModel =
//                        roomWebService.getCreateRoomViewModel();
//
//                model.addAttribute("viewModel",viewModel);
//                model.addAttribute("commandModel",commandModel);
//                return "room/search";
//            }
//            Room room = roomWebService.saveCreateRoomCommandModel(commandModel);
//
//            return "redirect:/room/profile?id=" + room.getId();
//        }

//        @RequestMapping (value= "/edit")
//        public String edit(@RequestParam Long id, Model model){
//
//            EditRoomViewModel viewModel = roomWebService.getEditRoomViewModel(id);
//            model.addAttribute("commandModel", viewModel.getCommandModel());
//            return "room/edit";
//        }
//
//        @RequestMapping(value = "/edit", method = RequestMethod.POST)
//        public String saveEdit(@Valid @ModelAttribute("commandModel") EditRoomCommandModel commandModel, BindingResult bindingResult, Model model) {
//            if(bindingResult.hasErrors()) {
//                EditRoomViewModel viewModel = roomWebService.getEditRoomViewModel(commandModel.getId());
//                model.addAttribute("viewModel", viewModel);
//                model.addAttribute("commandModel", commandModel);
//                return "room/edit";
//            }
//            roomWebService.saveEditRoomCommandModel(commandModel);
//            return "redirect:/room/profile?id=" + commandModel.getId();
//        }

        @RequestMapping(value = "/profile")
        public String profile(@RequestParam Long id, Model model) {
            ProfileRoomViewModel profileViewModel = roomWebService.getRoomProfileViewModel(id);

            model.addAttribute("profileViewModel",profileViewModel);

            return "room/profile";
        }

//        @RequestMapping(value = "/delete")
//        public String delete(@RequestParam Long id, Model model) {
//            roomWebService.deleteRoom(id);
//
//            return "redirect:/room/list";
//        }


    }

