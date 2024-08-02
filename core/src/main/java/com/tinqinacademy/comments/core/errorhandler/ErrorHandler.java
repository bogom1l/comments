package com.tinqinacademy.comments.core.errorhandler;


import com.tinqinacademy.comments.api.error.Error;
import com.tinqinacademy.comments.api.error.ErrorsWrapper;
import com.tinqinacademy.comments.api.exceptions.CommentException;
import com.tinqinacademy.comments.api.exceptions.ValidationException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Component
public class ErrorHandler {

    public ErrorsWrapper handleErrors(Throwable throwable) {
        List<Error> errors = new ArrayList<>();

        HttpStatus status = Match(throwable).of(
                Case($(instanceOf(MethodArgumentNotValidException.class)), ex -> handleMethodArgumentNotValidException(ex, errors)),
                Case($(instanceOf(ValidationException.class)), ex -> handleValidationException(ex, errors)),
                Case($(instanceOf(CommentException.class)), ex -> handleCommentException(ex, errors)),
                Case($(), ex -> handleGenericException(ex, errors))
        );

        return ErrorsWrapper.builder()
                .errors(errors)
                .httpStatus(status)
                .build();
    }

    private HttpStatus handleValidationException(ValidationException ex, List<Error> errors) {
        ex.getViolations().forEach(violation -> errors.add(Error.builder()
                .field(violation.getField())
                .message(violation.getMessage())
                .build()));
        return HttpStatus.BAD_REQUEST;

    }

    private HttpStatus handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, List<Error> errors) {
        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.add(Error.builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .build()));
        return HttpStatus.BAD_REQUEST;
    }

    private HttpStatus handleCommentException(CommentException ex, List<Error> errors) {
        errors.add(Error.builder().message(ex.getMessage()).build());
        return HttpStatus.NOT_FOUND;
    }

    private HttpStatus handleGenericException(Throwable ex, List<Error> errors) {
        errors.add(Error.builder().message(ex.getMessage()).build());
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}