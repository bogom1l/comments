package com.tinqinacademy.comments.core.contracts;

import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminOutput;

public interface SystemService {

    EditCommentAdminOutput editCommentAdmin(EditCommentAdminInput input);
}
