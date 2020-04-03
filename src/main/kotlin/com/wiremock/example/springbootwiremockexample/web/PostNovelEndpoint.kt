package com.wiremock.example.springbootwiremockexample.web

import com.wiremock.example.springbootwiremockexample.client.PostClient
import com.wiremock.example.springbootwiremockexample.domain.Post
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostNovelEndpoint(
        @Autowired
        private val postClient: PostClient
) {

    @GetMapping("/posts")
    fun retrieveAllPosts(): List<Post> {
        return postClient.getAllPosts()
    }
}