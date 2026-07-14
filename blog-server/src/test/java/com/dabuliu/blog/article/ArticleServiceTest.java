package com.dabuliu.blog.article;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}