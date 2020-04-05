package com.postnovel.web.postnovelweb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.postnovel.web.postnovelweb.domain.Post;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class PostNovelServiceStub {
    private static final int wireMockPort = 8081;

    private WireMockServer postNovelServiceServer;
    private static final int USER_ID = 123;
    private static final int ID = 1;
    private static final String TITLE = "Some title";
    private static final String BODY = "Some post message";

    public void getPostNovelServiceServer() {
        if (postNovelServiceServer == null) {
            postNovelServiceServer = new WireMockServer(wireMockConfig().port(wireMockPort));
            postNovelServiceServer.start();
        }

    }

    public void stopPostNovelServiceServer() {
        postNovelServiceServer.stop();
    }

    public void stubGetPostMapping() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Post expectedPost = new Post(USER_ID, ID, TITLE, BODY);
        String postJsonString = mapper.writeValueAsString(expectedPost);
        System.out.println(postJsonString);

        postNovelServiceServer.stubFor(
                get(urlEqualTo("/posts/1"))
                        .willReturn(
                                aResponse()
                                        .withStatus(HttpStatus.OK.value())
                                        .withHeader(CONTENT_TYPE, "application/json")
                                        .withBody(postJsonString)
                        ));
    }
}
