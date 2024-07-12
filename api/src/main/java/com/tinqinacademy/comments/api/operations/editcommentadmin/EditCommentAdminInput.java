package com.tinqinacademy.comments.api.operations.editcommentadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class EditCommentAdminInput {

    @JsonIgnore
    private String commentId;

    @NotBlank(message = "roomNo is mandatory")
    private String roomNo;

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @NotBlank(message = "content is mandatory")
    private String content;
}
