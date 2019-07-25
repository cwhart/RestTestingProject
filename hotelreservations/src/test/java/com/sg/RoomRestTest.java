package com.sg;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import com.sg.hotelreservations.viewmodels.reservation.ReservationModel;
import com.sg.hotelreservations.viewmodels.room.RoomViewModel;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.joda.time.LocalDate;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RoomRestTest extends FunctionalTest {

    @Test
    public void invalidRoomNumber() {
        given().when().get("/room/retrieve/999")
                .then().statusCode(404);
    }

    @Test
    public void validRoomNumber() {

        given().when().get("/room/retrieve/101").then()
                .body(matchesJsonSchemaInClasspath("jsonSchemas/room-schema.json"))
                .body("roomNumber",equalTo(101))
                .body("type",equalTo("Double"))
                .body("occupancy",equalTo(4))
                .body("amenityList[0].id", equalTo(3))
                .body("amenityList[0].type", equalTo("Minibar"))
                .body("amenityList[1].id", equalTo(2))
                .body("amenityList[1].type", equalTo("Ocean View"))
                .body("rate",equalTo("99.00"))
                .statusCode(200);

        //NOTE: to validate JSON does not include additional elements that we aren't specifically looking for,
        //add the following to the schema.json at the end after the properties:
        //  "additionalProperties": false
    }

    @Test
    public void roomNumberAlpha() {
        given().when().get("/room/retrieve/fred")
                .then().statusCode(400);
    }

    @Test
    public void testRetrieveAllRooms() {

        RoomViewModel[] rooms = get("/room/retrieveAll").then()
                //.body(matchesJsonSchemaInClasspath("com/lmig/jsonSchemas/room-list-schema.json"))
                .statusCode(200)
                .extract()
                .as(RoomViewModel[].class);
                assertTrue(rooms.length > 1);
    }

    @Test
    public void testSaveCreateCancelReservation() {

        RestAssured.baseURI ="http://localhost:8080/";
        RequestSpecification request = RestAssured.given();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("billId", 222);
        //jsonObject.put("reservationId", 4444);
        JSONArray guestDetailsArray = new JSONArray();
        JSONArray reservationDetailsArray = new JSONArray();

        JSONObject guestDetails = new JSONObject();
        guestDetails.put("dateOfBirth", "2001-10-01");
        guestDetails.put("email", "me@nospam.com");
        guestDetails.put("firstName", "Cersei");
        guestDetails.put("id", 2222);
        guestDetails.put("lastName", "Lannister");
        guestDetails.put("phoneNo", "1234567890");

        guestDetailsArray.add(guestDetails);
        jsonObject.put("guestDetails", guestDetailsArray);

        JSONObject reservationDetails = new JSONObject();
        reservationDetails.put("endDate", LocalDate.now().plusDays(1).toString());
        reservationDetails.put("numInParty", 3);
        reservationDetails.put("roomNumber", 101);
        reservationDetails.put("startDate", LocalDate.now().toString());

        reservationDetailsArray.add(reservationDetails);
        jsonObject.put("reservationDetails", reservationDetails);

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(jsonObject.toJSONString());

        // Post the request and check the response
        //Response response = request.post("reservation/saveCreate");
        ReservationModel model = request.post("http://localhost:8080/reservation/saveCreate")
                .then().statusCode(200)
                //.body(matchesJsonSchemaInClasspath("com/lmig/jsonSchemas/reservation-response-schema.json"))
                .body("guestDetails[0].firstName", equalTo("Cersei"))
                .extract()
                .as(ReservationModel.class);

        Long reservationId = model.getReservationId();

        given().when().delete("/reservation/cancelReservation/" + reservationId).then()
                .statusCode(200);
    }


}
