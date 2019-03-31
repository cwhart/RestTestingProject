package com.sg.hotelreservations.webservice.impl;

import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.service.serviceinterface.AmenityService;
import com.sg.hotelreservations.service.serviceinterface.ReservationRoomService;
import com.sg.hotelreservations.service.serviceinterface.RoomRateService;
import com.sg.hotelreservations.service.serviceinterface.RoomService;
import com.sg.hotelreservations.util.PagingUtils;
import com.sg.hotelreservations.viewmodels.reservation.SearchAvailableRoomsCommandModel;
import com.sg.hotelreservations.viewmodels.reservation.SearchAvailableRoomsViewModel;
import com.sg.hotelreservations.viewmodels.room.AmenityViewModel;
import com.sg.hotelreservations.viewmodels.room.ListRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.RoomViewModel;
import com.sg.hotelreservations.webservice.exception.InvalidDatesException;
import com.sg.hotelreservations.webservice.webinterface.RoomWebService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomWebServiceImpl implements RoomWebService {

    RoomService roomService;
    AmenityService amenityService;
    ReservationRoomService reservationRoomService;
    RoomRateService roomRateService;

    @Inject
    public void RoomWebServiceImpl (RoomService roomService, AmenityService amenityService,
                                    ReservationRoomService reservationRoomService, RoomRateService roomRateService) {
        this.roomService = roomService;
        this.amenityService = amenityService;
        this.reservationRoomService = reservationRoomService;
        this.roomRateService = roomRateService;
    }

    @Override
    public List<RoomViewModel> getListOfRooms() {
        List<Room> roomList = roomService.retrieveAll(Integer.MAX_VALUE, 0);

        return translate(roomList, null, null);
    }

    @Override
    public RoomViewModel retrieveRoom(int roomNumber) {
        Room room = roomService.retrieveByRoomNumber(roomNumber);
        List<Room> roomList = new ArrayList<>();
        roomList.add(room);
        return translate(roomList, null, null).get(0);
    }

    @Override
    public ListRoomViewModel getRoomListViewModel(Integer offset) {
        Integer limit = 10;
        ListRoomViewModel viewModel = new ListRoomViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<Room> roomList = roomService.retrieveAll(limit, offset);
        List<RoomViewModel> rooms = translate(roomList, null, null);

        viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setRooms(rooms);

        return viewModel;
    }

    @Override
    public List<RoomViewModel> getReservationRoomListViewModel(Integer offset, Integer numPersons,
                                           LocalDate start, LocalDate end) throws InvalidDatesException {

        Integer limit = 10;

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);
        SearchAvailableRoomsCommandModel commandModel = new SearchAvailableRoomsCommandModel();
        if (start.isBefore(LocalDate.now())) {
            throw new InvalidDatesException("Start date cannot be before today, please try again.") ;

        }

        if(end.isBefore(start)) {
            throw new InvalidDatesException("Start date cannot be after end date, please try again.") ;

        }

        commandModel.setStartDate(start.toString());
        commandModel.setEndDate(end.toString());
        commandModel.setNumInParty(numPersons);
        SearchAvailableRoomsViewModel searchAvailableRoomsViewModel = new SearchAvailableRoomsViewModel();
        searchAvailableRoomsViewModel.setCommandModel(commandModel);


        //Get the list of Rooms that are not booked for the specified date range.

        List<Room> roomList = roomService.retrieveAll(Integer.MAX_VALUE, 0);
        List<Room> toRemove = new ArrayList<>();
        for (Room thisRoom : roomList) {
            if (reservationRoomService.isBookedForDateRange(thisRoom.getRoomNumber(), start, end) ||
                    numPersons > thisRoom.getOccupancy()) {
                toRemove.add(thisRoom);
            }

        }

        roomList.removeAll(toRemove);

        List<RoomViewModel> rooms = translate(roomList, start, end);

        return rooms;

    }

    private List<RoomViewModel> translate(List<Room> roomList, LocalDate start, LocalDate end) {

        List<RoomViewModel> viewModels = new ArrayList<>();

        for (Room room: roomList) {
            RoomViewModel viewModel = new RoomViewModel();
            viewModel.setRoomNumber(room.getRoomNumber());
            viewModel.setOccupancy(room.getOccupancy());
            viewModel.setType(room.getType());
            viewModel.setId(room.getId());
            List<Amenity> amenityList = amenityService.retrieveAmenityByRoom(room.getId());
            viewModel.setAmenityList(amenityList);
            //if start and end are null, then get the default date.
            //if start and end are not null, then check the current rate. If null, then set the default rate.
            if (start == null) {
                viewModel.setRate(roomRateService.retrieveDefaultRate(room.getId()).getPrice());
            } else {
                if (roomRateService.retrieveCurrentRate(room.getId(), start, end).getRoom() != null) {
                    viewModel.setRate(roomRateService.retrieveCurrentRate(room.getId(), start, end).getPrice());
                } else viewModel.setRate(roomRateService.retrieveDefaultRate(room.getId()).getPrice());
            }


            viewModels.add(viewModel);
        }

        return viewModels;

    }

//    private List<AmenityViewModel> translateAmenities(List<Amenity> amenities) {
//        List<AmenityViewModel> amenityViewModels = new ArrayList<>();
//
//        for (Amenity amenity : amenities) {
//            AmenityViewModel vm = new AmenityViewModel();
//            vm.setType(amenity.getType());
//        }
//
//        return amenityViewModels;
//    }


    @Override
    public void deleteRoom(Long id) {

    }
}
