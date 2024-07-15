package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.operations.createcomment.CreateCommentInput;
import com.tinqinacademy.comments.api.operations.createcomment.CreateCommentOutput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.core.contracts.HotelService;
import com.tinqinacademy.comments.rest.configurations.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @Operation(summary = "Get all comments for a room", description = "Get all comments for a room")
    @GetMapping(RestApiRoutes.GET_ALL_COMMENTS)
    public ResponseEntity<?> getAllComments(@PathVariable @Valid String roomId) {

        GetCommentsInput input = GetCommentsInput.builder()
                .roomId(roomId)
                .build();

        GetCommentsOutput output = hotelService.getComments(input);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Operation(summary = "Create a comment for a room", description = "Create a comment for a room")
    @PostMapping(RestApiRoutes.CREATE_COMMENT)
    public ResponseEntity<?> createComment(@PathVariable String roomId,
                                           @RequestBody @Valid CreateCommentInput input) {

        CreateCommentInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .build();

        CreateCommentOutput output = hotelService.createComment(updatedInput);

        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @Operation(summary = "Edit own comment for a room", description = "Edit own comment for a room")
    @PutMapping(RestApiRoutes.EDIT_COMMENT)
    public ResponseEntity<?> editComment(@PathVariable String commentId,
                                         @RequestBody @Valid EditCommentInput input) {

        EditCommentInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .build();

        EditCommentOutput output = hotelService.editComment(updatedInput);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }


}
