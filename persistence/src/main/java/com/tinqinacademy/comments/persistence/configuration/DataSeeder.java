package com.tinqinacademy.comments.persistence.configuration;

import com.tinqinacademy.comments.persistence.model.Comment;
import com.tinqinacademy.comments.persistence.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements ApplicationRunner {
    private final CommentRepository commentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        seedComments();
    }

    private void seedComments() {
        if (commentRepository.count() != 0) {
            log.info("DataSeeder - Comments were not seeded because they already exist.");
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

        Comment comment4 = Comment.builder()
                .firstName("Emily")
                .lastName("Smith")
                .content("Great service and friendly staff!")
                .publishDate(LocalDate.parse("2023-03-10"))
                .lastEditedDate(LocalDate.parse("2023-03-12"))
                .lastEditedBy("Admin")
                .build();

        Comment comment5 = Comment.builder()
                .firstName("Michael")
                .lastName("Brown")
                .content("The room was clean and well-maintained.")
                .publishDate(LocalDate.parse("2023-04-15"))
                .lastEditedDate(LocalDate.parse("2023-04-16"))
                .lastEditedBy("Admin")
                .build();

        Comment comment6 = Comment.builder()
                .firstName("Sarah")
                .lastName("Davis")
                .content("Had a wonderful stay, will come back again!")
                .publishDate(LocalDate.parse("2023-05-20"))
                .lastEditedDate(LocalDate.parse("2023-05-21"))
                .lastEditedBy("Admin")
                .build();

        commentRepository.saveAll(List.of(comment1, comment2, comment3, comment4, comment5, comment6));
        log.info("DataSeeder - Sample comments seeded successfully.");
    }
}
