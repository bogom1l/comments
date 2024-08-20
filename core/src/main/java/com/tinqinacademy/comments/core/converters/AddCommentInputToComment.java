//package com.tinqinacademy.comments.core.converters;
//
//import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
//import com.tinqinacademy.comments.persistence.model.Comment;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Component
//@Slf4j
//public class AddCommentInputToComment implements Converter<AddCommentInput, Comment> {
//    @Override
//    public Comment convert(AddCommentInput source) {
//        log.info("Started Converter - AddCommentInput to Comment");
//
//        //todo; refactor; this will become AddCommentInputToCommentBuilder, because user is another table;
//
//        Comment target = Comment.builder()
//                .content(source.getContent())
//                .firstName(source.getFirstName()) // user
//                .lastName(source.getLastName()) // user
//                .lastEditedBy(source.getFirstName() + " " + source.getLastName()) // user
//                .lastEditedDate(LocalDate.now()) // user
//                .publishDate(LocalDate.now()) // user
//                .build();
//
//        log.info("Ended Converter - AddCommentInput to Comment");
//        return target;
//    }
//}
