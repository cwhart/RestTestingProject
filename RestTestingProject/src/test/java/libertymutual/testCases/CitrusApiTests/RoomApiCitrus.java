package libertymutual.testCases.CitrusApiTests;//package com.sg.hotelreservations.restApi;

import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.context.TestContext;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.*;
import com.consol.citrus.message.MessageType;
import libertymutual.EndpointConfig;
import org.apache.http.entity.ContentType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@ContextConfiguration(classes = { EndpointConfig.class })
public class RoomApiCitrus extends TestNGCitrusTestRunner {

        @Autowired
        private HttpClient RetrieveAllRooms;

        @Test
        @Parameters("context")
        @CitrusTest
        public void frameworkTestForHotelReservationClient(@Optional @CitrusResource TestContext context) {

            http(httpActionBuilder -> httpActionBuilder
                    .client(RetrieveAllRooms)
                    .send()
                    .get()
                    .messageType(MessageType.JSON)
                    .contentType(ContentType.APPLICATION_JSON.getMimeType()));

            String returnString = "";

            http(httpActionBuilder -> httpActionBuilder
                    .client(RetrieveAllRooms)
                    .receive()
                    .response(HttpStatus.OK)
                    .messageType(MessageType.JSON)
                    .name("sampleMessage")
                    .schemaValidation(true));
                    //.payload(matchesJsonSchemaInClasspath("templates/room-list-schema.json"))
                    //.extractFromPayload("$..*", "returnStr"));

            //returnString = context.getVariable("returnStr");
            //System.out.println("Return String: " + context.getVariable("returnStr"));
            //assertThat(context.getVariable("returnStr"), matchesJsonSchemaInClasspath("templates/room-list-schema.json"));
            //assertThat(returnString, matchesJsonSchemaInClasspath("templates/room-list-schema.json"));


        }
    }
