package com.postnovel.integrationtest.endpoint;

import com.postnovel.integrationtest.PostNovelServiceStub;
import com.postnovel.web.postnovelweb.PostNovelWebApplication;
import com.postnovel.web.postnovelweb.domain.Post;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PostNovelWebApplication.class
)
@ContextConfiguration(classes = PostNovelServiceStub.class)
@TestPropertySource("/test.properties")
class PostNovelEndpointIntegrationTest {

    private static final int USER_ID = 123;
    private static final int ID = 1;
    private static final String TITLE = "Stubbed title";
    private static final String BODY = "Stubbed post message";
    @Autowired
    private PostNovelServiceStub postNovelServiceStub;
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        postNovelServiceStub.getPostNovelServiceServer();
        RestAssured.port = port;
    }

    @AfterEach
    void cleanup() {
        postNovelServiceStub.stopPostNovelServiceServer();
    }

    @Test
    @DisplayName("Should get a single PostNovel post by id")
    void getPostById() throws Exception {
        RestAssured.defaultParser = Parser.JSON;
        Post expectedPost = new Post(USER_ID, ID, TITLE, BODY);
        postNovelServiceStub.stubGetPostMapping();

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