package com.tinqinacademy.comments.core.processors.comment;

import com.tinqinacademy.comments.api.error.ErrorsWrapper;
import com.tinqinacademy.comments.api.exceptions.CommentException;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentOperation;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.core.errorhandler.ErrorHandler;
import com.tinqinacademy.comments.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class EditCommentOperationProcessor extends BaseOperationProcessor<EditCommentInput> implements EditCommentOperation {
    private final CommentRepository commentRepository;

    protected EditCommentOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorHandler, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorsWrapper, EditCommentOutput> process(EditCommentInput input) {
        return Try.of(() -> editComment(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private EditCommentOutput editComment(EditCommentInput input) {
        log.info("Started editComment with input: {}", input);
        validateInput(input);

        Comment comment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new CommentException("Comment not found."));

        if (!comment.getUserId().equals(UUID.fromString(input.getUserId()))) {
            throw new CommentException("User is not allowed to edit this comment because it is not their own comment.");
        }

        comment.setContent(input.getContent());
        comment.setLastEditedBy(UUID.fromString(input.getUserId()));

        commentRepository.save(comment);

        EditCommentOutput output = EditCommentOutput.builder()
                .commentId(comment.getId())
                .build();

        log.info("Ended editComment with output: {}", output);
        return output;
    }
}
