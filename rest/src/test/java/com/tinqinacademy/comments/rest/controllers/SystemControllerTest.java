package com.tinqinacademy.comments.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
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

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
public class SystemControllerTest {
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
    void editCommentAdminReturnsOk() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        EditCommentAdminInput input = EditCommentAdminInput.builder()
                .commentId(commentId)
                .userId(userId)
                .content("Nice room!")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(patch(RestApiRoutes.EDIT_COMMENT_ADMIN, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.commentId").value(commentId));
    }

    @Test
    void editCommentAdminReturnsBadRequest() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();
        String userId = commentRepository.findAll().getFirst().getUserId().toString();

        EditCommentAdminInput input = EditCommentAdminInput.builder()
                .commentId(commentId)
                .userId(userId)
                //.content("Nice room!")
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(patch(RestApiRoutes.EDIT_COMMENT_ADMIN, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteCommentAdminReturnsOk() throws Exception {
        String commentId = commentRepository.findAll().getFirst().getId().toString();

        DeleteCommentAdminInput input = DeleteCommentAdminInput.builder()
                .commentId(commentId)
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(delete(RestApiRoutes.DELETE_COMMENT_ADMIN, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCommentAdminReturnsNotFound() throws Exception {
        UUID commentId = UUID.randomUUID();

        DeleteCommentAdminInput input = DeleteCommentAdminInput.builder()
                .commentId(String.valueOf(commentId))
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(delete(RestApiRoutes.DELETE_COMMENT_ADMIN, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCommentAdminReturnsBadRequest() throws Exception {
        String commentId = "asd"; // invalid uuid format

        DeleteCommentAdminInput input = DeleteCommentAdminInput.builder()
                .commentId(commentId)
                .build();

        String serializedInput = mapper.writeValueAsString(input);

        mvc.perform(delete(RestApiRoutes.DELETE_COMMENT_ADMIN, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializedInput)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isInternalServerError());
    }


}
