package com.tinqinacademy.comments.core.processors.comment;

import com.tinqinacademy.comments.api.errorhandler.ErrorHandler;
import com.tinqinacademy.comments.api.errorhandler.ErrorsWrapper;
import com.tinqinacademy.comments.api.exceptions.CommentException;
import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminOutput;
import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminOperation;
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
public class DeleteCommentAdminAdminOperationProcessor extends BaseOperationProcessor<DeleteCommentAdminInput> implements DeleteCommentAdminOperation {

    private final CommentRepository commentRepository;

    protected DeleteCommentAdminAdminOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorHandler, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorsWrapper, DeleteCommentAdminOutput> process(DeleteCommentAdminInput input) {
        return Try.of(() -> deleteComment(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private DeleteCommentAdminOutput deleteComment(DeleteCommentAdminInput input) {
        log.info("Started deleteCommentAdmin with input: {}", input);

        validateInput(input);

        Comment comment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new CommentException("Comment not found"));

        commentRepository.delete(comment);

        DeleteCommentAdminOutput output = DeleteCommentAdminOutput.builder().build();

        log.info("Ended deleteCommentAdmin with output: {}", output);
        return output;
    }

}
