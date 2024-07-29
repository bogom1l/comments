package com.tinqinacademy.comments.core;


import com.tinqinacademy.comments.api.error.CommentException;
import com.tinqinacademy.comments.core.contracts.SystemService;
import com.tinqinacademy.comments.persistence.entity.Comment;
import com.tinqinacademy.comments.persistence.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.persistence.operations.deletecommentadmin.DeleteCommentAdminOutput;
import com.tinqinacademy.comments.persistence.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.persistence.operations.editcommentadmin.EditCommentAdminOutput;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    public SystemServiceImpl(CommentRepository commentRepository, ConversionService conversionService) {
        this.commentRepository = commentRepository;
        this.conversionService = conversionService;
    }

    @Override
    public EditCommentAdminOutput editCommentAdmin(EditCommentAdminInput input) {
        log.info("Started editCommentAdmin with input: {}", input);

        Comment comment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new CommentException("Comment not found"));

        if(input.getContent() != null){
            comment.setContent(input.getContent());
        }

        if(input.getFirstName() != null){
            comment.setFirstName(input.getFirstName());
        }

        if(input.getLastName() != null){
            comment.setLastName(input.getLastName());
        }

//        if(input.getRoomNumber() != null){
//
//        }

        comment.setLastEditedDate(LocalDate.now());
        comment.setLastEditedBy("ADMIN");

        commentRepository.save(comment);

        EditCommentAdminOutput output = EditCommentAdminOutput.builder()
                .id(comment.getId())
                .build();

        log.info("Ended editCommentAdmin with output: {}", output);
        return output;
    }

    @Override
    public DeleteCommentAdminOutput deleteCommentAdmin(DeleteCommentAdminInput input) {
        log.info("Started deleteCommentAdmin with input: {}", input);

        Comment comment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new CommentException("Comment not found"));

        commentRepository.delete(comment);

        DeleteCommentAdminOutput output = DeleteCommentAdminOutput.builder().build();

        log.info("Ended deleteCommentAdmin with output: {}", output);
        return output;
    }


}
