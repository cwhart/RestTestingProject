package com.sg.hotelreservations.webservice.impl;

import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.service.serviceinterface.ReservationService;
import com.sg.hotelreservations.util.PagingUtils;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesCommandModel;
import com.sg.hotelreservations.viewmodels.reservation.list.ListReservationViewModel;
import com.sg.hotelreservations.viewmodels.reservation.list.ReservationViewModel;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesViewModel;
import com.sg.hotelreservations.webservice.webinterface.ReservationWebService;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationWebServiceImpl implements ReservationWebService {

    ReservationService reservationService;

    @Inject
    public void ReservationServiceWebImpl (ReservationService reservationService) {
        this.reservationService = reservationService;

    }

    @Override
    public InputReservationDatesViewModel getReservationDatesViewModel() {
        InputReservationDatesViewModel viewModel = new InputReservationDatesViewModel();
        viewModel.setCommandModel(new InputReservationDatesCommandModel());

        return viewModel;
    }

    @Override
    public ListReservationViewModel getReservationListViewModel(Integer offset) {
        Integer limit = 10;
        ListReservationViewModel viewModel = new ListReservationViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<Reservation> reservationList = reservationService.retrieveAll(Integer.MAX_VALUE, offset);
        List<ReservationViewModel> reservations = translate(reservationList);

        viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setReservations(reservations);

        return viewModel;
    }

    @Override
    public Reservation searchInputDatesCommandModel(InputReservationDatesCommandModel inputReservationDatesCommandModel) {

        Reservation reservation = new Reservation();
        reservation.setStartDate(LocalDate.parse(inputReservationDatesCommandModel.getStartDate()));
        reservation.setEndDate(LocalDate.parse(inputReservationDatesCommandModel.getEndDate()));

        return reservation;
    }

    @Override
    public void deleteReservation(Long id) {

    }

    private List<ReservationViewModel> translate(List<Reservation> reservationList) {

        List<ReservationViewModel> viewModels = new ArrayList<>();

        for (Reservation reservation: reservationList) {
            ReservationViewModel viewModel = new ReservationViewModel();
            viewModel.setPromoId(reservation.getPromo().getId());
            viewModel.setReservationHolderId(reservation.getReservationHolder().getId());
            viewModel.setStartDate(reservation.getStartDate());
            viewModel.setEndDate(reservation.getEndDate());
            viewModel.setId(reservation.getId());
            viewModels.add(viewModel);
        }

        return viewModels;

    }

//
//    @Override
//    public ProfileRoomViewModel getRoomProfileViewModel(Long id) {
//        //Instantiate stuff
//        ProfileRoomViewModel viewModel = new ProfileRoomViewModel();
//
//        //Look up stuff
//        if (roomService.retrieve(id) == null) return null;
//        Room room = roomService.retrieve(id);
//        List<Amenity> amenityList = amenityService.retrieveAmenityByRoom(room.getId());
//
//        //Set stuff
//        viewModel.setFloorNumber(room.getFloorNumber());
//        viewModel.setRoomNumber(room.getRoomNumber());
//        viewModel.setOccupancy(room.getOccupancy());
//        viewModel.setType(room.getType());
//
//        if (amenityList != null && amenityList.size() > 0) {
//            List <AmenityViewModel> amenityViewModels = translateAmenities(amenityList);
//            viewModel.setAmenityList(amenityViewModels);
//        }
//
////        //Return stuff
//        return viewModel;
//    }
//
//    @Override
//    public CreateRoomViewModel getCreateRoomViewModel() {
//        CreateRoomViewModel viewModel = new CreateRoomViewModel();
//        viewModel.setCommandModel(new CreateRoomCommandModel());
//
//        return viewModel;
//    }
//
//    @Override
//    public Room saveCreateRoomCommandModel(CreateRoomCommandModel commandModel) {
//        Room room = new Room();
//        room.setFloorNumber(commandModel.getFloorNumber());
//        room.setRoomNumber(commandModel.getRoomNumber());
//        room.setOccupancy(commandModel.getOccupancy());
//        room.setType(commandModel.getType());
//
//
//        room = roomService.search(room);
//        return room;
//    }
//
//    @Override
//    public EditRoomViewModel getEditRoomViewModel(Long id) {
//        EditRoomViewModel model = new EditRoomViewModel();
//        Room room = this.roomService.retrieve(id);
//
//        EditRoomCommandModel commandModel = new EditRoomCommandModel();
//
//        commandModel.setId(room.getId());
//        commandModel.setFloorNumber(room.getFloorNumber());
//        commandModel.setRoomNumber(room.getRoomNumber());
//        commandModel.setOccupancy(room.getOccupancy());
//        commandModel.setType(room.getType());
////
//        model.setCommandModel(commandModel);
//        return model;
//    }
//
//    @Override
//    public void saveEditRoomCommandModel(EditRoomCommandModel commandModel) {
//
//        Room room = roomService.retrieve(commandModel.getId());
//        if(room == null) return;
//
//        room.setFloorNumber(commandModel.getFloorNumber());
//        room.setRoomNumber(commandModel.getRoomNumber());
//        room.setOccupancy(commandModel.getOccupancy());
//        room.setType(commandModel.getType());
//
//        roomService.update(room);
//
//    }
//
//    @Override
//    public void deleteRoom(Long id) {
//
//        Room room = roomService.retrieve(id);
//        // if the player doesn't exist, no need to continue
//        if(room == null) return;
//
//        // when all foreign key are deleted, we are now allowed to delete the player
//        roomService.delete(room);
//
//    }
}
