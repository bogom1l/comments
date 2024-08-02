package com.tinqinacademy.comments.api.operations.addcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.comments.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AddCommentInput implements OperationInput {
    @JsonIgnore
    private String roomId;

    private String content;

    private String firstName; //user

    private String lastName; //user
}
