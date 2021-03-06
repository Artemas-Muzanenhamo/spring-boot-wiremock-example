package com.postnovel.integrationtest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.postnovel.web.postnovelweb.domain.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Component
public class PostNovelServiceStub {
    @Value("${postnovel.client.port}")
    private int port;
    private WireMockServer postNovelServiceServer;

    private static final int USER_ID = 123;
    private static final int ID = 1;
    private static final String TITLE = "Stubbed title";
    private static final String BODY = "Stubbed post message";

    public void getPostNovelServiceServer() {
        if (isNull(postNovelServiceServer)) {
            postNovelServiceServer = new WireMockServer(wireMockConfig().port(port));
            postNovelServiceServer.start();
        }

    }

    public void stopPostNovelServiceServer() {
        if (nonNull(postNovelServiceServer)) {
            postNovelServiceServer.stop();
        }
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