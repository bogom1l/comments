package com.tinqinacademy.comments.rest.controllers;


import com.tinqinacademy.comments.core.contracts.HotelService;
import com.tinqinacademy.comments.persistence.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.persistence.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.persistence.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.persistence.operations.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.persistence.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.persistence.operations.editcommentadmin.EditCommentAdminOutput;
import com.tinqinacademy.comments.persistence.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.persistence.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.rest.configurations.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all comments for a room",
            description = "Get all comments for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved comments"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @GetMapping(RestApiRoutes.GET_ALL_COMMENTS)
    public ResponseEntity<?> getAllComments(@PathVariable String roomId) {
        GetCommentsInput input = GetCommentsInput.builder()
                .roomId(roomId)
                .build();

        GetCommentsOutput output = hotelService.getComments(input);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Operation(summary = "Add a comment for a room",
            description = "Add a comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added comment"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @PostMapping(RestApiRoutes.ADD_COMMENT)
    public ResponseEntity<?> addComment(@PathVariable String roomId, @RequestBody AddCommentInput input) {
        AddCommentInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .build();

        AddCommentOutput output = hotelService.addComment(updatedInput);

        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

    @Operation(summary = "Edit own comment for a room",
            description = "Edit own comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited comment"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
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
