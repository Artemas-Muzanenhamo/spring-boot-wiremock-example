package com.postnovel.web.postnovelweb.endpoint;

import com.postnovel.web.postnovelweb.WireMockConfig;
import com.postnovel.web.postnovelweb.domain.Post;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostNovelEndpointIntegrationTest {

    private static final int USER_ID = 123;
    private static final int ID = 1;
    private static final String TITLE = "Some title";
    private static final String BODY = "Some post message";
    private WireMockConfig wireMockConfig = new WireMockConfig();
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        wireMockConfig.getPostNovelServiceServer();
        RestAssured.port = port;
    }

    @AfterEach
    void cleanup() {
        wireMockConfig.stopPostNovelServiceServer();
    }

    @Test
    @DisplayName("Should get a single PostNovel post by id")
    void getPostById() throws Exception {
        RestAssured.defaultParser = Parser.JSON;
        Post expectedPost = new Post(USER_ID, ID, TITLE, BODY);
        wireMockConfig.stubGetPostMapping();

        Post post = given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(Post.class);

        assertThat(post).isNotNull();
        assertThat(post).isEqualTo(expectedPost);
    }
}