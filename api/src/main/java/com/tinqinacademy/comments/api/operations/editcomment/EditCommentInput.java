package com.tinqinacademy.comments.api.operations.editcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class EditCommentInput implements OperationInput {
    @JsonIgnore
    private String commentId;

    private String userId;

    @NotBlank
    private String content;
}
