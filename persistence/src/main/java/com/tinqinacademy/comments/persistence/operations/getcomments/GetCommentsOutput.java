package com.tinqinacademy.comments.persistence.operations.getcomments;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetCommentsOutput {
    List<CommentInput> comments;
}
