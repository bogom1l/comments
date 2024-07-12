package com.tinqinacademy.comments.core;

import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminOutput;
import com.tinqinacademy.comments.core.contracts.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SystemServiceImpl implements SystemService {
    @Override
    public EditCommentAdminOutput editCommentAdmin(EditCommentAdminInput input) {
        log.info("Start editCommentAdmin with input: {}", input);

        EditCommentAdminOutput output = EditCommentAdminOutput.builder()
                .id("1")
                .build();

        log.info("End editCommentAdmin with output: {}", output);
        return output;
    }
}
