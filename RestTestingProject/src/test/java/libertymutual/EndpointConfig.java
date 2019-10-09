package libertymutual;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.message.MessageType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;

@Configuration
@PropertySource("citrus.properties")
public class EndpointConfig {

    public static String ENVIRONMENT_NON_PROD = "test"; //The value of this variable is set equal to the <Environment> tag value in the GAR request xml. Change this when switching environments.
    public static String ENVIRONMENT_LOCAL = "local"; //Do not change
    public static String ENVIRONMENT_PROD = "production"; //Do not change
    public static String XML_ENVIRONMENT_VARIABLE = "environment"; //Do not change

    @Bean
    public com.consol.citrus.http.client.HttpClient RetrieveAllRooms() {
        return CitrusEndpoints.http()
                .client()
                .requestUrl("http://localhost:8080/room/retrieveAll")
                .build();
    }

    @Bean
    public com.consol.citrus.http.client.HttpClient RetrieveRoom() {
        return CitrusEndpoints.http()
                .client()
                .requestUrl("http://localhost:8080/room/retrieve/")
                .build();
    }

    @Bean
    public com.consol.citrus.http.client.HttpClient SaveReservation() {
        return CitrusEndpoints.http()
                .client()
                .requestUrl("http://localhost:8080/reservation/saveCreate/")
                .build();
    }

}