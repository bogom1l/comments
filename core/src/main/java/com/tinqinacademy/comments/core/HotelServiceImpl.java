package com.tinqinacademy.comments.core;


import com.tinqinacademy.comments.api.error.CommentException;
import com.tinqinacademy.comments.core.contracts.HotelService;
import com.tinqinacademy.comments.persistence.entity.Comment;
import com.tinqinacademy.comments.persistence.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.persistence.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.persistence.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.persistence.operations.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.persistence.operations.getcomments.CommentInput;
import com.tinqinacademy.comments.persistence.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.persistence.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    public HotelServiceImpl(CommentRepository commentRepository, ConversionService conversionService) {
        this.commentRepository = commentRepository;
        this.conversionService = conversionService;
    }

    @Override
    public GetCommentsOutput getComments(GetCommentsInput input) {
        log.info("Started getComments with input: {}", input);

        // search room by roomId
        // get all comments for that room
        // convert comments to CommentInput

        List<Comment> comments = commentRepository.findAll();

        List<CommentInput> commentInputs = comments.stream()
                .map(comment -> conversionService.convert(comment, CommentInput.class))
                .toList();

        GetCommentsOutput output = GetCommentsOutput.builder()
                .comments(commentInputs)
                .build();

        log.info("Ended getComments with output: {}", output);
        return output;
    }

    @Override
    public AddCommentOutput addComment(AddCommentInput input) {
        log.info("Started addComment with input: {}", input);

        Comment comment = conversionService.convert(input, Comment.class);

        commentRepository.save(comment);

        AddCommentOutput output = conversionService.convert(comment, AddCommentOutput.class);

        log.info("Ended addComment with output: {}", output);
        return output;
    }

    @Override
    public EditCommentOutput editComment(EditCommentInput input) {
        log.info("Started editComment with input: {}", input);

        Comment comment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new CommentException("Comment not found"));

        comment.setContent(input.getContent());

        commentRepository.save(comment);

        EditCommentOutput output = EditCommentOutput.builder()
                .id(comment.getId())
                .build();

        log.info("Ended editComment with output: {}", output);
        return output;
    }

}
