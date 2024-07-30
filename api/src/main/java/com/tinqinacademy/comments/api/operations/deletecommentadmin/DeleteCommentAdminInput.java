package com.tinqinacademy.comments.api.operations.deletecommentadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteCommentAdminInput {
    @JsonIgnore
    private String commentId;
}
