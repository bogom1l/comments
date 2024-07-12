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

    @NotBlank(message = "roomId is mandatory") // otherwise it could come as null or empty
    private String roomId;
}
