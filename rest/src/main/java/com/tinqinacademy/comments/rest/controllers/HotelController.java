package com.tinqinacademy.comments.rest.controllers;

import com.tinqinacademy.comments.api.operations.createcomment.CreateCommentInput;
import com.tinqinacademy.comments.api.operations.createcomment.CreateCommentOutput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.core.contracts.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    // TODO: Add Swagger's @Operation, @ApiResponses
    @GetMapping("/{roomId}/comments")
    public ResponseEntity<?> getAllComments(@PathVariable @Valid String roomId) {

        GetCommentsInput input = GetCommentsInput.builder()
                .roomId(roomId)
                .build();

        GetCommentsOutput output = hotelService.getComments(input);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @PostMapping("/{roomId}/comment")
    public ResponseEntity<?> createComment(@PathVariable @Valid String roomId,
                                           @RequestBody @Valid CreateCommentInput input){

        CreateCommentInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .build();

        CreateCommentOutput output = hotelService.createComment(updatedInput);

        return new ResponseEntity<>(output, HttpStatus.CREATED);
    }

}
