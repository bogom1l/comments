package com.tinqinacademy.comments.api.operations.deletecommentadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteCommentAdminInput implements OperationInput {
    @JsonIgnore
    private String commentId;
}
