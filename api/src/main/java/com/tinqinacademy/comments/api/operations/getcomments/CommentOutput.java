package com.tinqinacademy.comments.api.operations.getcomments;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentOutput {
    private String id;

    private String content;

    private LocalDate publishDate;

    private LocalDate lastEditedDate;

    private String userId; // userId

    private String lastEditedBy; // userId
}
