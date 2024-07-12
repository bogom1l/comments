package com.tinqinacademy.comments.api.operations.editcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class EditCommentInput {

    @JsonIgnore
    private String commentId;

    @Size(min = 1, max = 254)
    @NotBlank(message = "content is mandatory")
    private String content;
}
