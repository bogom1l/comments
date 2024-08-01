package com.tinqinacademy.comments.api.operations.editcommentadmin;

import com.tinqinacademy.comments.api.base.OperationInput;
import com.tinqinacademy.comments.api.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditCommentAdminOutput implements OperationOutput {
    private UUID id;
}
