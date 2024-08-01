package com.tinqinacademy.comments.rest.controllers;


import com.tinqinacademy.comments.api.errorhandler.ErrorsWrapper;
import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.addcomment.AddCommentOperation;
import com.tinqinacademy.comments.api.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentOperation;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOperation;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.rest.configurations.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelController extends BaseController {

    private final GetCommentsOperation getComments;
    private final AddCommentOperation addComment;
    private final EditCommentOperation editComment;

    public HotelController(GetCommentsOperation getComments, AddCommentOperation addComment, EditCommentOperation editComment) {
        this.getComments = getComments;
        this.addComment = addComment;
        this.editComment = editComment;
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

        Either<ErrorsWrapper, GetCommentsOutput> output = getComments.process(input);
        return handle(output);
    }

    @Operation(summary = "Add a comment for a room",
            description = "Add a comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added comment"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @PostMapping(RestApiRoutes.ADD_COMMENT)
    public ResponseEntity<?> addComment(@PathVariable String roomId,
                                        @RequestBody AddCommentInput input) {
        AddCommentInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .build();

        Either<ErrorsWrapper, AddCommentOutput> output = addComment.process(updatedInput);
        return handleWithStatus(output, HttpStatus.CREATED);
    }

    @Operation(summary = "Edit own comment for a room",
            description = "Edit own comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited comment"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @PutMapping(RestApiRoutes.EDIT_COMMENT)
    public ResponseEntity<?> editComment(@PathVariable String commentId,
                                         @RequestBody EditCommentInput input) {
        EditCommentInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .build();

        Either<ErrorsWrapper, EditCommentOutput> output = editComment.process(updatedInput);
        return handle(output);
    }

}
