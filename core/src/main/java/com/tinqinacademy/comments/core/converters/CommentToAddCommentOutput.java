package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.comments.persistence.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentToAddCommentOutput implements Converter<Comment, AddCommentOutput> {
    @Override
    public AddCommentOutput convert(Comment source) {
        log.info("Started Converter - Comment to AddCommentOutput");

        AddCommentOutput target = AddCommentOutput.builder()
                .commentId(source.getId())
                .build();

        log.info("Ended Converter - Comment to AddCommentOutput");
        return target;
    }
}
