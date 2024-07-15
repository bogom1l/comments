package com.tinqinacademy.comments.core;

import com.tinqinacademy.comments.api.operations.createcomment.CreateCommentInput;
import com.tinqinacademy.comments.api.operations.createcomment.CreateCommentOutput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentOutput;
import com.tinqinacademy.comments.api.operations.getcomments.GetComment;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.api.contracts.HotelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    @Override
    public GetCommentsOutput getComments(GetCommentsInput input) {
        log.info("Start getComments with input: {}", input);

        List<GetComment> randomComments = generateRandomComments();

        GetCommentsOutput output = GetCommentsOutput.builder()
                .comments(randomComments)
                .build();

        log.info("End getComments with output: {}", output);
        return output;
    }

    private List<GetComment> generateRandomComments(){
        List<GetComment> randomComments = new ArrayList<>();
        LocalDate today = LocalDate.now();

        GetComment randomComment1 = GetComment.builder()
                .id("1")
                .firstName("a")
                .lastName("b")
                .content("c")
                .publishDate(today)
                .lastEditedDate(today.plusDays(1))
                .lastEditedBy("d")
                .build();

        GetComment randomComment2 = GetComment.builder()
                .id("2")
                .firstName("name2")
                .lastName("name3")
                .content("content content content content")
                .publishDate(today.plusDays(5))
                .lastEditedDate(today.plusDays(10))
                .lastEditedBy("admin")
                .build();

        randomComments.add(randomComment1);
        randomComments.add(randomComment2);

        return randomComments;
    }

    @Override
    public CreateCommentOutput createComment(CreateCommentInput input) {
        log.info("Start createComment with input: {}", input);

        CreateCommentOutput output = CreateCommentOutput.builder()
                .id("1")
                .build();

        log.info("End createComment with output: {}", output);
        return output;
    }

    @Override
    public EditCommentOutput editComment(EditCommentInput input) {
        log.info("Start editComment with input: {}", input);

        EditCommentOutput output = EditCommentOutput.builder()
                .id("1")
                .build();

        log.info("End editComment with output: {}", output);
        return output;
    }

}
