package com.wiremock.example.springbootwiremockexample.web

import com.google.gson.Gson
import com.wiremock.example.springbootwiremockexample.domain.Post
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ExtendWith(SpringExtension::class)
@WebMvcTest(PostNovelEndpoint::class)
class FaceNovelEndpointTest(
        @Autowired
        private val mockMvc: MockMvc
) {

    @Test
    fun `get all FaceNovel posts`() {
        val gson = Gson()
        val posts = arrayListOf<String>(gson.toJson(Post(1234, 1, "Some title", "Post Body will be here...")))

        mockMvc.get("/posts")
                .andExpect {
                    status { isOk }
                }
    }
}