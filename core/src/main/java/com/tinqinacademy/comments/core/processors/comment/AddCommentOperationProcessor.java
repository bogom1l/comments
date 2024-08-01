package com.tinqinacademy.comments.core.processors.comment;

import com.tinqinacademy.comments.api.errorhandler.ErrorHandler;
import com.tinqinacademy.comments.api.errorhandler.ErrorsWrapper;
import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.addcomment.AddCommentOperation;
import com.tinqinacademy.comments.api.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddCommentOperationProcessor extends BaseOperationProcessor<AddCommentInput> implements AddCommentOperation {

    private final CommentRepository commentRepository;

    protected AddCommentOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorHandler, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorsWrapper, AddCommentOutput> process(AddCommentInput input) {
        return Try.of(() -> addComment(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private AddCommentOutput addComment(AddCommentInput input) {
        log.info("Started addComment with input: {}", input);

        validateInput(input);

        Comment comment = conversionService.convert(input, Comment.class);

        commentRepository.save(comment);

        AddCommentOutput output = conversionService.convert(comment, AddCommentOutput.class);

        log.info("Ended addComment with output: {}", output);
        return output;
    }

}
