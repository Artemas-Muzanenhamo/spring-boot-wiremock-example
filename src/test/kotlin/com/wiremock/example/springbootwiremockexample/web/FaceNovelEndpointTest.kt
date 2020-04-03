package com.wiremock.example.springbootwiremockexample.web

import com.wiremock.example.springbootwiremockexample.client.PostClient
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@ExtendWith(SpringExtension::class)
@WebMvcTest(PostNovelEndpoint::class)
class FaceNovelEndpointTest(
        @Autowired
        private val mockMvc: MockMvc,
        @MockBean
        private val postClient: PostClient
) {

    @Test
    fun `get all FaceNovel posts`() {

        mockMvc.get("/posts")
                .andExpect {
                    status { isOk }
                }
    }
}