package com.tinqinacademy.comments.api.operations.editcomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditCommentOutput implements OperationOutput {
    private UUID id;
}
