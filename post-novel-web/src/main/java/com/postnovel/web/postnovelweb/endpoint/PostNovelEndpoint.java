package com.postnovel.web.postnovelweb.endpoint;

import com.postnovel.web.postnovelweb.domain.Post;
import com.postnovel.web.postnovelweb.client.PostNovelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class PostNovelEndpoint {

    private PostNovelService postNovelService;

    public PostNovelEndpoint(PostNovelService postNovelService) {
        this.postNovelService = postNovelService;
    }

    @GetMapping(value = "/posts/{id}", produces = APPLICATION_JSON_VALUE)
    public Post retrieveAPost(@PathVariable int id) {
        return postNovelService.getPostById(id);
    }
}
