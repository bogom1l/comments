package com.tinqinacademy.comments.core.errorhandler;

import com.tinqinacademy.comments.api.error.Error;
import com.tinqinacademy.comments.api.error.ErrorsWrapper;
import com.tinqinacademy.comments.api.exceptions.CommentException;
import com.tinqinacademy.comments.api.exceptions.ValidationException;
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
        return Match(throwable).of(
                Case($(instanceOf(MethodArgumentNotValidException.class)), ex -> handleMethodArgumentNotValidException(ex)),
                Case($(instanceOf(ValidationException.class)), ex -> handleValidationException(ex)),
                Case($(instanceOf(CommentException.class)), ex -> handleCommentException(ex)),
                Case($(), ex -> handleGenericException(ex))
        );
    }

    private static Error createError(String field, String message) {
        return Error.builder()
                .field(field)
                .message(message)
                .build();
    }

    private static ErrorsWrapper createErrorsWrapper(List<Error> errors, HttpStatus status) {
        return ErrorsWrapper.builder()
                .errors(errors)
                .httpStatus(status)
                .build();
    }

    private static ErrorsWrapper handleValidationException(ValidationException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getViolations().forEach(violation -> errors.add(createError(violation.getField(), violation.getMessage())));

        return createErrorsWrapper(errors, HttpStatus.BAD_REQUEST);
    }

    private static ErrorsWrapper handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(createError(error.getField(), error.getDefaultMessage())));

        return createErrorsWrapper(errors, HttpStatus.BAD_REQUEST);
    }

    private static ErrorsWrapper handleCommentException(CommentException ex) {
        List<Error> errors = List.of(createError(null, ex.getMessage()));
        return createErrorsWrapper(errors, HttpStatus.NOT_FOUND);
    }

    private static ErrorsWrapper handleGenericException(Throwable ex) {
        List<Error> errors = List.of(createError(null, ex.getMessage()));
        return createErrorsWrapper(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}