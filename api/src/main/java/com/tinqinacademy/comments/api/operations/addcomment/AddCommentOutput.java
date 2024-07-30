package com.tinqinacademy.comments.api.operations.addcomment;

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
