package pl.com.laweta.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class LawetaMailTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/mail/send")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}