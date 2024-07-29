package com.tinqinacademy.comments.persistence.configuration;


import com.tinqinacademy.comments.persistence.entity.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Component
@Order(1)
public class DataSeeder implements ApplicationRunner {

    private final CommentRepository commentRepository;

    @Autowired
    public DataSeeder(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedComments();
    }

    private void seedComments() {
        if (commentRepository.count() != 0) {
            log.info("DataSeeder - didn't seed any bed comments");
            return;
        }

        Comment comment1 = Comment.builder()
                .firstName("John")
                .lastName("Doe")
                .content("This is a comment")
                .publishDate(LocalDate.parse("2021-01-01"))
                .lastEditedDate(LocalDate.parse("2021-01-02"))
                .lastEditedBy("Jane")
                .build();

        Comment comment2 = Comment.builder()
                .firstName("Jane")
                .lastName("Jackson")
                .content("This is another comment")
                .publishDate(LocalDate.parse("2021-01-05"))
                .lastEditedDate(LocalDate.parse("2021-01-15"))
                .lastEditedBy("John")
                .build();

        Comment comment3 = Comment.builder()
                .firstName("Alex")
                .lastName("Alex")
                .content("Comment123123")
                .publishDate(LocalDate.parse("2022-02-22"))
                .lastEditedDate(LocalDate.parse("2022-02-25"))
                .lastEditedBy("Bob")
                .build();

        commentRepository.saveAll(List.of(comment1, comment2, comment3));
        log.info("DataSeeder - Seeded comments");
    }
}
