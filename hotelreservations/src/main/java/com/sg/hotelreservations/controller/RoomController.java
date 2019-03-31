package com.sg.hotelreservations.controller;

import com.sg.hotelreservations.viewmodels.room.ListRoomViewModel;
import com.sg.hotelreservations.viewmodels.room.RoomViewModel;
import com.sg.hotelreservations.webservice.webinterface.RoomWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping(value = "/room")
public class RoomController {

    @Autowired
    RoomWebService roomWebService;

    @GetMapping(value = "retrieveAll")
    public @ResponseBody List<RoomViewModel> retrieveAllRooms() {
        return roomWebService.getListOfRooms();
    }

    @GetMapping(value = "retrieve/{roomNumber}")
    public @ResponseBody RoomViewModel retrieveRoom(@PathVariable(value = "roomNumber", required = true) int roomNumber) {
        return roomWebService.retrieveRoom(roomNumber);
    }



}
