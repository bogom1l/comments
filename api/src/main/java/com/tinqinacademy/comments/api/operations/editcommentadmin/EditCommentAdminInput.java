package com.tinqinacademy.comments.api.operations.editcommentadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EditCommentAdminInput {
    @JsonIgnore
    private String commentId;

    @NotBlank
    private String content;

    private String roomNumber;

    private String firstName; //

    private String lastName; //
}
