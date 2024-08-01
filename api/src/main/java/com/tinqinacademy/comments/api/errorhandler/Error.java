package com.tinqinacademy.comments.api.errorhandler;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Error {
    private String field; // for MethodArgumentNotValidException
    private String message;
}