package com.tinqinacademy.comments.core.services.contracts;

import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;

public interface HotelService {
    GetCommentsOutput getComments(GetCommentsInput input);

    AddCommentOutput addComment(AddCommentInput input);

    EditCommentOutput editComment(EditCommentInput input);
}
