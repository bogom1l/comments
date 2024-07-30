package com.tinqinacademy.comments.core.services.contracts;


import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminOutput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminOutput;

public interface SystemService {

    EditCommentAdminOutput editCommentAdmin(EditCommentAdminInput input);

    DeleteCommentAdminOutput deleteCommentAdmin(DeleteCommentAdminInput input);
}
