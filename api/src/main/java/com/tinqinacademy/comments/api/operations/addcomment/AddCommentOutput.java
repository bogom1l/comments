package com.tinqinacademy.comments.api.operations.addcomment;

import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCommentOutput implements OperationOutput {
    private UUID id;
}
