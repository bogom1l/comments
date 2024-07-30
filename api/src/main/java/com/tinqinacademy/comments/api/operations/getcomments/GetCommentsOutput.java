package com.tinqinacademy.comments.api.operations.getcomments;

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
