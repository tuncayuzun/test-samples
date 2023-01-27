package com.tallstech.samples.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import com.tallstech.samples.config.DatasourceConfig;
import com.tallstech.samples.model.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@DataJdbcTest
@Import(DatasourceConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@DisplayName("Comment Repository Integration Tests With TestContainers")
class CommentRepositoryTest {

    @Container
    static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.1"));

    @DynamicPropertySource
    static void overridePostgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
        registry.add("spring.flyway.url", postgresContainer::getJdbcUrl);
    }

    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("When trying to save comment")
    void saveComment() {
        var commentNewComment = new Comment(null, 1L, "Tuncay Uzun", "Testcontainers is great tool.", null, null);
        var createdComment = commentRepository.save(commentNewComment);

        assertAll("Results...",
                () -> assertThat(createdComment.id(), notNullValue()),
                () -> assertThat(createdComment.topicId(), equalTo(1L))
        );
    }

    @Test
    @DisplayName("When trying to fetch comments by topic")
    void findCommentsByTopicId() {

        var firstComment = new Comment(null, 2L, "John Doe", "Testcontainers makes integration test easy.", null, null);
        var secondComment = new Comment(null, 2L, "Joker", "Why so serious about Testcontainers?", null, null);

        commentRepository.saveAll(List.of(firstComment, secondComment));
        var commentsOfTopic = commentRepository.findCommentsByTopicId(2L);

        assertAll("Results...",
                () -> assertThat(commentsOfTopic, hasSize(2)),
                () -> assertThat(commentsOfTopic.get(0).author(), equalTo("John Doe"))
        );
    }
}