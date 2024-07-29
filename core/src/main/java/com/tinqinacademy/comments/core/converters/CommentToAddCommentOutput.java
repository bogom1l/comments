package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.persistence.entity.Comment;
import com.tinqinacademy.comments.persistence.operations.addcomment.AddCommentOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentToAddCommentOutput implements Converter<Comment, AddCommentOutput> {
    @Override
    public AddCommentOutput convert(Comment source) {
        log.info("Started Converter - Comment to AddCommentOutput");

        AddCommentOutput addCommentOutput = AddCommentOutput.builder()
                .id(source.getId())
                .build();

        log.info("Ended Converter - Comment to AddCommentOutput");
        return addCommentOutput;
    }
}
