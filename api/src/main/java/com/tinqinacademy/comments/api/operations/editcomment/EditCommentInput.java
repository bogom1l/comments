package com.tinqinacademy.comments.api.operations.editcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class EditCommentInput {

    @JsonIgnore
    private String commentId;

    @NotBlank
    private String content;
}
