package com.tinqinacademy.comments.rest.controllers;


import com.tinqinacademy.comments.core.contracts.SystemService;
import com.tinqinacademy.comments.persistence.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.persistence.operations.deletecommentadmin.DeleteCommentAdminOutput;
import com.tinqinacademy.comments.persistence.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.persistence.operations.editcommentadmin.EditCommentAdminOutput;
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
// @RequestMapping("/system")
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }


    @Operation(summary = "Edit any comment for a room",
            description = "Edit any comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited comment"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @PatchMapping(RestApiRoutes.EDIT_COMMENT_ADMIN)
    public ResponseEntity<?> editCommentAdmin(@PathVariable String commentId,
                                              @RequestBody @Valid EditCommentAdminInput input) {

        EditCommentAdminInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .build();

        EditCommentAdminOutput output = systemService.editCommentAdmin(updatedInput);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }


    @Operation(summary = "Delete any comment for a room",
            description = "Delete any comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted comment"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @DeleteMapping(RestApiRoutes.DELETE_COMMENT_ADMIN)
    ResponseEntity<?> deleteCommentAdmin(@PathVariable String commentId) {

        DeleteCommentAdminInput input = DeleteCommentAdminInput
                .builder()
                .commentId(commentId)
                .build();

        DeleteCommentAdminOutput output = systemService.deleteCommentAdmin(input);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

}
