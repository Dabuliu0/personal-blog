package com.dabuliu.blog.article;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArticleTest {

    @Test
    void shouldDeleteArticle() {
        Article article = new Article(
                1L,
                "Java 学习记录",
                "测试正文",
                false
        );

        assertFalse(article.isDeleted());

        article.delete();

        assertTrue(article.isDeleted());
    }
}