package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminOperation;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminOperation;
import com.tinqinacademy.comments.api.restroutes.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SystemController extends BaseController {
    private final DeleteCommentAdminOperation deleteCommentAdmin;
    private final EditCommentAdminOperation editCommentAdmin;

    @Operation(summary = "Edit any comment for a room",
            description = "Edit any comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited comment"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @PatchMapping(RestApiRoutes.EDIT_COMMENT_ADMIN)
    public ResponseEntity<?> editCommentAdmin(@PathVariable String commentId,
                                              @RequestBody EditCommentAdminInput input) {
        EditCommentAdminInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .build();

        return handle(editCommentAdmin.process(updatedInput));
    }

    @Operation(summary = "Delete any comment for a room",
            description = "Delete any comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted comment"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @DeleteMapping(RestApiRoutes.DELETE_COMMENT_ADMIN)
    ResponseEntity<?> deleteCommentAdmin(@PathVariable String commentId) {
        DeleteCommentAdminInput input = DeleteCommentAdminInput
                .builder()
                .commentId(commentId)
                .build();

        return handle(deleteCommentAdmin.process(input));
    }

}
