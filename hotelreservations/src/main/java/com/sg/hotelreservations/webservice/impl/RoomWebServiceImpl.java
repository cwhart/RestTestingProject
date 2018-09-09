package com.sg.hotelreservations.webservice.impl;

import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.service.serviceinterface.AmenityService;
import com.sg.hotelreservations.service.serviceinterface.RoomService;
import com.sg.hotelreservations.util.PagingUtils;
import com.sg.hotelreservations.viewmodels.amenity.AmenityViewModel;
import com.sg.hotelreservations.viewmodels.room.create.CreateRoomCommandModel;
import com.sg.hotelreservations.viewmodels.room.create.CreateRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.edit.EditRoomCommandModel;
import com.sg.hotelreservations.viewmodels.room.edit.EditRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.list.ListRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.list.RoomViewModel;
import com.sg.hotelreservations.viewmodels.room.profile.ProfileRoomViewModel;
import com.sg.hotelreservations.webservice.webinterface.RoomWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class RoomWebServiceImpl implements RoomWebService {

    RoomService roomService;
    AmenityService amenityService;

    @Inject
    public void RoomServiceWebImpl (RoomService roomService, AmenityService amenityService) {
        this.roomService = roomService;
        this.amenityService = amenityService;
    }

    @Override
    public ListRoomViewModel getRoomListViewModel(Integer offset) {
        Integer limit = 10;
        ListRoomViewModel viewModel = new ListRoomViewModel();

        int selectedPage = PagingUtils.getSelectedPage(offset, limit);
        int[] pageNumbers = PagingUtils.getPageNumbers(5, selectedPage);

        //Translate from domain to view models
        List<Room> roomList = roomService.retrieveAll(limit, offset);
        List<RoomViewModel> rooms = translate(roomList);

        viewModel.setSelectedPage(selectedPage);
        viewModel.setPageNumbers(pageNumbers);
        viewModel.setRooms(rooms);

        return viewModel;
    }

    private List<RoomViewModel> translate(List<Room> roomList) {

        List<RoomViewModel> viewModels = new ArrayList<>();

        for (Room room: roomList) {
            RoomViewModel viewModel = new RoomViewModel();
            viewModel.setFloornumber(room.getFloorNumber());
            viewModel.setRoomnumber(room.getRoomNumber());
            viewModel.setOccupancy(room.getOccupancy());
            viewModel.setType(room.getType());
            viewModel.setId(room.getId());
            List<Amenity> amenityList = amenityService.retrieveAmenityByRoom(room.getId());
            viewModel.setAmenities(amenityList);
            viewModels.add(viewModel);
        }

        return viewModels;

    }

    private List<AmenityViewModel> translateAmenities(List<Amenity> amenities) {
        List<AmenityViewModel> amenityViewModels = new ArrayList<>();

        for (Amenity amenity : amenities) {
            AmenityViewModel vm = new AmenityViewModel();
            vm.setType(amenity.getType());
        }

        return amenityViewModels;
    }

    @Override
    public ProfileRoomViewModel getRoomProfileViewModel(Long id) {
        //Instantiate stuff
        ProfileRoomViewModel viewModel = new ProfileRoomViewModel();

        //Look up stuff
        if (roomService.retrieve(id) == null) return null;
        Room room = roomService.retrieve(id);
        List<Amenity> amenityList = amenityService.retrieveAmenityByRoom(room.getId());

        //Set stuff
        viewModel.setFloorNumber(room.getFloorNumber());
        viewModel.setRoomNumber(room.getRoomNumber());
        viewModel.setOccupancy(room.getOccupancy());
        viewModel.setType(room.getType());

        if (amenityList != null && amenityList.size() > 0) {
            List <AmenityViewModel> amenityViewModels = translateAmenities(amenityList);
            viewModel.setAmenityList(amenityViewModels);
        }

//        //Return stuff
        return viewModel;
    }

    @Override
    public CreateRoomViewModel getCreateRoomViewModel() {
        CreateRoomViewModel viewModel = new CreateRoomViewModel();
        viewModel.setCommandModel(new CreateRoomCommandModel());

        return viewModel;
    }

    @Override
    public Room saveCreateRoomCommandModel(CreateRoomCommandModel commandModel) {
        Room room = new Room();
        room.setFloorNumber(commandModel.getFloorNumber());
        room.setRoomNumber(commandModel.getRoomNumber());
        room.setOccupancy(commandModel.getOccupancy());
        room.setType(commandModel.getType());


        room = roomService.create(room);
        return room;
    }

    @Override
    public EditRoomViewModel getEditRoomViewModel(Long id) {
        EditRoomViewModel model = new EditRoomViewModel();
        Room room = this.roomService.retrieve(id);

        EditRoomCommandModel commandModel = new EditRoomCommandModel();

        commandModel.setId(room.getId());
        commandModel.setFloorNumber(room.getFloorNumber());
        commandModel.setRoomNumber(room.getRoomNumber());
        commandModel.setOccupancy(room.getOccupancy());
        commandModel.setType(room.getType());
//
        model.setCommandModel(commandModel);
        return model;
    }

    @Override
    public void saveEditRoomCommandModel(EditRoomCommandModel commandModel) {

        Room room = roomService.retrieve(commandModel.getId());
        if(room == null) return;

        room.setFloorNumber(commandModel.getFloorNumber());
        room.setRoomNumber(commandModel.getRoomNumber());
        room.setOccupancy(commandModel.getOccupancy());
        room.setType(commandModel.getType());

        roomService.update(room);

    }

    @Override
    public void deleteRoom(Long id) {

        Room room = roomService.retrieve(id);
        // if the player doesn't exist, no need to continue
        if(room == null) return;

        // when all foreign key are deleted, we are now allowed to delete the player
        roomService.delete(room);

    }
}
