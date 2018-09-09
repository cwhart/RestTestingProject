package com.sg.hotelreservations.webservice.webinterface;

import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.viewmodels.room.create.CreateRoomCommandModel;
import com.sg.hotelreservations.viewmodels.room.create.CreateRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.edit.EditRoomCommandModel;
import com.sg.hotelreservations.viewmodels.room.edit.EditRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.list.ListRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.profile.ProfileRoomViewModel;

public interface RoomWebService {

    public ListRoomViewModel getRoomListViewModel(Integer offset);

    public ProfileRoomViewModel getRoomProfileViewModel(Long id); //takes in ID since that's what is sent to the
//    //page via URL so it can load.
//
//    //These two are input/output for search room
    public CreateRoomViewModel getCreateRoomViewModel();
    public Room saveCreateRoomCommandModel(CreateRoomCommandModel commandModel);
//
//    //do not re-use search for edit. Make models for edit. Same pattern.
    public EditRoomViewModel getEditRoomViewModel(Long id);
    public void saveEditRoomCommandModel(EditRoomCommandModel commandModel);
//
    public void deleteRoom (Long id);
}
