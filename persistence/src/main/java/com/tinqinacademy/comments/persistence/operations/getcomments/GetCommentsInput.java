package com.tinqinacademy.comments.persistence.operations.getcomments;

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
