package com.postnovel.web.postnovelweb.client;

import com.postnovel.web.postnovelweb.domain.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PostNovelService {

    public Post getPostById(int id) {
        final String uri = String.format("http://localhost:8081/posts/%s", id);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, Post.class);
    }
}
