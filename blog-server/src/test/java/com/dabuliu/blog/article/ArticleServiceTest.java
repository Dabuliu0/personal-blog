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

                                "第一篇文章",
                                "正文一",
                                false));

                service.addArticle(new Article(

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

                                "Java 入门",
                                "正文一",
                                false));

                service.addArticle(new Article(

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

        @Test
        void shouldReturnRequestedArticlePage() {
                ArticleRepository repository = new InMemoryArticleRepository();
                ArticleService service = new ArticleService(repository);

                service.addArticle(new Article(
                                "第一篇文章",
                                "正文一",
                                false));

                service.addArticle(new Article(
                                "第二篇文章",
                                "正文二",
                                false));

                service.addArticle(new Article(
                                "第三篇文章",
                                "正文三",
                                false));

                var result = service.findAllArticles(1, 1);

                assertEquals(1, result.size());
                assertEquals("第二篇文章", result.get(0).getTitle());
        }

        @Test
        void shouldRejectInvalidPageParameters() {
                ArticleRepository repository = new InMemoryArticleRepository();
                ArticleService service = new ArticleService(repository);

                assertThrows(
                                IllegalArgumentException.class,
                                () -> service.findAllArticles(-1, 10));

                assertThrows(
                                IllegalArgumentException.class,
                                () -> service.findAllArticles(0, 0));

                assertThrows(
                                IllegalArgumentException.class,
                                () -> service.findAllArticles(0, 101));
        }

}