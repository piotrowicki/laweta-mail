package pl.com.laweta.controller;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import pl.com.laweta.TestConfiguration;
import pl.com.laweta.dto.MailDto;

@QuarkusTest
@TestProfile(TestConfiguration.class)
public class MailControllerTest {

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void testTextMail() throws IOException {
        // when
        MailDto dto = new MailDto();
        dto.setEmail("test@test.pl");
        dto.setName("test");
        dto.setText("test");

        // then
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(mapper.writeValueAsString(dto))
        .when()
            .post("/api/mail/send")
        .then()
            .statusCode(200);
    }

    @Test
    void testEmptyTextMail() throws IOException {
        // when
        MailDto dto = new MailDto();

        // then
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .body(mapper.writeValueAsString(dto))
        .when()
            .post("/api/mail/send")
        .then()
            .statusCode(400);
    }

}