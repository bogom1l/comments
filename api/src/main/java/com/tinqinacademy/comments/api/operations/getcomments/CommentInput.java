package com.tinqinacademy.comments.api.operations.getcomments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentInput {

    @NotBlank
    private String id;

    private String fistName; //todo; will be refactored later

    private String lastName; //todo; will be refactored later

    @NotBlank
    private String content;

    @NotNull
    private LocalDate publishDate;

    @NotNull
    private LocalDate lastEditedDate;

    private String lastEditedBy; //todo; will be refactored later
}
