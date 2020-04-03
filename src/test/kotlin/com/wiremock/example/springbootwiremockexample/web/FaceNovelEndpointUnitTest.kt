package com.wiremock.example.springbootwiremockexample.web

import com.google.gson.Gson
import com.wiremock.example.springbootwiremockexample.client.PostClient
import com.wiremock.example.springbootwiremockexample.domain.Post
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class FaceNovelEndpointUnitTest {

    @Mock
    private val postClient = PostClient()
    private val faceNovelEndpoint = PostNovelEndpoint(postClient)

    @Test
    fun `should return all FaceNovel posts`() {
        val gson = Gson()
        val expectedPosts = arrayListOf<String>(gson.toJson(Post(1234, 1, "Some title", "Post Body will be here...")))

        val posts = faceNovelEndpoint.retrieveAllPosts()

        assertThat(posts).isNotEmpty
    }
}