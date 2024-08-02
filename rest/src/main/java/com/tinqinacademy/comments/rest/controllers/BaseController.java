package com.tinqinacademy.comments.rest.controllers;


import com.tinqinacademy.comments.api.base.OperationOutput;
import com.tinqinacademy.comments.api.error.ErrorsWrapper;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class BaseController {
    protected ResponseEntity<?> handle(Either<ErrorsWrapper, ? extends OperationOutput> output) {
        return output.isLeft() ? error(output) : new ResponseEntity<>(output.get(), HttpStatus.OK);
    }

    protected ResponseEntity<?> handleWithStatus(Either<ErrorsWrapper, ? extends OperationOutput> output, HttpStatus status) {
        return output.isLeft() ? error(output) : new ResponseEntity<>(output.get(), status);
    }

    private ResponseEntity<?> error(Either<ErrorsWrapper, ? extends OperationOutput> output) {
        ErrorsWrapper errorWrapper = output.getLeft();
        return new ResponseEntity<>(errorWrapper.getErrors(), errorWrapper.getHttpStatus());
    }
}