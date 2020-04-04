package com.wiremock.example.springbootwiremockexample.client

import com.wiremock.example.springbootwiremockexample.domain.Post
import org.springframework.stereotype.Service

@Service
class PostClient {
    fun getAllPosts(): List<Post> {
        return emptyList()
    }
}