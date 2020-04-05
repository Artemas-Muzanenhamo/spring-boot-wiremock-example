package com.postnovel.web.postnovelweb.client;

import com.postnovel.web.postnovelweb.domain.Post;
import org.springframework.stereotype.Service;

@Service
public class PostNovelService {
    public Post getPostById(int id) {
        return new Post(123, 1, "Some title", "Some post message");
    }
}
