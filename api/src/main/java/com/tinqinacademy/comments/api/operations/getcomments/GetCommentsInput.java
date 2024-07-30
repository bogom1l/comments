package com.tinqinacademy.comments.api.operations.getcomments;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetCommentsInput {
    private String roomId;
}
