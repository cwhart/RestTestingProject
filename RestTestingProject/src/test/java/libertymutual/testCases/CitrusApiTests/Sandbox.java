package libertymutual.testCases.CitrusApiTests;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.junit.JUnit4CitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.JSONFunctions;
import model.Person;
import model.ReservationModel;
import model.SearchAvailableRoomsCommandModel;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.w3c.tidy.OutJavaImpl;

import java.util.ArrayList;
import java.util.List;

public class Sandbox extends JUnit4CitrusTestRunner {

    @Autowired
    private HttpClient RetrieveAllRooms;

    @Autowired
    private HttpClient RetrieveRoom;

    @Autowired
    private HttpClient SaveReservation;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @CitrusTest
    public void getAllRooms() {

        http(action -> action.client(RetrieveAllRooms)
                .send()
                .get()
                .messageType(MessageType.JSON)
                .contentType(ContentType.APPLICATION_JSON.getMimeType()));

        http(action -> action.client(RetrieveAllRooms)
                .receive()
                .response(HttpStatus.OK)
                .schemaValidation(true)
                .jsonSchema("room-list-schema"));
    }

    @Test
    @CitrusTest
    public void getRoom() {

        http(action -> action.client(RetrieveRoom)
                .send()
                .get("101")
                .messageType(MessageType.JSON)
                .contentType(ContentType.APPLICATION_JSON.getMimeType()));

        http(action -> action.client(RetrieveRoom)
                .receive()
                .response(HttpStatus.OK)
                .schemaValidation(true)
                .jsonSchema("room-schema"));

    }

    @Test
    @CitrusTest
    public void getRoomInvalidNumber() {

        http(action -> action.client(RetrieveRoom)
                .send()
                .get("999")
                .messageType(MessageType.JSON)
                .contentType(ContentType.APPLICATION_JSON.getMimeType()));

        http(action -> action.client(RetrieveRoom)
                .receive()
                .response(HttpStatus.valueOf(404)));

    }

    @Test
    @CitrusTest
    public void saveReservation() {
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person();
        person1.setDateOfBirth("1950-02-01");
        person1.setEmail("me@nospam.com");
        person1.setFirstName("Frank");
        person1.setLastName("Randall");
        person1.setPhoneNo("56473");
        persons.add(person1);

        SearchAvailableRoomsCommandModel commandModel = new SearchAvailableRoomsCommandModel();
        commandModel.setEndDate("2020-01-01");
        commandModel.setStartDate("2019-12-25");
        commandModel.setNumInParty(1);
        commandModel.setRoomNumber(101);

        ReservationModel reservationModel = new ReservationModel();
        reservationModel.setBillId(1L);
        reservationModel.setGuestDetails(persons);
        reservationModel.setReservationDetails(commandModel);

        http(action -> action.client(SaveReservation)
                .send()
                .post()
                .contentType(ContentType.APPLICATION_JSON.getMimeType())
                .payload(reservationModel, objectMapper));

        http(action -> action.client(SaveReservation)
                .receive()
                .response(HttpStatus.OK));

    }
}

