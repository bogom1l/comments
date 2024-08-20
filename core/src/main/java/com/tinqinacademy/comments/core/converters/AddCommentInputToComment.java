package com.tinqinacademy.comments.core.converters;

import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.persistence.model.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@Slf4j
public class AddCommentInputToComment implements Converter<AddCommentInput, Comment> {
    @Override
    public Comment convert(AddCommentInput source) {
        log.info("Started Converter - AddCommentInput to Comment");

        Comment target = Comment.builder()
                .content(source.getContent())
                .userId(UUID.fromString(source.getUserId()))
                .roomId(UUID.fromString(source.getRoomId()))
                .publishDate(LocalDate.now())
                .lastEditedBy(null)
                .build();

        log.info("Ended Converter - AddCommentInput to Comment");
        return target;
    }
}
