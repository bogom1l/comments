package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.rest.configurations.RestApiRoutes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllComments() throws Exception {
        mockMvc.perform(get(RestApiRoutes.GET_ALL_COMMENTS, "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllComments_WrongUrl_ReturnsNotFound() throws Exception {

        mockMvc.perform(get(RestApiRoutes.GET_ALL_COMMENTS + "/aaaaaa", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}