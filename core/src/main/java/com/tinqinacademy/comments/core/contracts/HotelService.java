package com.tinqinacademy.comments.core.contracts;

import com.tinqinacademy.comments.persistence.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.persistence.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.persistence.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.persistence.operations.getcomments.GetCommentsOutput;

public interface HotelService {
    GetCommentsOutput getComments(GetCommentsInput input);

    AddCommentOutput addComment(AddCommentInput input);

//    EditCommentOutput editComment(EditCommentInput input);
}
