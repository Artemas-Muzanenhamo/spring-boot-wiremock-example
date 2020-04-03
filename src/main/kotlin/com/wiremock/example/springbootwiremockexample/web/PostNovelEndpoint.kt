package com.wiremock.example.springbootwiremockexample.web

import com.wiremock.example.springbootwiremockexample.domain.Post
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostNovelEndpoint {

    @GetMapping("/posts")
    fun retrieveAllPosts(): List<Post> {
        return listOf(Post(1234, 1, "Some title", "Post Body will be here..."))
    }
}