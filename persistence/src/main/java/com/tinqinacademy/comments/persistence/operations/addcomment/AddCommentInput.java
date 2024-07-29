package com.tinqinacademy.comments.persistence.operations.addcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AddCommentInput {

    @JsonIgnore
    private String roomId;

    private String content;

    private String firstName; //user

    private String lastName; //user

}
