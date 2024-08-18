package com.tinqinacademy.comments.core.processors.comment;

import com.tinqinacademy.comments.api.error.ErrorsWrapper;
import com.tinqinacademy.comments.api.exceptions.CommentException;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminOperation;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminOutput;
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

import java.time.LocalDate;
import java.util.UUID;

@Service
@Slf4j
public class EditCommentAdminOperationProcessor extends BaseOperationProcessor<EditCommentAdminInput> implements EditCommentAdminOperation {
    private final CommentRepository commentRepository;

    protected EditCommentAdminOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorHandler, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorsWrapper, EditCommentAdminOutput> process(EditCommentAdminInput input) {
        return Try.of(() -> editCommentAdmin(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private EditCommentAdminOutput editCommentAdmin(EditCommentAdminInput input) {
        log.info("Started editCommentAdmin with input: {}", input);
        validateInput(input);

        Comment comment = commentRepository.findById(UUID.fromString(input.getCommentId()))
                .orElseThrow(() -> new CommentException("Comment not found"));

        if (input.getContent() != null) {
            comment.setContent(input.getContent());
        }

        if (input.getFirstName() != null) {
            comment.setFirstName(input.getFirstName());
        }

        if (input.getLastName() != null) {
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
}
