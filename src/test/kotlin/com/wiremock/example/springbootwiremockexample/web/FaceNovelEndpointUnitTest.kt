package com.wiremock.example.springbootwiremockexample.web

import com.wiremock.example.springbootwiremockexample.client.PostClient
import com.wiremock.example.springbootwiremockexample.domain.Post
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class FaceNovelEndpointUnitTest(
        @Mock
        private val postClient: PostClient) {

    @InjectMocks
    private val faceNovelEndpoint = PostNovelEndpoint(postClient)

    @Test
    @DisplayName("should return all FaceNovel posts")
    fun getAllPostsTest() {
        val expectedPosts = listOf((Post(1234, 1, "Some title", "Post Body will be here...")))
        `when`(postClient.getAllPosts()).thenReturn(expectedPosts)

        val posts = faceNovelEndpoint.retrieveAllPosts()

        assertThat(posts).isNotEmpty
        assertThat(posts).isNotEqualTo(expectedPosts)
    }
}