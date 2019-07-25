package com.sg.hotelreservations.controller;

import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.viewmodels.reservation.*;
import com.sg.hotelreservations.viewmodels.room.ListRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.RoomViewModel;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;
import com.sg.hotelreservations.webservice.exception.InvalidPromoException;
import com.sg.hotelreservations.webservice.webinterface.ReservationWebService;
import com.sg.hotelreservations.webservice.webinterface.RoomWebService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.h2.util.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
//@Controller
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
    @GetMapping(value = "/displayRooms")
    //@ApiOperation(value = "Find all data by createDts range (limit to 1000 results)", notes = "Example date/time value:  2017-11-28T12:40:00.000Z")
    public @ResponseBody SearchAvailableRoomsViewModel display(@RequestParam(required = false) Integer offset, Model model, String message) {

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



        return viewModel;

    }

    @GetMapping(value = "/searchRooms/{startDate}/{endDate}/{numInParty}")
    public @ResponseBody List<RoomViewModel> search(@PathVariable(value = "startDate", required = true) String startDate,
                        @PathVariable(value = "endDate", required = true) String endDate,
                        @PathVariable(value = "numInParty", required = true) int numInParty, Model model) throws InvalidDatesException{

        //Get startdate, enddate and numpersons from commandModel and input into this method:

        List<RoomViewModel> roomViewModels = new ArrayList<>();

        try {
            roomViewModels = roomWebService.getReservationRoomListViewModel(0, numInParty,
                    LocalDate.parse(startDate), LocalDate.parse(endDate));
        } catch (InvalidDatesException e) {
            //roomViewModels.setMessage(e.getMessage());
        }
        return roomViewModels;

    }

    @GetMapping(value = "/searchResults")
    public @ResponseBody
    ReservationModel searchResults(String roomNum, String startDate, String endDate, String numInParty, Model model) {

        if(startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            String errorMsg = "Please enter start date, end date and number in party.";
            //return "redirect: /reservation/displayRooms?message=" + errorMsg;
        }

        Integer numPersons = Integer.valueOf(numInParty);
        List<InputPersonDetailsCommandModel> inputPersonDetailsCommandModels = new ArrayList<>();

        SearchAvailableRoomsCommandModel searchCommandModel = new SearchAvailableRoomsCommandModel();
        searchCommandModel.setNumInParty(numPersons);
        searchCommandModel.setRoomNumber(Integer.parseInt(roomNum.trim()));
        searchCommandModel.setStartDate(startDate);
        searchCommandModel.setEndDate(endDate);


        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setReservationDetails(searchCommandModel);
        model.addAttribute("commandModel", reservationModel);

        return reservationModel;

    }

    @ApiOperation(value = "Save a reservation.", response = Reservation.class)
    @PostMapping(value = "/saveCreate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ReservationModel saveCreate(@ApiParam(name="saveReservation", required=true, value="Data required to save a reservation.")
                                 @Valid @RequestBody ReservationModel commandModel,
                                 BindingResult bindingResult) throws Exception {

        if(bindingResult.hasErrors()){
            InputPersonDetailsViewModel viewModel =
                    reservationWebService.getInputPersonDetailsViewModel();
        }

        ReservationModel reservationModel = new ReservationModel();

        reservationModel = reservationWebService.saveCreate(commandModel);


        return reservationModel;


    }

//    @GetMapping(value = "/profile")
//    public @ResponseBody ProfileReservationViewModelDEPRECATED profile(@RequestParam Long id, Model model) {
//        ProfileReservationViewModelDEPRECATED viewModel = reservationWebService.getReservationProfileViewModel(id);
//
//        model.addAttribute("viewModel",viewModel);
//
//        return viewModel;
//    }

    @GetMapping(value = "retrieveAll")
    public @ResponseBody List<Reservation> retrieveAllReservations() {
        return reservationWebService.retrieveAllReservations();
    }

    @GetMapping(value = "/searchReservation/{id}")
    public @ResponseBody
    ReservationModel searchReservation(Model model,
                                       @PathVariable(value = "id", required = true)String id) {
        SearchReservationCommandModel commandModel = reservationWebService.getSearchReservationCommandModel();
        ReservationModel reservationModel = new ReservationModel();

            if (id != null) {
            if(!StringUtils.isNumber(id)) {
                commandModel.setMessage("Invalid reservation number, please try again.");
            } else {

                Long reservationId = Long.valueOf(id);
                reservationModel =
                        reservationWebService.getReservationModel(reservationId);
                if (reservationModel == null) {
                    commandModel.setMessage("Reservation not found.");

                } else {
                    model.addAttribute("viewModel", reservationModel);
                }
            }
        }

        model.addAttribute("commandModel",commandModel);

        return reservationModel;
    }

    @DeleteMapping(value = "/cancelReservation/{reservationId}")
    public void cancelReservation(Model model, @PathVariable(value = "reservationId", required = true)
            Long reservationId) {

        //Long reservationId = Long.valueOf(reservationNumber);
        reservationWebService.cancelReservation(reservationId);

        return ;

    }

//    @GetMapping (value= "/edit")
//    public @ResponseBody EditReservationViewModel edit(@RequestParam Long id, Model model){
//
//        EditReservationViewModel viewModel = reservationWebService.getEditReservationViewModel(id);
//        model.addAttribute("viewModel", viewModel);
//        return viewModel;
//    }

    @ApiOperation(value = "Edit a reservation.", response = Reservation.class)
    @PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  @ResponseBody ReservationModel saveEdit(@ModelAttribute("viewModel") ReservationModel reservation) {

       // EditReservationCommandModel commandModel = viewModel.getCommandModel();
        ReservationModel updatedRes = new ReservationModel();

        try {
            updatedRes = reservationWebService.saveEditReservation(reservation);
        } catch (InvalidPromoException ex) {
//            viewModel = reservationWebService.getEditReservationViewModel(commandModel.getReservationId());
//            viewModel.setMessage(ex.getMessage());
//            model.addAttribute("viewModel", viewModel);

        }
        return updatedRes;

    }

}
