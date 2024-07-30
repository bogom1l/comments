package com.tinqinacademy.comments.rest.exceptions;


import com.tinqinacademy.comments.api.exceptions.CommentException;
import com.tinqinacademy.comments.api.error.ErrorWrapper;
import com.tinqinacademy.comments.core.services.contracts.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final ErrorService errorService;

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<?> handleHotelException(CommentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // TODO: handle all other different exceptions

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorWrapper errors = errorService.handleErrors(ex);

        return new ResponseEntity<>(errors.getErrors(), errors.getErrorCode());
    }

}