package com.tinqinacademy.comments.api.operations.editcommentadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EditCommentAdminInput implements OperationInput {
    @JsonIgnore
    private String commentId;

    @NotBlank
    private String content;

    private String roomNumber;

    private String firstName; //

    private String lastName; //
}
