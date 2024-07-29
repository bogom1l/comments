package com.tinqinacademy.comments.persistence.operations.addcomment;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCommentOutput {
    private UUID id;
}
