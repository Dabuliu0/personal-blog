package com.dabuliu.blog.article;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArticleServiceTest {

    @Test
    void shouldCountArticles() {
        ArticleRepository repository = new InMemoryArticleRepository();
        ArticleService service = new ArticleService(repository);

        service.addArticle(new Article(
                1L,
                "第一篇文章",
                "正文一",
                false));

        service.addArticle(new Article(
                2L,
                "第二篇文章",
                "正文二",
                true));

        assertEquals(2, service.getArticleCount());
    }

    @Test
    void shouldSearchArticlesIgnoringCase() {
        ArticleRepository repository = new InMemoryArticleRepository();
        ArticleService service = new ArticleService(repository);

        service.addArticle(new Article(
                1L,
                "Java 入门",
                "正文一",
                false));

        service.addArticle(new Article(
                2L,
                "Spring Boot 学习",
                "正文二",
                false));

        var result = service.searchByTitle("java");

        assertEquals(1, result.size());
        assertEquals("Java 入门", result.get(0).getTitle());
    }

    @Test
    void shouldRejectBlankSearchKeyword() {
        ArticleRepository repository = new InMemoryArticleRepository();
        ArticleService service = new ArticleService(repository);

        assertThrows(
                IllegalArgumentException.class,
                () -> service.searchByTitle("   "));
    }

}