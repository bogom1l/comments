package com.tinqinacademy.comments.api.error;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ErrorWrapper {
    private List<Error> errors;
    private HttpStatus errorCode;
}

