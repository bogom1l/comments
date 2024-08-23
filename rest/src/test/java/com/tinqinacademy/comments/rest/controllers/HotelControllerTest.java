package com.tinqinacademy.comments.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.restroutes.RestApiRoutes;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // Integrates the Spring TestContext Framework with JUnit 5
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Loads the full application context for integration tests
@AutoConfigureMockMvc // Configures MockMvc for testing Spring MVC controllers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2) // Configures an embedded database for testing
class HotelControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void setup() {
        Comment comment = Comment.builder()
                .id(UUID.fromString("1234112d-6611-4562-a2db-ee1e4f4c3987"))
                .roomId(UUID.fromString("5a76112d-6611-4562-a2db-ee1e4f4c3894"))
                .userId(UUID.fromString("56fc4621-a5f4-442b-82bb-cdd532ba4d12"))
                .content("Hello from test comment!")
                .publishDate(LocalDate.now())
                .build();

        commentRepository.save(comment);
    }

    @AfterEach
    public void afterEach() {
        commentRepository.deleteAll();
    }

    @Test
    void getAllCommentsReturnsOk() throws Exception {
        mvc.perform(get(RestApiRoutes.GET_ALL_COMMENTS, "5a76112d-6611-4562-a2db-ee1e4f4c3894")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comments[0].content").value("Hello from test comment!"));
    }

    @Test
    void getAllCommentsReturnsEmptyList() throws Exception {
        mvc.perform(get(RestApiRoutes.GET_ALL_COMMENTS, "1111112d-6611-4562-a2db-ee1e4f4c3895")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comments").isEmpty());
    }

    @Test
    void addCommentReturnsCreated() throws Exception {
        String roomId = commentRepository.findAll().getFirst().getRoomId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        AddCommentInput input = AddCommentInput.builder()
                .roomId(roomId)
                .userId(userId)
                .content("Nice room!")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.ADD_COMMENT, roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isCreated());
    }

    @Test
    void addCommentReturnsBadRequest() throws Exception {
        String roomId = "6655552d-6611-4562-a2db-ee1e4f4c3894";
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        AddCommentInput input = AddCommentInput.builder()
                .roomId(roomId)
                .userId(userId)
                //.content("Nice room!")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(post(RestApiRoutes.ADD_COMMENT, roomId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void editCommentReturnsOk() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        EditCommentInput input = EditCommentInput.builder()
                .commentId(commentId)
                .userId(userId)
                .content("Nice room!")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.EDIT_COMMENT, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").value(commentId));
    }

    @Test
    void editCommentReturnsBadRequest() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        EditCommentInput input = EditCommentInput.builder()
                .commentId(commentId)
                .userId(userId)
                //.content("Nice room!")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(put(RestApiRoutes.EDIT_COMMENT, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }


}