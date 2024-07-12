package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminOutput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminOutput;
import com.tinqinacademy.comments.core.contracts.SystemService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class SystemController {

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService) {
        this.systemService = systemService;
    }

    @Operation(summary = "Edit comment (ADMIN)", description = "ADMIN edits any comment for a certain room")
    @PutMapping("/comment/{commentId}")
    ResponseEntity<?> editCommentAdmin(@PathVariable String commentId,
                                       @RequestBody @Valid EditCommentAdminInput input) {

        EditCommentAdminInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .build();

        EditCommentAdminOutput output = systemService.editCommentAdmin(updatedInput);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Operation(summary = "Delete comment (ADMIN)", description = "ADMIN deletes any comment for a certain room")
    @DeleteMapping("/comment/{commentId}")
    ResponseEntity<?> deleteCommentAdmin(@PathVariable String commentId) {

        DeleteCommentAdminInput input = DeleteCommentAdminInput
                .builder()
                .commentId(commentId)
                .build();

        DeleteCommentAdminOutput output = systemService.deleteCommentAdmin(input);

        return new ResponseEntity<>(output, HttpStatus.NO_CONTENT);
    }

}
