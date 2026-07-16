package com.dabuliu.blog.article;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.dabuliu.blog.category.Category;

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

                var result = service.searchByTitle("java", 0, 10);

                assertEquals(1, result.size());
                assertEquals("Java 入门", result.get(0).getTitle());
        }

        @Test
        void shouldRejectBlankSearchKeyword() {
                ArticleRepository repository = new InMemoryArticleRepository();
                ArticleService service = new ArticleService(repository);

                assertThrows(
                                IllegalArgumentException.class,
                                () -> service.searchByTitle("   ", 0, 10));
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

        @Test
        void shouldPaginateSearchResults() {
                ArticleRepository repository = new InMemoryArticleRepository();
                ArticleService service = new ArticleService(repository);

                service.addArticle(new Article(
                                "Java 第一篇",
                                "正文一",
                                false));

                service.addArticle(new Article(
                                "Python 学习",
                                "正文二",
                                false));

                service.addArticle(new Article(
                                "Java 第二篇",
                                "正文三",
                                false));

                service.addArticle(new Article(
                                "Java 第三篇",
                                "正文四",
                                false));

                var result = service.searchByTitle("java", 1, 1);

                assertEquals(1, result.size());
                assertEquals("Java 第二篇", result.get(0).getTitle());
        }

        @Test
        void shouldAddArticleWithCategory() {
                ArticleRepository repository = new InMemoryArticleRepository();

                ArticleService service = new ArticleService(repository);

                Category category = new Category("Java");

                Article article = new Article(
                                "分类文章",
                                "关系映射测试",
                                false);

                service.addArticle(article, category);

                var savedArticle = repository.findAll().get(0);

                assertEquals(
                                "Java",
                                savedArticle.getCategory().getName());
        }

}