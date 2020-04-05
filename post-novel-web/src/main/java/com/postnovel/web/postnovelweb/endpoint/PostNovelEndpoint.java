package com.postnovel.web.postnovelweb.endpoint;

import com.postnovel.web.postnovelweb.domain.Post;
import com.postnovel.web.postnovelweb.service.PostNovelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostNovelEndpoint {

    private PostNovelService postNovelService;

    public PostNovelEndpoint(PostNovelService postNovelService) {
        this.postNovelService = postNovelService;
    }

    @GetMapping("/posts/{id}")
    public Post retrieveAPost(@PathVariable int id) {
        return postNovelService.getPostById(id);
    }
}
