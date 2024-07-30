package com.tinqinacademy.comments.core.services.contracts;

import com.tinqinacademy.comments.api.error.ErrorWrapper;
import org.springframework.web.bind.MethodArgumentNotValidException;

public interface ErrorService {
    ErrorWrapper handleErrors(MethodArgumentNotValidException ex);
}
