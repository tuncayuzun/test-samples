package com.tallstech.samples.model;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;


public record Comment(
        @Id
        Long id,
        @NotNull
        Long topicId,
        @NotNull
        String author,
        @NotNull
        String text,
        @CreatedDate
        LocalDateTime createdDate,
        @LastModifiedDate
        LocalDateTime lastModifiedDate
) {}
