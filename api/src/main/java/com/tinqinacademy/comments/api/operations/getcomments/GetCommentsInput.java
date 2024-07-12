package com.tinqinacademy.comments.api.operations.getcomments;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetCommentsInput {

    @NotBlank
    private String roomId;
}
