package com.tinqinacademy.comments.core.contracts;

import com.tinqinacademy.comments.api.error.ErrorWrapper;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ErrorService {
    ErrorWrapper handleErrors(MethodArgumentNotValidException ex);
}
