package com.tinqinacademy.comments.persistence.operations.deletecommentadmin;

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
