package com.tinqinacademy.comments.persistence.operations.editcomment;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditCommentOutput {
    private UUID id;
}
