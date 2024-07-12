package com.tinqinacademy.comments.core.contracts;

import com.tinqinacademy.comments.api.operations.createcomment.CreateCommentInput;
import com.tinqinacademy.comments.api.operations.createcomment.CreateCommentOutput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;

public interface HotelService {
    GetCommentsOutput getComments(GetCommentsInput input);

    CreateCommentOutput createComment(CreateCommentInput input);
}
