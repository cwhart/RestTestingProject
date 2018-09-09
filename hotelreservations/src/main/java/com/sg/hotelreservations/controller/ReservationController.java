package com.sg.hotelreservations.controller;

import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.service.serviceinterface.ReservationRoomService;
import com.sg.hotelreservations.viewmodels.reservation.list.ListReservationViewModel;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesCommandModel;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesViewModel;
import com.sg.hotelreservations.webservice.webinterface.ReservationWebService;
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
@RequestMapping(value = "/reservation")
public class ReservationController {

        ReservationWebService reservationWebService;

        ReservationRoomService reservationRoomService;

        @Inject
        public ReservationController(ReservationWebService reservationWebService) {
            this.reservationWebService = reservationWebService;
        }


        @RequestMapping(value = "/search", method = RequestMethod.GET)
        public String inputDates(Model model) {

            InputReservationDatesViewModel viewModel = reservationWebService.getReservationDatesViewModel();

            model.addAttribute("viewModel",viewModel);
            model.addAttribute("commandModel",viewModel.getCommandModel());

            return "reservation/search";
        }//*

    @RequestMapping(value = "/search", method= RequestMethod.POST)
    public String search(@Valid @ModelAttribute("commandModel") InputReservationDatesCommandModel commandModel,
                         BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            InputReservationDatesViewModel viewModel =
                    reservationWebService.getReservationDatesViewModel();

            model.addAttribute("viewModel",viewModel);
            model.addAttribute("commandModel",commandModel);
            return "reservation/search";
        }

        Reservation reservation = reservationWebService.searchInputDatesCommandModel(commandModel);

        return "redirect:/reservation/list?start=" + reservation.getStartDate() +"&end=" + reservation.getEndDate();
    }



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String profile(@RequestParam(required = false) Model model) {

//        InputReservationDatesCommandModel commandModel = new InputReservationDatesCommandModel();
//        commandModel.setStartDate(start);
//        commandModel.setEndDate(end);
        //LocalDate startDate = LocalDate.parse(start);
        //LocalDate endDate = LocalDate.parse(end);
        ListReservationViewModel reservationList = reservationWebService.getReservationListViewModel(0);

        model.addAttribute("viewModel", reservationList);

        return "reservation/list";
    }


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

//        @RequestMapping(value = "/profile")
//        public String profile(@RequestParam Long id, Model model) {
//            ProfileRoomViewModel profileViewModel = roomWebService.getRoomProfileViewModel(id);
//
//            model.addAttribute("profileViewModel",profileViewModel);
//
//            return "room/profile";
//        }

//        @RequestMapping(value = "/delete")
//        public String delete(@RequestParam Long id, Model model) {
//            roomWebService.deleteRoom(id);
//
//            return "redirect:/room/list";
//        }


    }

