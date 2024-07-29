package com.tinqinacademy.comments.core;


import com.tinqinacademy.comments.core.contracts.HotelService;
import com.tinqinacademy.comments.persistence.entity.Comment;
import com.tinqinacademy.comments.persistence.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.persistence.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.persistence.operations.getcomments.CommentInput;
import com.tinqinacademy.comments.persistence.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.persistence.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

    private final CommentRepository commentRepository;
    private final ConversionService conversionService;

    public HotelServiceImpl(CommentRepository commentRepository, ConversionService conversionService) {
        this.commentRepository = commentRepository;
        this.conversionService = conversionService;
    }

    @Override
    public GetCommentsOutput getComments(GetCommentsInput input) {
        log.info("Started getComments with input: {}", input);

        // search room by roomId
        // get all comments for that room
        // convert comments to CommentInput

        List<Comment> comments = commentRepository.findAll();

        List<CommentInput> commentInputs = comments.stream()
                .map(comment -> conversionService.convert(comment, CommentInput.class))
                .toList();

        GetCommentsOutput output = GetCommentsOutput.builder()
                .comments(commentInputs)
                .build();

        log.info("Ended getComments with output: {}", output);
        return output;
    }

    @Override
    public AddCommentOutput addComment(AddCommentInput input) {
        log.info("Started addComment with input: {}", input);

        Comment comment = conversionService.convert(input, Comment.class);

        commentRepository.save(comment);

        AddCommentOutput output = conversionService.convert(comment, AddCommentOutput.class);

        log.info("Ended addComment with output: {}", output);
        return output;
    }


//
//    @Override
//    public EditCommentOutput editComment(EditCommentInput input) {
//        return null;
//    }

    /*
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


     */
}
