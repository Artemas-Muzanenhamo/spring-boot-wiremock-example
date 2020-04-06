package com.postnovel.service.postnovelservice.api;

import com.postnovel.service.postnovelservice.domain.Post;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class PostsEndpoint {

    @GetMapping(value = "/posts/{id}", produces = APPLICATION_JSON_VALUE)
    public Post findPostById(@PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(APPLICATION_JSON));

        HttpEntity<Post> request = new HttpEntity<>(httpHeaders);

        final String url = String.format("https://jsonplaceholder.typicode.com/posts/%s", id);

        return restTemplate.exchange(url, GET, request, Post.class).getBody();
    }
}
