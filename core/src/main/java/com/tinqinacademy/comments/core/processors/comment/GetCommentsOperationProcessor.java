package com.tinqinacademy.comments.core.processors.comment;

import com.tinqinacademy.comments.core.errorhandler.ErrorHandler;
import com.tinqinacademy.comments.api.error.ErrorsWrapper;
import com.tinqinacademy.comments.api.operations.getcomments.CommentInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOperation;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GetCommentsOperationProcessor extends BaseOperationProcessor<GetCommentsInput> implements GetCommentsOperation {

    private final CommentRepository commentRepository;

    protected GetCommentsOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentRepository commentRepository) {
        super(conversionService, errorHandler, validator);
        this.commentRepository = commentRepository;
    }

    @Override
    public Either<ErrorsWrapper, GetCommentsOutput> process(GetCommentsInput input) {
        return Try.of(() -> getComments(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private GetCommentsOutput getComments(GetCommentsInput input) {
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
}
