package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.getcomments.CommentOutput;
import com.tinqinacademy.comments.persistence.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentToCommentOutput implements Converter<Comment, CommentOutput> {
    @Override
    public CommentOutput convert(Comment source) {
        log.info("Started Converter - Comment to CommentOutput");

        CommentOutput target = CommentOutput.builder()
                .id(String.valueOf(source.getId()))
                .content(source.getContent())
                .publishDate(source.getPublishDate())
                .lastEditedDate(source.getLastEditedDate())
                .userId(String.valueOf(source.getId()))
                .lastEditedBy(String.valueOf(source.getLastEditedBy()))
                .build();

        log.info("Ended Converter - Comment to CommentOutput");
        return target;
    }
}
