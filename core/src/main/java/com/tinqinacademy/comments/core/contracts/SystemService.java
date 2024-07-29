package com.tinqinacademy.comments.core.contracts;


import com.tinqinacademy.comments.persistence.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.persistence.operations.deletecommentadmin.DeleteCommentAdminOutput;
import com.tinqinacademy.comments.persistence.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.persistence.operations.editcommentadmin.EditCommentAdminOutput;

public interface SystemService {

    EditCommentAdminOutput editCommentAdmin(EditCommentAdminInput input);

    DeleteCommentAdminOutput deleteCommentAdmin(DeleteCommentAdminInput input);
}
