package com.sg.hotelreservations.restApi;

import com.jayway.restassured.RestAssured;
import com.sg.hotelreservations.controller.RoomController;
import com.sg.hotelreservations.dto.Amenity;
import com.sg.hotelreservations.dto.Room;
import com.sg.hotelreservations.dto.RoomRate;
import com.sg.hotelreservations.service.serviceinterface.AmenityService;
import com.sg.hotelreservations.service.serviceinterface.RoomRateService;
import com.sg.hotelreservations.service.serviceinterface.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Matchers.anyLong;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = RoomController.class)
public class RoomApi {


    @InjectMocks
    private RoomController controller;

    @Mock
    RoomService roomService;

    @Mock
    AmenityService amenityService;

    @Mock
    RoomRateService roomRateService;

    @Test
    public void shouldReturnListOfAllRooms(){

        List<Room> roomList = new ArrayList<>();
        Room room1 = new Room();
        room1.setRoomNumber(101);
        room1.setOccupancy(2);
        room1.setId(1L);
        room1.setType("King");
        room1.setFloorNumber(1);
        roomList.add(room1);

        Room room2 = new Room();
        room2.setRoomNumber(201);
        room2.setOccupancy(4);
        room2.setId(1L);
        room2.setType("Double");
        room2.setFloorNumber(4);
        roomList.add(room2);

        List<Amenity> amenityList = new ArrayList<>();
        Amenity amenity1 = new Amenity();
        amenity1.setType("Hot Tub");
        amenity1.setId(1L);
        amenityList.add(amenity1);

        RoomRate roomRate1 = new RoomRate();
        roomRate1.setId(1L);
        roomRate1.setPrice(new BigDecimal("159.99"));
        roomRate1.setRoom(room1);

        RoomRate roomRate2 = new RoomRate();
        roomRate2.setId(2L);
        roomRate2.setPrice(new BigDecimal("139.99"));
        roomRate2.setRoom(room1);

        //TODO: mocks are not working, need to determine how to configure them for restAssured.
        when(roomService.retrieveAll(anyInt(), anyInt())).thenReturn(roomList);
        when(amenityService.retrieveAmenityByRoom(anyLong())).thenReturn(amenityList);
        when(roomRateService.retrieveDefaultRate(1L)).thenReturn(roomRate1);
        when(roomRateService.retrieveDefaultRate(2L)).thenReturn(roomRate2);

		/*
		 * 	GIVEN I make a call to '/room/retrieveAll' to get a list of all rooms
			WHEN I retrieve the response
			THEN the statusCode should be 200
				AND the response body should be:
				[
				    {
                        "id" : "<<idNum>>",
                        "roomNumber" : "101",
                        "type" : "Double",
                        "occupancy" : "4",
                        "amenityList" [
                        {
                            "id" : "<<idNum>>",
                            "type" : "Balcony"
                        }
                            "id" : "<<idNum>>",
                            "type" : "Hot Tub"
                        {
                        }
                        ],
                        "rate" : "99.78"
				},
				{
                        "id" : "<<idNum>>",
                        "roomNumber" : "201",
                        "type" : "King",
                        "occupancy" : "2",
                        "amenityList" [
                        {
                            "id" : "<<idNum>>",
                            "type" : "Fireplace"
                        }
                            "id" : "<<idNum>>",
                            "type" : "Hot Tub"
                        {
                        }
                        ],
                        "rate" : "115.99"
				}
				]
		 */

        //Used to store json data for our POST to create a new
//        Map<String, Object> jsonAsMap = new HashMap<String, Object>();
//        jsonAsMap.put("firstName","Michael");
//        jsonAsMap.put("lastName","Jackson");
//        jsonAsMap.put("address1", "123 Hillside");
//        jsonAsMap.put("address2","Suite 200");
//        jsonAsMap.put("city","Hollywood");
//        jsonAsMap.put("state","CA");
//        jsonAsMap.put("zip","90210");
//        jsonAsMap.put("phone","317-999-9999");

        RestAssured.given()
                .auth().preemptive().basic("test_user","1234")
                .contentType("application/json")
                .log().all().
                when()
                .get("room/retrieveAll").
                then()
                .log().all()
                .statusCode(200)
                .body("roomNumber", equalTo("[101, 102, 201, 202, 301]"));

    }

}
