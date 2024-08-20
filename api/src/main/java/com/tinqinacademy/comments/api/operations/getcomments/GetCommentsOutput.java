package com.tinqinacademy.comments.api.operations.getcomments;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetCommentsOutput implements OperationOutput {
    List<CommentOutput> comments;
}
