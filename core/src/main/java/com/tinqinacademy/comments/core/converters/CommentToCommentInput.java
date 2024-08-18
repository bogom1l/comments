package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.getcomments.CommentInput;
import com.tinqinacademy.comments.persistence.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentToCommentInput implements Converter<Comment, CommentInput> {
    @Override
    public CommentInput convert(Comment source) {
        log.info("Started Converter - Comment to CommentInput");

        //todo; refactor; this will become CommentToCommentInputBuilder, because user is another table;

        CommentInput target = CommentInput.builder()
                .id(String.valueOf(source.getId()))
                .content(source.getContent())
                .fistName(source.getFirstName())
                .lastName(source.getLastName())
                .publishDate(source.getPublishDate())
                .lastEditedDate(source.getLastEditedDate())
                .lastEditedBy(source.getLastEditedBy())
                .build();

        log.info("Ended Converter - Comment to CommentInput");
        return target;
    }
}
