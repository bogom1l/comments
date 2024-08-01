package com.tinqinacademy.comments.api.base;

import com.tinqinacademy.comments.api.errorhandler.ErrorsWrapper;
import io.vavr.control.Either;

public interface OperationProcessor<I extends OperationInput, O extends OperationOutput> {
    Either<ErrorsWrapper, O> process(I input);
}