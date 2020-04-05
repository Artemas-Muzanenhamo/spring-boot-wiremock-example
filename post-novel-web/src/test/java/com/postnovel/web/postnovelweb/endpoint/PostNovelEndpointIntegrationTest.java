package com.postnovel.web.postnovelweb.endpoint;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostNovelEndpointIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("Should get a single PostNovel post by id")
    void getPostById() {
        String response = given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .prettify();

//        assertThat(response).isEqualTo(matchesJsonSchemaInClasspath("json/posts.json"));
    }
}