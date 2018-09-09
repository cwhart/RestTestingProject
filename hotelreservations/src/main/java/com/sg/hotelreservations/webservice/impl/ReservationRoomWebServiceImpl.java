package com.sg.hotelreservations.webservice.impl;

import com.sg.hotelreservations.dto.Promo;
import com.sg.hotelreservations.dto.Reservation;
import com.sg.hotelreservations.dto.ReservationRoom;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.service.serviceinterface.PromoService;
import com.sg.hotelreservations.service.serviceinterface.ReservationRoomService;
import com.sg.hotelreservations.service.serviceinterface.ReservationService;
import com.sg.hotelreservations.service.serviceinterface.RoomService;
import com.sg.hotelreservations.util.PagingUtils;
import com.sg.hotelreservations.viewmodels.reservationroom.list.ListReservationRoomViewModel;
import com.sg.hotelreservations.viewmodels.reservationroom.list.ReservationRoomViewModel;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesCommandModel;
import com.sg.hotelreservations.viewmodels.reservationroom.search.InputReservationDatesViewModel;
import com.sg.hotelreservations.webservice.webinterface.ReservationRoomWebService;
import org.springframework.cglib.core.Local;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ReservationRoomWebServiceImpl implements ReservationRoomWebService {

    RoomService roomService;
    ReservationService reservationService;
    ReservationRoomService reservationRoomService;
    PromoService promoService;

    @Inject
    public void RoomServiceWebImpl (RoomService roomService, ReservationService reservationService, ReservationRoomService
                                    reservationRoomService, PromoService promoService) {
        this.roomService = roomService;
        this.reservationService = reservationService;
        this.reservationRoomService = reservationRoomService;
        this.promoService = promoService;
    }

    @Override
    public ListReservationRoomViewModel getReservationRoomListViewModel(Integer offset, Integer numPersons,
                                                                        LocalDate start, LocalDate end) {
        Integer limit = 10;
        ListReservationRoomViewModel viewModel = new ListReservationRoomViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);
        InputReservationDatesCommandModel commandModel = new InputReservationDatesCommandModel();
        commandModel.setStartDate(start.toString());
        commandModel.setEndDate(end.toString());
        commandModel.setNumPersons(numPersons);

        //Get the list of Rooms that are not booked for the specified date range.

        List<Room> roomList = roomService.retrieveAll(Integer.MAX_VALUE, 0);
        List<Room> availableRooms = roomService.retrieveAll(Integer.MAX_VALUE, 0);
        for (Room thisRoom : roomList) {
            if (reservationRoomService.isBookedForDateRange(thisRoom.getId(), start, end) ||
                    numPersons > thisRoom.getOccupancy()) {
                availableRooms.remove(thisRoom);
            }

        }

        //List<ReservationRoom> reservationRoomList = reservationRoomService.retrieveByDates(start, end);

        List<ReservationRoomViewModel> reservationRooms = translate(availableRooms, commandModel);

        viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setInputReservationDatesCommandModel(commandModel);
        viewModel.setReservationRooms(reservationRooms);

        return viewModel;
    }

    @Override
    public InputReservationDatesViewModel getInputReservationDatesViewModel(LocalDate startDate, LocalDate endDate) {
        InputReservationDatesViewModel viewModel = new InputReservationDatesViewModel();
        viewModel.setCommandModel(new InputReservationDatesCommandModel());

        return viewModel;
    }

    @Override
    public void deleteReservation(Long id) {

    }

    //Take in a list of ReservationRooms and translate to ReservationViewModels
    private List<ReservationRoomViewModel> translate(List<Room> roomList, InputReservationDatesCommandModel cmd) {

        List<ReservationRoomViewModel> viewModels = new ArrayList<>();
        for (Room thisRoom : roomList) {
            ReservationRoomViewModel viewModel = new ReservationRoomViewModel();
            viewModel.setRoomNumber(thisRoom.getRoomNumber());
            viewModel.setRoomType(thisRoom.getType());
            viewModel.setOccupancy(thisRoom.getOccupancy());
            viewModel.setId(thisRoom.getId());
            viewModels.add(viewModel);
        }

        return viewModels;

    }

}
