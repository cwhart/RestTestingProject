package com.sg.hotelreservations.controller;

import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.viewmodels.reservation.*;
import com.sg.hotelreservations.viewmodels.room.ListRoomViewModel;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;
import com.sg.hotelreservations.webservice.exception.InvalidPromoException;
import com.sg.hotelreservations.webservice.webinterface.ReservationWebService;
import com.sg.hotelreservations.webservice.webinterface.RoomWebService;
import org.h2.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/reservation")
public class ReservationController {

    RoomWebService roomWebService;
    ReservationWebService reservationWebService;


    public ReservationController(RoomWebService roomWebService,
                                 ReservationWebService reservationWebService) {
        this.roomWebService = roomWebService;
        this.reservationWebService = reservationWebService;
    }



    //Method to list rooms based on search criteria. If no criteria entered, list all rooms.
    @RequestMapping(value = "/displayRooms", method = RequestMethod.GET)
    //@ApiOperation(value = "Find all data by createDts range (limit to 1000 results)", notes = "Example date/time value:  2017-11-28T12:40:00.000Z")
    public String display(@RequestParam(required = false) Integer offset, Model model, String message) {

        if (offset == null) {
            offset = 0;
        }
        ListRoomViewModel roomViewModel = roomWebService.getRoomListViewModel(offset);

        model.addAttribute("viewModel", roomViewModel);

        //This is just the initial display, so we want to get the searchAvailableRoomsCommandModel,
        //display it, and also get the ListRoomViewModel for all rooms.

        SearchAvailableRoomsViewModel viewModel = reservationWebService.getSearchAvailableRoomsViewModel();
        roomViewModel.setMessage(message);
        model.addAttribute("viewModel",viewModel);
        model.addAttribute("commandModel",viewModel.getCommandModel());
        model.addAttribute("viewModel", roomViewModel);



        return "/reservation/searchRooms";

    }

    @RequestMapping(value = "/searchRooms", method = RequestMethod.GET)
    public String search(SearchAvailableRoomsCommandModel commandModel, Model model) throws InvalidDatesException{

        //Get startdate, enddate and numpersons from commandModel and input into this method:
        LocalDate startDate = LocalDate.parse(commandModel.getStartDate());
        LocalDate endDate = LocalDate.parse(commandModel.getEndDate());
        int numInParty = commandModel.getNumInParty();
        ListRoomViewModel viewModel = new ListRoomViewModel();

        try {
            viewModel = roomWebService.getReservationRoomListViewModel(0, numInParty,
                    startDate, endDate);
        } catch (InvalidDatesException e) {
            viewModel.setMessage(e.getMessage());
        }

        model.addAttribute("commandModel", commandModel);
        model.addAttribute("viewModel", viewModel);

        return "/reservation/searchRooms";

    }

    @RequestMapping(value = "/searchResults", method = RequestMethod.GET)
    public String searchResults(String roomNum, String startDate, String endDate, String numInParty, Model model) {

        if(startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            String errorMsg = "Please enter start date, end date and number in party.";
            return "redirect: /reservation/displayRooms?message=" + errorMsg;
        }

        Integer numPersons = Integer.valueOf(numInParty);
        List<InputPersonDetailsViewModel> inputPersonDetailsViewModels = new ArrayList<>();

        for (int i=0; i<numPersons; i++) {
            InputPersonDetailsViewModel personDetailsViewModel = reservationWebService.getInputPersonDetailsViewModel();
            inputPersonDetailsViewModels.add(personDetailsViewModel);
        }

        SearchAvailableRoomsCommandModel searchCommandModel = new SearchAvailableRoomsCommandModel();
        searchCommandModel.setNumInParty(numPersons);
        searchCommandModel.setRoomNum(Integer.parseInt(roomNum.trim()));
        searchCommandModel.setStartDate(startDate);
        searchCommandModel.setEndDate(endDate);
        SearchAvailableRoomsViewModel searchAvailableRoomsViewModel = new SearchAvailableRoomsViewModel();
        searchAvailableRoomsViewModel.setCommandModel(searchCommandModel);


//        model.addAttribute("commandModel", personDetailsViewModel.getCommandModel());
//        model.addAttribute("searchCommandModel", searchCommandModel);

        SaveReservationCommandModel saveReservationCommandModel = new SaveReservationCommandModel();
        saveReservationCommandModel.setRoomsViewModel(searchAvailableRoomsViewModel);
        saveReservationCommandModel.setPersonDetailsViewModels(inputPersonDetailsViewModels);
        //model.addAttribute("personCommandModels", saveReservationCommandModel.getPersonDetailsCommandModels());
        model.addAttribute("commandModel", saveReservationCommandModel);

        return "/reservation/personDetails";

    }

    @RequestMapping(value = "/personDetails", method = RequestMethod.POST)
    public String saveCreate(@Valid @ModelAttribute("commandModel") SaveReservationCommandModel commandModel,
                                 BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            InputPersonDetailsViewModel viewModel =
                    reservationWebService.getInputPersonDetailsViewModel();

            model.addAttribute("viewModel",viewModel);
            model.addAttribute("commandModel",viewModel.getCommandModel());
            return "/reservation/personDetails";
        }


        Reservation reservation = new Reservation();

        try {
            reservation = reservationWebService.saveCreate(commandModel);
        } catch(InvalidDatesException | InvalidPromoException e) {
            InputPersonDetailsViewModel viewModel =
                    reservationWebService.getInputPersonDetailsViewModel();
            viewModel.setCommandModel(commandModel.getPersonDetailsViewModels().get(0).getCommandModel());
            viewModel.setMessage(e.getMessage());

            model.addAttribute("viewModel",viewModel);
            model.addAttribute("commandModel", commandModel);

            return "reservation/personDetails";
        }

        return "redirect:/reservation/profile?id=" + reservation.getId().toString();


    }

    @RequestMapping(value = "/profile")
    public String profile(@RequestParam Long id, Model model) {
        ProfileReservationViewModel viewModel = reservationWebService.getReservationProfileViewModel(id);

        model.addAttribute("viewModel",viewModel);

        return "/reservation/profile";
    }

    @RequestMapping(value = "/searchReservation")
    public String searchReservation(Model model, String reservationNumber) {
        SearchReservationCommandModel commandModel = reservationWebService.getSearchReservationCommandModel();

        if (reservationNumber != null) {
            if(!StringUtils.isNumber(reservationNumber)) {
                commandModel.setMessage("Invalid reservation number, please try again.");
            } else {

                Long reservationId = Long.valueOf(reservationNumber);
                ProfileReservationViewModel reservationViewModel =
                        reservationWebService.getReservationProfileViewModel(reservationId);
                if (reservationViewModel == null) {
                    commandModel.setMessage("Reservation not found.");

                } else {
                    model.addAttribute("viewModel", reservationViewModel);
                }
            }
        }

        model.addAttribute("commandModel",commandModel);

        return "/reservation/searchReservation";
    }

    @RequestMapping(value = "/cancelReservation")
    public String cancelReservation(Model model, String reservationNumber) {

        Long reservationId = Long.valueOf(reservationNumber);
        reservationWebService.cancelReservation(reservationId);

        return "/reservation/cancelSuccessful";

    }

    @RequestMapping (value= "/edit")
    public String edit(@RequestParam Long id, Model model){

        EditReservationViewModel viewModel = reservationWebService.getEditReservationViewModel(id);
        model.addAttribute("viewModel", viewModel);
        return "/reservation/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public  String saveEdit(@ModelAttribute("viewModel") EditReservationViewModel viewModel, BindingResult bindingResult, Model model) {

        EditReservationCommandModel commandModel = viewModel.getCommandModel();

        try {
            reservationWebService.saveEditReservationCommandModel(commandModel);
        } catch (InvalidPromoException ex) {
            viewModel = reservationWebService.getEditReservationViewModel(commandModel.getReservationId());
            viewModel.setMessage(ex.getMessage());
            model.addAttribute("viewModel", viewModel);
            return "reservation/edit";
        }
        return "redirect:reservation/profile?id=" + commandModel.getReservationId();

    }

}
